package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.user.constants.InviterTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.EFriendInviterBO;
import com.lawu.eshop.user.srv.bo.InviterBO;
import com.lawu.eshop.user.srv.converter.InviterConverter;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDOExample;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.extend.InviteMerchantInfoView;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.MemberDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.MerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.CommonService;
import com.lawu.framework.core.page.Page;

/**
 * 公共服务实现
 *
 * @author meishuquan
 * @date 2017/3/23
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MemberDOMapperExtend memberDOMapperExtend;

    @Autowired
    private MerchantDOMapperExtend merchantDOMapperExtend;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Override
    public InviterBO getInviterByAccount(String account) {

        // 查询会员信息
        MemberDOExample memberDOExample = new MemberDOExample();
        memberDOExample.createCriteria().andAccountEqualTo(account);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(memberDOExample);
        if (!memberDOS.isEmpty()) {
            return InviterConverter.convertBO(memberDOS.get(0));
        }

        // 查询商户信息
        MerchantDOExample merchantDOExample = new MerchantDOExample();
        merchantDOExample.createCriteria().andAccountEqualTo(account);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(merchantDOExample);
        if (!merchantDOS.isEmpty()) {
            MerchantStoreDOExample merchantStoreDOExample = new MerchantStoreDOExample();
            merchantStoreDOExample.createCriteria().andMerchantIdEqualTo(merchantDOS.get(0).getId());
            List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(merchantStoreDOExample);
            if (!merchantStoreDOS.isEmpty()) {
                return InviterConverter.convertBO(merchantStoreDOS.get(0), merchantDOS.get(0));
            }
            return InviterConverter.convertBO(merchantDOS.get(0));
        }
        return null;
    }

	@Override
	public List<CommissionInvitersUserDTO> selectHigherLevelInviters(String invitedUserNum, int level,
											     boolean isLevel) {
		InviteRelationDOExample example = new InviteRelationDOExample();
		example.createCriteria().andInviteUserNumEqualTo(invitedUserNum).andDepthBetween(1, level).andStatusEqualTo(StatusEnum.VALID.getValue()).andIsDelEqualTo(false);
		example.setOrderByClause(" depth asc ");
		List<InviteRelationDO> dos = inviteRelationDOMapper.selectByExample(example);
		List<CommissionInvitersUserDTO> list = new ArrayList<CommissionInvitersUserDTO>();
		for (InviteRelationDO irdo : dos) {
			CommissionInvitersUserDTO user = new CommissionInvitersUserDTO();
			user.setLevel(1);
			if(isLevel){
				if (irdo.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
					MemberDOExample memberExample = new MemberDOExample();
					memberExample.createCriteria().andNumEqualTo(irdo.getUserNum());
					List<MemberDO> members = memberDOMapper.selectByExample(memberExample);
					if(members != null && !members.isEmpty()){
						user.setLevel(members.get(0).getLevel());
					}
				} else if (irdo.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
					MerchantDOExample merchantExample = new MerchantDOExample();
					merchantExample.createCriteria().andNumEqualTo(irdo.getUserNum());
					List<MerchantDO> merchants = merchantDOMapper.selectByExample(merchantExample);
					if(merchants != null && !merchants.isEmpty()){
						user.setLevel(merchants.get(0).getLevel());
					}
				}
			}
			user.setUserNum(irdo.getUserNum());
			user.setDept(irdo.getDepth().intValue());
			if(invitedUserNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
				user.setFlag(1);
			} else if(invitedUserNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
				user.setFlag(2);
			}
			list.add(user);
		}
		return list;
	}

    @Override
    public Page<EFriendInviterBO> selectEFriend(EFriendQueryDataParam dataParam) {
        Page<EFriendInviterBO> page = new Page<>();
        List<EFriendInviterBO> rtnList = new ArrayList<>();
        List<String> userNumList = new ArrayList<>();
        if (StringUtils.isNotEmpty(dataParam.getQueryContent())) {
            //用户：账号或昵称、商家：账号或店铺名称
            List<String> memberNumList = memberDOMapperExtend.selectNumLikeContent("%" + dataParam.getQueryContent() + "%");
            List<String> merchantNumList = merchantDOMapperExtend.selectNumLikeContent("%" + dataParam.getQueryContent() + "%");
            userNumList.addAll(memberNumList);
            userNumList.addAll(merchantNumList);
            if (userNumList.isEmpty()) {
                page.setTotalCount(0);
                page.setCurrentPage(dataParam.getCurrentPage());
                page.setRecords(rtnList);
                return page;
            }
        }

        RowBounds rowBounds = new RowBounds(dataParam.getOffset(), dataParam.getPageSize());
        InviteRelationDOExample example = new InviteRelationDOExample();
        //过滤已删除，未生效的推荐关系
        com.lawu.eshop.user.srv.domain.InviteRelationDOExample.Criteria criteria = example.createCriteria();
        criteria.andUserNumEqualTo(dataParam.getUserNum()).andDepthEqualTo(1)
                .andStatusEqualTo(StatusEnum.VALID.getValue()).andIsDelEqualTo(false);
        if (!userNumList.isEmpty()) {
            criteria.andInviteUserNumIn(userNumList);
        }
        if (dataParam.getTypeEnum() != null) {
            if (dataParam.getTypeEnum() == InviterTypeEnum.MEMBER) {
                criteria.andInviteUserNumLike(UserCommonConstant.MEMBER_NUM_TAG + "%");
            } else {
                criteria.andInviteUserNumLike(UserCommonConstant.MERCHANT_NUM_TAG + "%");
            }
        }
        List<InviteRelationDO> inviteUserList = inviteRelationDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Integer totalCount = inviteRelationDOMapper.countByExample(example);

        MemberDOExample memberDOExample = new MemberDOExample();
        for (InviteRelationDO invite : inviteUserList) {
            EFriendInviterBO bo = new EFriendInviterBO();
            if (invite.getInviteUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                memberDOExample.clear();
                memberDOExample.createCriteria().andNumEqualTo(invite.getInviteUserNum());
                List<MemberDO> memberList = memberDOMapper.selectByExample(memberDOExample);
                if (memberList == null || memberList.isEmpty()) {
                    continue;
                }
                MemberDO member = memberList.get(0);
                bo.setTitleName("E店用户");
                bo.setGmtCreate(member.getGmtCreate());
                if (StringUtils.isNotEmpty(member.getHeadimg())) {
                    bo.setHeadImg(member.getHeadimg());
                } else {
                    bo.setHeadImg(userSrvConfig.getDefaultHeadimg());
                }
                bo.setLevel(member.getLevel() == null ? 1 : member.getLevel());
                bo.setName(member.getNickname() == null ? "E店用户" : member.getNickname());
                bo.setAccount(member.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                bo.setRegionAddress(member.getRegionName() == null ? "" : member.getRegionName());
                MemberProfileDO memberProfileDO = memberProfileDOMapper.selectByPrimaryKey(member.getId());
                if (memberProfileDO == null) {
                    bo.setInviterCount(0);
                } else {
                    bo.setInviterCount(memberProfileDO.getInviteMemberCount() + memberProfileDO.getInviteMemberCount2() + memberProfileDO.getInviteMerchantCount() + memberProfileDO.getInviteMerchantCount2());
                }
                bo.setTotalInviteCount(bo.getInviterCount());
                bo.setUserNum(invite.getInviteUserNum());
            } else if (invite.getInviteUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                List<InviteMerchantInfoView> merchantList = merchantDOMapperExtend.selectInviteMerchantInfo(invite.getInviteUserNum());
                if (merchantList == null || merchantList.isEmpty()) {
                    continue;
                }
                InviteMerchantInfoView merchant = merchantList.get(0);
                if (merchant.getStoreName() != null && !"".equals(merchant.getStoreName())) {
                    bo.setTitleName(merchant.getStoreName());
                } else {
                    bo.setTitleName("E店商家");
                }
                bo.setGmtCreate(merchant.getGmtCreate());
                bo.setLevel(merchant.getLevel() == null ? 1 : merchant.getLevel());
                bo.setName(merchant.getPrincipalName() == null ? "E店商家" : merchant.getPrincipalName());
                bo.setAccount(merchant.getAccount().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                String detailAddress = merchant.getRegionName() == null ? (merchant.getAddress() == null ? "" : merchant.getAddress()) : merchant.getRegionName() + (merchant.getAddress() == null ? "" : merchant.getAddress());
                bo.setRegionAddress(detailAddress);
                bo.setMerchantStatus(MerchantStatusEnum.getEnum(merchant.getStatus()));

                MerchantProfileDO merchantProfileDO = merchantProfileDOMapper.selectByPrimaryKey(merchant.getId());
                if (merchantProfileDO == null) {
                    bo.setInviterCount(0);
                } else {
                    bo.setInviterCount(merchantProfileDO.getInviteMemberCount() + merchantProfileDO.getInviteMemberCount2() + merchantProfileDO.getInviteMerchantCount() + merchantProfileDO.getInviteMerchantCount2());
                }
                bo.setTotalInviteCount(bo.getInviterCount());

                MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
                merchantStoreImageDOExample.createCriteria().andMerchantIdEqualTo(merchant.getId()).andStatusEqualTo(true).andTypeEqualTo(new Byte("3"));
                List<MerchantStoreImageDO> merchantStoreImgList = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
                if (merchantStoreImgList == null || merchantStoreImgList.isEmpty()) {
                    bo.setHeadImg(userSrvConfig.getMerchant_headimg());
                } else {
                    bo.setHeadImg(merchantStoreImgList.get(0).getPath());
                }
                bo.setUserNum(invite.getInviteUserNum());
            }
            rtnList.add(bo);
        }
        page.setTotalCount(totalCount);
        page.setCurrentPage(dataParam.getCurrentPage());
        page.setRecords(rtnList);
        return page;
    }
}
