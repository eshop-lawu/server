package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.constants.UserInviterTypeEnum;
import com.lawu.eshop.user.constants.UserStatusEnum;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MerchantInviterParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantBaseInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantFreezeInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.converter.MerchantConverter;
import com.lawu.eshop.user.srv.converter.MerchantInviterConverter;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDOExample;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDOExample;
import com.lawu.eshop.user.srv.domain.UserLoginLogDO;
import com.lawu.eshop.user.srv.domain.extend.InviterMerchantDOView;
import com.lawu.eshop.user.srv.domain.extend.MerchantDOView;
import com.lawu.eshop.user.srv.domain.extend.MerchantPushView;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.UserLoginLogDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.InviterMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.MerchantDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.MerchantStoreDOMapperExtend;
import com.lawu.eshop.user.srv.rong.models.TokenResult;
import com.lawu.eshop.user.srv.rong.service.RongUserService;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.eshop.user.srv.service.UserCommonService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.PwdUtil;

/**
 * 商户服务实现
 *
 * @author meishuquan
 * @date 2017/3/22
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MerchantStoreDOMapper merchantStoreDOMapper;

    @SuppressWarnings("rawtypes")
    @Autowired
    @Qualifier("merchantRegTransactionMainServiceImpl")
    private TransactionMainService transactionMainService;

    @Autowired
    private InviterMerchantDOMapperExtend inviterMerchantDOMapper;

    @Autowired
    private RongUserService rongUserService;

    @Autowired
    private MerchantStoreDOMapperExtend merchantStoreDOMapperExtend;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Autowired
    private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private MerchantDOMapperExtend merchantDOMapperExtend;

    @Autowired
    private UserLoginLogDOMapper userLoginLogDOMapper;

    @Autowired
    private UserCommonService userCommonService;

    @Autowired
    private MemberDOMapper memberDOMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLoginPwd(Long id, String newPwd) {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(id);
        merchantDO.setPwd(PwdUtil.generate(newPwd));
        merchantDOMapper.updateByPrimaryKeySelective(merchantDO);
    }

    @Override
    public MerchantBO getMerchantByAccount(String account) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andAccountEqualTo(account);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
        return merchantDOS.isEmpty() ? null : MerchantConverter.convertBO(merchantDOS.get(0));
    }

    @Override
    public MerchantInfoBO findMerchantInfo(Long merchantId) {
        MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(merchantId);

        return MerchantConverter.convertInfoBO(merchantDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterRealParam registerRealParam) {
        //推荐人id
        long inviterId = 0;
        if (registerRealParam.getInviterId() != null) {
            inviterId = registerRealParam.getInviterId();
        }
        //推荐人类型
        byte inviterType = UserInviterTypeEnum.INVITER_TYPE_NULL.val;
        if (registerRealParam.getUserNum() != null) {
            if (registerRealParam.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                inviterType = UserInviterTypeEnum.INVITER_TYPE_MEMBER.val;
            } else {
                inviterType = UserInviterTypeEnum.INVITER_TYPE_MERCHANT.val;
            }
        }

        //插入商户信息
        String userNum = IdWorkerHelperImpl.generate(BizIdType.MERCHANT);
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum(userNum);
        merchantDO.setAccount(registerRealParam.getAccount());
        merchantDO.setPwd(PwdUtil.generate(registerRealParam.getPwd()));
        merchantDO.setMobile(registerRealParam.getAccount());
        merchantDO.setStatus(UserStatusEnum.MEMBER_STATUS_VALID.val);
        merchantDO.setHeadimg(userSrvConfig.getMerchant_headimg());
        merchantDO.setInviterId(inviterId);
        merchantDO.setInviterType(inviterType);
        merchantDO.setLevel(UserCommonConstant.LEVEL_1);
        merchantDO.setGmtCreate(new Date());
        merchantDOMapper.insertSelective(merchantDO);
        long merchantId = merchantDO.getId();

        //插入商户扩展信息
        MerchantProfileDO merchantProfileDO = new MerchantProfileDO();
        merchantProfileDO.setId(merchantId);
        merchantProfileDO.setGmtCreate(new Date());
        merchantProfileDO.setRegIp(registerRealParam.getRegIp());
        merchantProfileDO.setRegAppVer(registerRealParam.getRegAppVer());
        merchantProfileDO.setRegPlatformVer(registerRealParam.getRegPlatformVer());
        merchantProfileDOMapper.insertSelective(merchantProfileDO);

        //插入商户推荐关系
        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setUserNum(userNum);
        inviteRelationDO.setInviteUserNum(userNum);
        inviteRelationDO.setDepth(0);
        inviteRelationDO.setType(UserInviterTypeEnum.INVITER_TYPE_MERCHANT.val);
        inviteRelationDO.setStatus(StatusEnum.VALID.getValue());
        inviteRelationDO.setIsDel(false);
        inviteRelationDOMapper.insert(inviteRelationDO);

        if (inviterId > 0) {
            //查询推荐人推荐关系
            InviteRelationDOExample inviteRelationDOExample = new InviteRelationDOExample();
            inviteRelationDOExample.createCriteria().andInviteUserNumEqualTo(registerRealParam.getUserNum())
                    .andStatusEqualTo(StatusEnum.VALID.getValue()).andIsDelEqualTo(false).andDepthBetween(0, 2);
            List<InviteRelationDO> inviteRelationDOS = inviteRelationDOMapper.selectByExample(inviteRelationDOExample);
            if (!inviteRelationDOS.isEmpty()) {
                //更新推荐关系
                for (InviteRelationDO inviteRelationDO1 : inviteRelationDOS) {
                    inviteRelationDO = new InviteRelationDO();
                    inviteRelationDO.setUserNum(inviteRelationDO1.getUserNum());
                    inviteRelationDO.setInviteUserNum(userNum);
                    inviteRelationDO.setDepth(inviteRelationDO1.getDepth() + 1);
                    inviteRelationDO.setStatus(StatusEnum.INVALID.getValue());
                    inviteRelationDO.setIsDel(false);
                    inviteRelationDO.setType(inviteRelationDO1.getType());
                    inviteRelationDOMapper.insert(inviteRelationDO);
                }
            }


        }
        //获取融云token
        TokenResult tokenResult = rongUserService.getRongToken(userNum, "E店商家", userSrvConfig.getMerchant_headimg());
        if (tokenResult != null && StringUtils.isNotEmpty(tokenResult.getToken())) {
            MerchantDO merchantDO1 = new MerchantDO();
            merchantDO1.setRyToken(tokenResult.getToken());
            merchantDO1.setId(merchantId);
            merchantDOMapper.updateByPrimaryKeySelective(merchantDO1);
        }
        transactionMainService.sendNotice(merchantId);
    }

    @Override
    public MerchantBO getMerchantBOById(Long id) {
        MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(id);
        return MerchantConverter.convertBO(merchantDO);
    }

    @Override
    public Page<MerchantInviterBO> getMerchantByInviter(Long userId, MerchantInviterParam pageParam, byte inviterType) {

        InviterMerchantDOView inviterMerchantDO = new InviterMerchantDOView();
        inviterMerchantDO.setInviterId(userId);
        inviterMerchantDO.setInviterType(inviterType);
        if (StringUtils.isNotEmpty(pageParam.getName())) {
            inviterMerchantDO.setName("%" + pageParam.getName() + "%");
        }
        int count = inviterMerchantDOMapper.selectInviterMerchantCount(inviterMerchantDO);
        RowBounds rowBounds = new RowBounds(pageParam.getOffset(), pageParam.getPageSize());
        //推荐的商家
        List<InviterMerchantDOView> inviterMerchantDOS = inviterMerchantDOMapper.selectInviterMerchantByRowbounds(inviterMerchantDO, rowBounds);
        for (InviterMerchantDOView inviterMerchantDOView : inviterMerchantDOS) {
            MerchantProfileDO merchantProfileDO = merchantProfileDOMapper.selectByPrimaryKey(inviterMerchantDOView.getId());
            if (merchantProfileDO == null) {
                inviterMerchantDOView.setInviterCount(0);
            } else {
                inviterMerchantDOView.setInviterCount(merchantProfileDO.getInviteMerchantCount2() + merchantProfileDO.getInviteMerchantCount3());
            }
            //获取门店logo
            MerchantStoreImageDOExample msidExample = new MerchantStoreImageDOExample();
            msidExample.createCriteria().andMerchantIdEqualTo(inviterMerchantDOView.getId()).andStatusEqualTo(true).andTypeEqualTo(new Byte("3"));
            List<MerchantStoreImageDO> msiList = merchantStoreImageDOMapper.selectByExample(msidExample);
            if (!msiList.isEmpty()) {
                if (msiList.get(0).getPath() == null) {
                    inviterMerchantDOView.setPath(userSrvConfig.getMerchant_headimg());
                } else {
                    inviterMerchantDOView.setPath(msiList.get(0).getPath());
                }

            } else {
                inviterMerchantDOView.setPath(userSrvConfig.getMerchant_headimg());
            }
        }

        Page<MerchantInviterBO> pageMerchantInviter = new Page<MerchantInviterBO>();
        pageMerchantInviter.setTotalCount(count);
        List<MerchantInviterBO> memberBOS = MerchantInviterConverter.convertMerchantInviterBOS(inviterMerchantDOS);
        pageMerchantInviter.setRecords(memberBOS);
        pageMerchantInviter.setCurrentPage(pageParam.getCurrentPage());
        return pageMerchantInviter;
    }

    @Override
    public MerchantBO find(String account, String pwd) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andAccountEqualTo(account);
        List<MerchantDO> merchantDOs = merchantDOMapper.selectByExample(example);
        if (!merchantDOs.isEmpty()) {
            if (PwdUtil.verify(pwd, merchantDOs.get(0).getPwd())) {
                return MerchantConverter.convertBO(merchantDOs.get(0));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Integer setGtAndRongYunInfo(Long id, String cid) {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(id);
        merchantDO.setGtCid(cid);
        Integer row = merchantDOMapper.updateByPrimaryKeySelective(merchantDO);
        return row;
    }

    @Override
    public MerchantBO findMemberByNum(String userNum) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
        if (merchantDOS.isEmpty()) {
            return null;
        }
        return MerchantConverter.convertBO(merchantDOS.get(0));
    }

    @Override
    public MerchantBO selectMerchantInfo(Long merchantId) {
        MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(merchantId);
        MerchantStoreDOExample example = new MerchantStoreDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        List<MerchantStoreDO> list = merchantStoreDOMapper.selectByExample(example);
        MerchantBO merchantBO = MerchantConverter.convertBO(merchantDO);
        if (!list.isEmpty()) {
            MerchantStoreDO merchantStoreDO = list.get(0);
            merchantBO.setPrincipalName(merchantStoreDO.getPrincipalName());
        }
        return merchantBO;
    }

    @Override
    public List<MessagePushBO> findMessagePushList(String area) {

        List<MerchantPushView> list = merchantStoreDOMapperExtend.selectPushInfo(area);
        if (list.isEmpty()) {
            return null;
        }
        List<MessagePushBO> messagePushBOS = new ArrayList<>();
        for (MerchantPushView pushView : list) {
            MessagePushBO messagePushBO = new MessagePushBO();
            messagePushBO.setUserNum(pushView.getNum());
            messagePushBO.setGtCid(pushView.getGtCid());
            messagePushBOS.add(messagePushBO);
        }
        return messagePushBOS;
    }

    @Override
    public MessagePushBO findMessagePushByMobile(String moblie) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andMobileEqualTo(moblie).andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
        if (!merchantDOS.isEmpty()) {
            MessagePushBO messagePushBO = new MessagePushBO();
            messagePushBO.setUserNum(merchantDOS.get(0).getNum());
            return messagePushBO;
        }
        return null;
    }

    @Override
    public void updateHeadImg(String headimg, Long merchantId) {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setHeadimg(headimg);
        merchantDO.setId(merchantId);
        merchantDOMapper.updateByPrimaryKeySelective(merchantDO);
        MerchantStoreProfileDOExample example = new MerchantStoreProfileDOExample();
        example.createCriteria().andMerchantIdEqualTo(merchantId);
        MerchantDO old = merchantDOMapper.selectByPrimaryKey(merchantId);
        List<MerchantStoreProfileDO> olds = merchantStoreProfileDOMapper.selectByExample(example);
        if (!olds.isEmpty()) {
            rongUserService.refreshUserInfo(old.getNum(), olds.get(0).getCompanyName(), headimg);
        }
    }

    @Override
    public RongYunBO getRongYunInfoByNum(String num) {
        MerchantDOExample merchantDOExample = new MerchantDOExample();
        merchantDOExample.createCriteria().andNumEqualTo(num);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(merchantDOExample);
        if (merchantDOS.isEmpty()) {
            return null;
        }
        RongYunBO rongYunBO = new RongYunBO();
        rongYunBO.setUserNum(num);
        rongYunBO.setNickName("E店商家");
        rongYunBO.setHeadImg(merchantDOS.get(0).getHeadimg());

        MerchantStoreDOExample merchantStoreDOExample = new MerchantStoreDOExample();
        merchantStoreDOExample.createCriteria().andMerchantIdEqualTo(merchantDOS.get(0).getId()).andStatusNotEqualTo(MerchantStatusEnum.MERCHANT_STATUS_CANCEL.val);
        List<MerchantStoreDO> merchantStoreDOS = merchantStoreDOMapper.selectByExample(merchantStoreDOExample);
        if (!merchantStoreDOS.isEmpty()) {
            rongYunBO.setNickName(merchantStoreDOS.get(0).getName());
        }

        MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
        merchantStoreImageDOExample.createCriteria().andMerchantIdEqualTo(merchantDOS.get(0).getId()).andStatusEqualTo(true).andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val);
        List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
        if (!merchantStoreImageDOS.isEmpty() && StringUtils.isNotEmpty(merchantStoreImageDOS.get(0).getPath())) {
            rongYunBO.setHeadImg(merchantStoreImageDOS.get(0).getPath());
        }

        return rongYunBO;
    }

    @Override
    public MerchantBaseInfoBO getMerchantById(Long merchantId) {
        MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(merchantId);
        MerchantBaseInfoBO bo = new MerchantBaseInfoBO();
        if (merchantDO != null) {
            bo.setUserNum(merchantDO.getNum());
        }
        return bo;
    }

    @Override
    public MerchantBO getMerchantByNum(String num) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
        return merchantDOS.isEmpty() ? null : MerchantConverter.convertBO(merchantDOS.get(0));
    }

    @Override
    public Boolean isRegister(String account) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andAccountEqualTo(account);
        int count = merchantDOMapper.countByExample(example);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delMerchantGtPush(Long merchantId) {
        int row = merchantDOMapperExtend.delMerchantGtPush(merchantId);
        return row;
    }

    @Override
    public MerchantDOView getMerchantView(Long id) {
        return merchantDOMapperExtend.getMerchantViewById(id);
    }

    @Override
    public Integer getTotalCount() {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val);
        Integer count = merchantDOMapper.countByExample(example);
        return count;
    }

    @Override
    public Page<MerchantBO> getAccountList(AccountParam param) {
        MerchantDOExample example = new MerchantDOExample();
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        Page<MerchantBO> pages = new Page<>();
        List<MerchantBO> merchantBOS = new ArrayList<>();
        int total;
        if (StringUtils.isEmpty(param.getAccount())) {
            example.setOrderByClause("id desc");
            List<MerchantDO> merchantDOS = merchantDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (merchantDOS.isEmpty()) {
                return pages;
            }
            for (MerchantDO merchantDO : merchantDOS) {
                MerchantBO merchantBO = MerchantConverter.convertBO(merchantDO);
                merchantBOS.add(merchantBO);
            }
            total = merchantDOMapper.countByExample(example);
        } else {
            example.createCriteria().andAccountEqualTo(param.getAccount());
            List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
            if (merchantDOS.isEmpty()) {
                return pages;
            }
            MerchantBO memberBO = MerchantConverter.convertBO(merchantDOS.get(0));
            merchantBOS.add(memberBO);

            total = 1;
        }
        pages.setCurrentPage(param.getCurrentPage());
        pages.setTotalCount(total);
        pages.setRecords(merchantBOS);
        return pages;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void freezeAccount(FreezeParam param) {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setIsFreeze(param.getIsFreeze());
        merchantDO.setFreezeReason(param.getFreezeReason());
        if (param.getIsFreeze()) {
            merchantDO.setFrozenType(param.getFreezeType().getValue());
        }
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andNumEqualTo(param.getNum());
        merchantDOMapper.updateByExampleSelective(merchantDO, example);

        example = new MerchantDOExample();
        example.createCriteria().andNumEqualTo(param.getNum());
        List<MerchantDO> merchantDOList = merchantDOMapper.selectByExample(example);
        if (merchantDOList.isEmpty()) {
            return;
        }
        merchantDO = merchantDOList.get(0);
        // 根据之前的冻结操作来还原用户的账号
        userCommonService.freezeAccount(FreezeTypeEnum.getEnum(merchantDO.getFrozenType()), param.getIsFreeze(), param.getNum());
    }

    @Override
    public MerchantDetailBO getMerchantDetailById(Long merchantId) {
        MerchantDetailBO detailBO = new MerchantDetailBO();
        MerchantDO merchant = merchantDOMapper.selectByPrimaryKey(merchantId);
        detailBO.setUserNum(merchant.getNum());
        MerchantStoreDOExample storeDOExample = new MerchantStoreDOExample();
        storeDOExample.createCriteria().andMerchantIdEqualTo(merchantId);
        List<MerchantStoreDO> storeDOS = merchantStoreDOMapper.selectByExample(storeDOExample);
        if (!storeDOS.isEmpty()) {
            MerchantStoreDO merchantStoreDO = storeDOS.get(0);
            detailBO.setName(merchantStoreDO.getName());
            detailBO.setRegionName(merchantStoreDO.getRegionName());
            detailBO.setAddress(merchantStoreDO.getAddress());
            detailBO.setIndustryName(merchantStoreDO.getIndustryName());
            detailBO.setKeywords(merchantStoreDO.getKeywords());
            detailBO.setIntro(merchantStoreDO.getIntro());
            detailBO.setStatusEnum(MerchantStatusEnum.getEnum(merchantStoreDO.getStatus()));
            detailBO.setPrincipalName(merchantStoreDO.getPrincipalName());
            detailBO.setPrincipalMobile(merchantStoreDO.getPrincipalMobile());
        }

        MerchantStoreProfileDOExample profileDOExample = new MerchantStoreProfileDOExample();
        profileDOExample.createCriteria().andMerchantIdEqualTo(merchantId);
        List<MerchantStoreProfileDO> profileDOS = merchantStoreProfileDOMapper.selectByExample(profileDOExample);
        if (!profileDOS.isEmpty()) {
            MerchantStoreProfileDO profileDO = profileDOS.get(0);
            detailBO.setCompanyName(profileDO.getCompanyName());
            detailBO.setRegNumber(profileDO.getRegNumber());
            detailBO.setCompanyAddress(profileDO.getCompanyAddress());
            detailBO.setLicenseIndate(profileDO.getLicenseIndate());
            detailBO.setManageType(MerchantStoreTypeEnum.getEnum(profileDO.getManageType()));
            detailBO.setCertifType(CertifTypeEnum.getEnum(profileDO.getCertifType()));
            detailBO.setOperatorCardId(profileDO.getOperatorCardId());
            detailBO.setOperatorName(profileDO.getOperatorName());
        }

        MerchantStoreImageDOExample imageDOExample = new MerchantStoreImageDOExample();
        imageDOExample.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo(true);
        List<MerchantStoreImageDO> imageDOS = merchantStoreImageDOMapper.selectByExample(imageDOExample);
        if (!imageDOS.isEmpty()) {
            List<String> storeUrl = new ArrayList<>();
            List<String> environmentUrl = new ArrayList<>();
            List<String> idcardUrl = new ArrayList<>();
            List<String> licenseUrl = new ArrayList<>();
            List<String> otherUrl = new ArrayList<>();
            for (MerchantStoreImageDO imageDO : imageDOS) {
                if (StringUtils.isEmpty(imageDO.getPath())) {
                    continue;
                }
                if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val)) {
                    detailBO.setLogoUrl(imageDO.getPath());
                } else {
                    String[] imgArr = imageDO.getPath().split(",");
                    for (String img : imgArr) {
                        if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_STORE.val)) {
                            storeUrl.add(img);
                        } else if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_ENVIRONMENT.val)) {
                            environmentUrl.add(img);
                        } else if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_LICENSE.val)) {
                            licenseUrl.add(img);
                        } else if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_OTHER.val)) {
                            otherUrl.add(img);
                        } else if (imageDO.getType().equals(MerchantStoreImageEnum.STORE_IMAGE_IDCARD.val)) {
                            idcardUrl.add(img);
                        }
                    }
                }
            }
            detailBO.setStoreUrl(storeUrl);
            detailBO.setEnvironmentUrl(environmentUrl);
            detailBO.setIdcardUrl(idcardUrl);
            detailBO.setLicenseUrl(licenseUrl);
            detailBO.setOtherUrl(otherUrl);
        }
        return detailBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLoginLog(UserLoginLogParam loginLogParam) {
        UserLoginLogDO loginLogDO = new UserLoginLogDO();
        loginLogDO.setUserNum(loginLogParam.getUserNum());
        loginLogDO.setAccount(loginLogParam.getAccount());
        loginLogDO.setUserType(loginLogParam.getUserType());
        loginLogDO.setImei(loginLogParam.getImei());
        loginLogDO.setPlatform(loginLogParam.getPlatform());
        loginLogDO.setPlatformVer(loginLogParam.getPlatformVer());
        loginLogDO.setAppVer(loginLogParam.getAppVer());
        loginLogDO.setCityId(loginLogParam.getCityId());
        loginLogDO.setChannel(loginLogParam.getChannel());
        loginLogDO.setIpAddr(loginLogParam.getIpAddr());
        loginLogDO.setGmtCreate(new Date());
        userLoginLogDOMapper.insertSelective(loginLogDO);
    }

    @Override
    public MerchantFreezeInfoBO getMerchantFreezeInfo(Long merchantId) {
        MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(merchantId);
        if (merchantDO == null) {
            return null;
        }
        MerchantFreezeInfoBO freezeInfoBO = new MerchantFreezeInfoBO();
        freezeInfoBO.setAccount(merchantDO.getAccount());
        freezeInfoBO.setUserNum(merchantDO.getNum());
        freezeInfoBO.setFreezeReason(merchantDO.getFreezeReason());
        return freezeInfoBO;
    }


    @Override
	public MerchantBO smsLogin(String account) {
    	 MerchantDOExample example = new MerchantDOExample();
         example.createCriteria().andAccountEqualTo(account);
         List<MerchantDO> merchantDOs = merchantDOMapper.selectByExample(example);
         if(!merchantDOs.isEmpty()){
        	return MerchantConverter.convertBO(merchantDOs.get(0));
         }else{
        	return null;
         }
	}

    @Override
    public String getRichTaskInviterNum(String userNum) {
        MerchantDOExample example = new MerchantDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        List<MerchantDO> merchantDOS = merchantDOMapper.selectByExample(example);
        if (merchantDOS.isEmpty()) {
            return null;
        }

        MerchantProfileDO merchantProfileDO = merchantProfileDOMapper.selectByPrimaryKey(merchantDOS.get(0).getId());
        if (merchantProfileDO == null || merchantProfileDO.getIsHelpRichTask()) {
            return null;
        }

        if (UserInviterTypeEnum.getEnum(merchantDOS.get(0).getInviterType()) == UserInviterTypeEnum.INVITER_TYPE_MEMBER) {
            MemberDO memberDO = memberDOMapper.selectByPrimaryKey(merchantDOS.get(0).getInviterId());
            return memberDO.getNum();
        } else if (UserInviterTypeEnum.getEnum(merchantDOS.get(0).getInviterType()) == UserInviterTypeEnum.INVITER_TYPE_MERCHANT) {
            MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(merchantDOS.get(0).getInviterId());
            return merchantDO.getNum();
        }
        return null;
    }

    @Override
    public MerchantStoreBO getMerchantChatInfo(String userNum) {
        MerchantDOView doView = merchantDOMapperExtend.getMerchantChatInfo(userNum);
        if (doView == null) {
            return null;
        }
        MerchantStoreBO storeBO = new MerchantStoreBO();
        storeBO.setId(doView.getStoreId() == null ? 0L : doView.getStoreId());
        storeBO.setMerchantId(doView.getId());
        return storeBO;
    }

}
