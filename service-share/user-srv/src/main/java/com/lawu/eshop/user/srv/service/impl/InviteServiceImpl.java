package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.user.constants.UserInviterTypeEnum;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDOExample;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.MemberProfileDOExample;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDOExample;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.InviteRelationDOExtendMapper;
import com.lawu.eshop.user.srv.mapper.extend.MemberDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.MerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.InviteService;

/**
 * @author zhangyong
 * @date 2018/1/16.
 */
@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MemberDOMapperExtend memberDOMapperExtend;

    @Autowired
    private MerchantDOMapperExtend merchantDOMapperExtend;

    @Autowired
    private InviteRelationDOExtendMapper inviteRelationDOExtendMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInviteUserProfileInfo(String userNum,Long id) {

        // 更新相关推荐关系有效状态：无效--->有效

        /*InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
        inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(userNum)
                .andStatusEqualTo(StatusEnum.NO_VALID.getValue()).andIsDelEqualTo(false);
        List<InviteRelationDO> olds = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
        if (olds.isEmpty()) {
            return;
        }*/

    	//查询商家，会员最后登录时间

		if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {

			MemberProfileDOExample mExample = new MemberProfileDOExample();
			mExample.createCriteria().andIdEqualTo(id).andGmtLastLoginIsNotNull();
			long count = memberProfileDOMapper.countByExample(mExample);
			if(count == 0) return ;
		} else {

			MerchantProfileDOExample mExample = new MerchantProfileDOExample();
			mExample.createCriteria().andIdEqualTo(id).andGmtLastLoginIsNotNull();
            long count = merchantProfileDOMapper.countByExample(mExample);
			if(count == 0) return ;
		}

        InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
        inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(userNum);
        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setStatus(StatusEnum.VALID.getValue());
        inviteRelationDOMapper.updateByExampleSelective(inviteRelationDO, inviteRelationDOExample);
    	updateRelationship(userNum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRelationship(String userNum) {
        // 将所有一级下线标记为僵尸账号
        memberDOMapperExtend.updateZombieAccount(userNum, true);
        merchantDOMapperExtend.updateZombieAccount(userNum, true);

        // 查询当前用户上级
        InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
        inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(userNum).andDepthEqualTo(1);
        List<InviteRelationDO> inviteRelationDOlist = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
        String parentUserNum = null;
        if (!inviteRelationDOlist.isEmpty()) {
            parentUserNum = inviteRelationDOlist.get(0).getUserNum();
        }

        // 根据当前用户编号删除上三级与下三级的关联
        inviteRelationDOExtendMapper.deleteRelationship(userNum);

        // 更新上级E友数量以及用户VIP等级
        if (parentUserNum != null) {
            updateRelationship(parentUserNum);
        }

        // 更新当前用户的E友数量以及VIP等级
        updateRelationship(userNum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void restoreRelationship(String userNum) {
        // 该用户所有非冻结用户编号
        List<String> lowerLevelNums = new ArrayList<String>();
        // 当前遍历用户编号集合
        List<String> userNums = new ArrayList<String>();
        userNums.add(userNum);
        lowerLevelNums.addAll(userNums);
        // 遍历查询下三级
        for (int i = 1; i <= 3; i ++) {
            userNums = inviteRelationDOExtendMapper.lowerLevelNumByDepth(userNums);
            if (userNums == null || userNums.isEmpty()) {
                break;
            }
            lowerLevelNums.addAll(userNums);
        }

        InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
        inviteRelationDOExample.setOrderByClause("depth asc");
        inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(userNum).andDepthBetween(0, 3);
        List<InviteRelationDO> inviteRelationDOlist = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
        if (!inviteRelationDOlist.isEmpty()) {
            for (InviteRelationDO item : inviteRelationDOlist) {
                boolean isFreeze = false;
                if (UserInviterTypeEnum.INVITER_TYPE_MEMBER.equals(UserInviterTypeEnum.getEnum(item.getType()))) {
                    MemberDOExample example = new MemberDOExample();
                    example.createCriteria().andNumEqualTo(item.getUserNum());
                    List<MemberDO> memberDOList = memberDOMapper.selectByExample(example);
                    MemberDO memberDO = memberDOList.isEmpty() ? null : memberDOList.get(0);
                    if (memberDO != null) {
                        if (FreezeTypeEnum.ROBOT_REGISTRATION.equals(FreezeTypeEnum.getEnum(memberDO.getFrozenType()))) {
                            isFreeze = memberDO.getIsFreeze();
                        }
                    }
                } else if (UserInviterTypeEnum.INVITER_TYPE_MEMBER.equals(UserInviterTypeEnum.getEnum(item.getType()))) {
                    MerchantDOExample example = new MerchantDOExample();
                    example.createCriteria().andNumEqualTo(item.getUserNum());
                    List<MerchantDO> merchantDOList = merchantDOMapper.selectByExample(example);
                    MerchantDO merchantDO = merchantDOList.isEmpty() ? null : merchantDOList.get(0);
                    if (merchantDO != null) {
                        if (FreezeTypeEnum.ROBOT_REGISTRATION.equals(FreezeTypeEnum.getEnum(merchantDO.getFrozenType()))) {
                            isFreeze = merchantDO.getIsFreeze();
                        }
                    }
                }
                if (isFreeze) {
                    break;
                }
                // 当下级编号有数据时
                if (!lowerLevelNums.isEmpty()) {
                    // 防止下级数据过多,分批恢复
                    for (int i = 0; i < lowerLevelNums.size(); i = i + 100) {
                        // 根据当前用户编号以及下级用户编号恢复关联
                        inviteRelationDOExtendMapper.restoreRelationship(item.getUserNum(), lowerLevelNums.subList(i, i + 100 > lowerLevelNums.size() ? lowerLevelNums.size() : i + 100));
                    }
                }
            }
        }

        // 将所有一级下线去除僵尸标记
        memberDOMapperExtend.updateZombieAccount(userNum, false);
        merchantDOMapperExtend.updateZombieAccount(userNum, false);

        // 更新上级E友数量以及用户VIP等级
        updateRelationship(userNum);
    }

    private void updateRelationship(String userNum) {
        // 查询会员的三级推荐人,包括自身
        InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
        inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(userNum).andDepthBetween(0, 3)
                .andStatusEqualTo(StatusEnum.VALID.getValue()).andIsDelEqualTo(false);
        List<InviteRelationDO> inviteRelationDOS = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
        if (!inviteRelationDOS.isEmpty()) {
            for (InviteRelationDO inviteRelationDO1 : inviteRelationDOS) {
                // 查询会员推荐人的推荐人数
                inviteRelationDOExample = new InviteRelationDOExample();
                inviteRelationDOExample.createCriteria().andUserNumEqualTo(inviteRelationDO1.getUserNum())
                        .andDepthBetween(1, 3).andStatusEqualTo(StatusEnum.VALID.getValue()).andIsDelEqualTo(false);
                int inviteCount = inviteRelationDOMapper.countByExample(inviteRelationDOExample);
                // 更新会员推荐人的等级
                int level = UserCommonConstant.LEVEL_1;
                if (inviteCount >= UserCommonConstant.LEVEL_5_FANS_CNT) {
                    level = UserCommonConstant.LEVEL_5;
                } else if (inviteCount >= UserCommonConstant.LEVEL_4_FANS_CNT) {
                    level = UserCommonConstant.LEVEL_4;
                } else if (inviteCount >= UserCommonConstant.LEVEL_3_FANS_CNT) {
                    level = UserCommonConstant.LEVEL_3;
                } else if (inviteCount >= UserCommonConstant.LEVEL_2_FANS_CNT) {
                    level = UserCommonConstant.LEVEL_2;
                }

                int inviteMemberCount = 0;
                int inviteMemberCount2 = 0;
                int inviteMemberCount3 = 0;
                int inviteMerchantCount = 0;
                int inviteMerchantCount2 = 0;
                int inviteMerchantCount3 = 0;
                // 查询推荐的一级会员/商家总人数
                inviteRelationDOExample = new InviteRelationDOExample();
                inviteRelationDOExample.createCriteria().andUserNumEqualTo(inviteRelationDO1.getUserNum())
                        .andDepthEqualTo(UserCommonConstant.DEPTH_1).andStatusEqualTo(StatusEnum.VALID.getValue())
                        .andIsDelEqualTo(false);
                List<InviteRelationDO> inviteRelationDOList = inviteRelationDOMapper
                        .selectByExample(inviteRelationDOExample);
                if (!inviteRelationDOList.isEmpty()) {
                    for (InviteRelationDO relationDO : inviteRelationDOList) {
                        if (relationDO.getInviteUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                            inviteMemberCount++;
                        } else {
                            inviteMerchantCount++;
                        }
                    }
                }
                // 查询推荐的二级会员/商家总人数
                inviteRelationDOExample = new InviteRelationDOExample();
                inviteRelationDOExample.createCriteria().andUserNumEqualTo(inviteRelationDO1.getUserNum())
                        .andDepthEqualTo(UserCommonConstant.DEPTH_2).andStatusEqualTo(StatusEnum.VALID.getValue())
                        .andIsDelEqualTo(false);
                inviteRelationDOList = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
                if (!inviteRelationDOList.isEmpty()) {
                    for (InviteRelationDO relationDO : inviteRelationDOList) {
                        if (relationDO.getInviteUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                            inviteMemberCount2++;
                        } else {
                            inviteMerchantCount2++;
                        }
                    }
                }
                // 查询推荐的三级会员/商家总人数
                inviteRelationDOExample = new InviteRelationDOExample();
                inviteRelationDOExample.createCriteria().andUserNumEqualTo(inviteRelationDO1.getUserNum())
                        .andDepthEqualTo(UserCommonConstant.DEPTH_3).andStatusEqualTo(StatusEnum.VALID.getValue())
                        .andIsDelEqualTo(false);
                inviteRelationDOList = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
                if (!inviteRelationDOList.isEmpty()) {
                    for (InviteRelationDO relationDO : inviteRelationDOList) {
                        if (relationDO.getInviteUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                            inviteMemberCount3++;
                        } else {
                            inviteMerchantCount3++;
                        }
                    }
                }

                if (UserInviterTypeEnum.INVITER_TYPE_MEMBER.val.equals(inviteRelationDO1.getType())) {
                    MemberDOExample memberDOExample = new MemberDOExample();
                    memberDOExample.createCriteria().andNumEqualTo(inviteRelationDO1.getUserNum());
                    List<MemberDO> memberDOS = memberDOMapper.selectByExample(memberDOExample);
                    MemberDO memberDO = new MemberDO();
                    memberDO.setId(memberDOS.get(0).getId());
                    memberDO.setLevel(level);
                    memberDOMapper.updateByPrimaryKeySelective(memberDO);

                    // 更新会员扩展信息
                    MemberProfileDO memberProfileDO = new MemberProfileDO();
                    memberProfileDO.setId(memberDOS.get(0).getId());
                    memberProfileDO.setInviteMemberCount(inviteMemberCount);
                    memberProfileDO.setInviteMemberCount2(inviteMemberCount2);
                    memberProfileDO.setInviteMemberCount3(inviteMemberCount3);
                    memberProfileDO.setInviteMerchantCount(inviteMerchantCount);
                    memberProfileDO.setInviteMerchantCount2(inviteMerchantCount2);
                    memberProfileDO.setInviteMerchantCount3(inviteMerchantCount3);
                    memberProfileDO.setGmtModified(new Date());
                    memberProfileDOMapper.updateByPrimaryKeySelective(memberProfileDO);
                } else {
                    MerchantDOExample merchantDOExample = new MerchantDOExample();
                    merchantDOExample.createCriteria().andNumEqualTo(inviteRelationDO1.getUserNum());
                    List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(merchantDOExample);
                    MerchantDO merchantDO = new MerchantDO();
                    merchantDO.setLevel(level);
                    merchantDO.setId(merchantDOS.get(0).getId());
                    merchantDOMapper.updateByPrimaryKeySelective(merchantDO);

                    // 更新商户扩展信息
                    MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
                    merchantProfileDO.setId(merchantDOS.get(0).getId());
                    merchantProfileDO.setInviteMemberCount(inviteMemberCount);
                    merchantProfileDO.setInviteMemberCount2(inviteMemberCount2);
                    merchantProfileDO.setInviteMemberCount3(inviteMemberCount3);
                    merchantProfileDO.setInviteMerchantCount(inviteMerchantCount);
                    merchantProfileDO.setInviteMerchantCount2(inviteMerchantCount2);
                    merchantProfileDO.setInviteMerchantCount3(inviteMerchantCount3);
                    merchantProfileDO.setGmtModified(new Date());
                    merchantProfileDOMapper.updateByPrimaryKeySelective(merchantProfileDO);
                }
            }
        }
    }

}
