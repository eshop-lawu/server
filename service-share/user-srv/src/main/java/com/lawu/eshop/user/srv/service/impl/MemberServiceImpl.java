package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.user.FreezeAccountNotification;
import com.lawu.eshop.mq.dto.user.RegHelpRedpacktUpdateHeadimgNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.constants.UserInviterTypeEnum;
import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.constants.UserStatusEnum;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.converter.MemberConverter;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDO;
import com.lawu.eshop.user.srv.domain.InviteRelationDOExample;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;
import com.lawu.eshop.user.srv.domain.UserLoginLogDO;
import com.lawu.eshop.user.srv.domain.extend.AreasCountView;
import com.lawu.eshop.user.srv.domain.extend.InviterUserDOView;
import com.lawu.eshop.user.srv.domain.extend.MemberDOView;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.InviteRelationDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.UserLoginLogDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.MemberDOMapperExtend;
import com.lawu.eshop.user.srv.rong.models.TokenResult;
import com.lawu.eshop.user.srv.rong.service.RongUserService;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.UserCommonService;
import com.lawu.framework.core.page.Page;
import com.lawu.mq.message.MessageProducerService;
import com.lawu.utils.PwdUtil;

/**
 * 会员信息服务实现
 *
 * @author Leach
 * @date 2017/3/13
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDOMapper memberDOMapper;

    @Autowired
    private InviteRelationDOMapper inviteRelationDOMapper;

    @Autowired
    private MemberProfileDOMapper memberProfileDOMapper;

    @SuppressWarnings("rawtypes")
    @Autowired
    @Qualifier("memberRegTransactionMainServiceImpl")
    private TransactionMainService transactionMainService;

    @Autowired
    private FansMerchantDOMapper fansMerchantDOMapper;

    @Autowired
    private RongUserService rongUserService;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Autowired
    private MerchantProfileDOMapper merchantProfileDOMapper;

    @Autowired
    private MemberDOMapperExtend memberDOMapperExtend;

    @Autowired
    private UserLoginLogDOMapper userLoginLogDOMapper;

    @SuppressWarnings("rawtypes")
    @Autowired
    @Qualifier("memberRegHelpRedpacketTransactionMainServiceImpl")
    private TransactionMainService memberRegHelpRedpacketTransactionMainServiceImpl;

//    @Autowired
//    @Qualifier("memberHelpRegUpdateHeadimgTransactionMainServiceImpl")
//    private TransactionMainService memberHelpRegUpdateHeadimgTransactionMainServiceImpl;

    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private UserCommonService userCommonService;

    @Autowired
    private MerchantDOMapper merchantDOMapper;

    @Override
    public MemberBO find(String account, String pwd) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andAccountEqualTo(account);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if(!memberDOS.isEmpty()){
        	if(StringUtils.isEmpty(memberDOS.get(0).getPwd())){
        		return null;
        	}else if(PwdUtil.verify(pwd, memberDOS.get(0).getPwd())){
        		return MemberConverter.convertBO(memberDOS.get(0));
        	}else{
        		return null;
        	}
        }else{
        	return null;
        }
    }

    @Override
    public MemberBO findMemberInfoById(Long id) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(id);
        return MemberConverter.convertBO(memberDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMemberInfo(UserParam memberParam, Long id) {

        MemberDO memberDO = MemberConverter.convertDOOther(memberParam);
        memberDO.setId(id);
        int result = memberDOMapper.updateByPrimaryKeySelective(memberDO);
        if (StringUtils.isNotEmpty(memberDO.getNickname())) {
            MemberDO old = memberDOMapper.selectByPrimaryKey(id);
            String headImg = userSrvConfig.getDefaultHeadimg();
            if (StringUtils.isNotEmpty(old.getHeadimg())) {
                headImg = old.getHeadimg();
            }
            rongUserService.refreshUserInfo(old.getNum(), memberDO.getNickname(), headImg);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLoginPwd(Long id, String newPwd) {
        MemberDO memberDO = new MemberDO();
        memberDO.setId(id);
        memberDO.setPwd(PwdUtil.generate(newPwd));
        memberDOMapper.updateByPrimaryKeySelective(memberDO);
    }

    @Override
    public MemberBO getMemberByAccount(String account) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andAccountEqualTo(account);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        return memberDOS.isEmpty() ? null : MemberConverter.convertBO(memberDOS.get(0));
    }
    
    @Override
    public Page<MemberBO> findMemberListByUser(Long inviterId, MemberQuery memberQuery, byte inviterType) {
    	int count=0;
    	if(inviterType==UserTypeEnum.MEMBER.getValue()){
    		MemberProfileDO memberProfileDO = memberProfileDOMapper.selectByPrimaryKey(inviterId);
        	count = memberProfileDO == null ? 0 : memberProfileDO.getInviteMemberCount();
    	}else{
    		 MerchantProfileDO  merchantProfileDO=merchantProfileDOMapper.selectByPrimaryKey(inviterId);
    		 count = merchantProfileDO == null ? 0 : merchantProfileDO.getInviteMemberCount();
    	}

    	InviterUserDOView view = new InviterUserDOView();
    	view.setInviterId(inviterId);
    	view.setInviterType(inviterType);
    	view.setStatus(UserStatusEnum.MEMBER_STATUS_VALID.val);
    	if (memberQuery.getAccountOrNickName() != null && !"".equals(memberQuery.getAccountOrNickName().trim())) {
           view.setContent("%" + memberQuery.getAccountOrNickName()+ "%");
        }
    	view.setOffset(memberQuery.getOffset());
    	view.setLimit(memberQuery.getPageSize());
    	List<MemberDO> memberDOS = memberDOMapperExtend.selectByExampleWithRowbounds(view);
        List<MemberProfileDO> mpList = new ArrayList<MemberProfileDO>();
        for (MemberDO memberDO : memberDOS) {
        	if(memberDO.getHeadimg()==null){
        		memberDO.setHeadimg(userSrvConfig.getDefaultHeadimg());
        	}
        	MemberProfileDO memberProfileDO = memberProfileDOMapper.selectByPrimaryKey(memberDO.getId());
            if (memberProfileDO != null)
                mpList.add(memberProfileDO);
        }
        Page<MemberBO> pageMember = new Page<MemberBO>();
        pageMember.setTotalCount(count);
        List<MemberBO> memberBOS = MemberConverter.convertListBOS(memberDOS, mpList);
        pageMember.setRecords(memberBOS);
        pageMember.setCurrentPage(memberQuery.getCurrentPage());
        return pageMember;
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

        //插入会员信息
        String userNum = IdWorkerHelperImpl.generate(BizIdType.MEMBER);
        MemberDO memberDO = new MemberDO();
        memberDO.setNum(userNum);
        memberDO.setAccount(registerRealParam.getAccount());
        if(registerRealParam.getPwd() !=null){
        	memberDO.setPwd(PwdUtil.generate(registerRealParam.getPwd()));
        }
        memberDO.setMobile(registerRealParam.getAccount());
        memberDO.setSex(UserSexEnum.SEX_SECRET.val);
        memberDO.setStatus(UserStatusEnum.MEMBER_STATUS_VALID.val);
        memberDO.setHeadimg(userSrvConfig.getUser_headimg());
        memberDO.setInviterId(inviterId);
        memberDO.setInviterType(inviterType);
        memberDO.setLevel(UserCommonConstant.LEVEL_1);
        memberDO.setRegOrigin(registerRealParam.getRegOrigin());
        memberDO.setGmtCreate(new Date());
        memberDO.setRegOrigin(registerRealParam.getRegOrigin());
        memberDOMapper.insertSelective(memberDO);
        long memberId = memberDO.getId();

        //插入会员扩展信息
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(memberId);
        memberProfileDO.setGmtCreate(new Date());
        memberProfileDO.setRegIp(registerRealParam.getRegIp());
        memberProfileDO.setRegAppVer(registerRealParam.getRegAppVer());
        memberProfileDO.setRegPlatformVer(registerRealParam.getRegPlatformVer());
        memberProfileDOMapper.insertSelective(memberProfileDO);

        //注册会员成为商户粉丝
        if (inviterId > 0 && inviterType == UserInviterTypeEnum.INVITER_TYPE_MERCHANT.val) {
            FansMerchantDO fansMerchantDO = new FansMerchantDO();
            fansMerchantDO.setMerchantId(inviterId);
            fansMerchantDO.setMemberId(memberId);
            fansMerchantDO.setChannel(FansMerchantChannelEnum.REGISTER.getValue());
            fansMerchantDO.setGmtCreate(new Date());
            fansMerchantDOMapper.insertSelective(fansMerchantDO);
        }

        //插入会员推荐关系
        InviteRelationDO inviteRelationDO = new InviteRelationDO();
        inviteRelationDO.setUserNum(userNum);
        inviteRelationDO.setInviteUserNum(userNum);
        inviteRelationDO.setDepth(0);
        inviteRelationDO.setStatus(StatusEnum.VALID.getValue());
        inviteRelationDO.setIsDel(false);
        inviteRelationDO.setType(UserInviterTypeEnum.INVITER_TYPE_MEMBER.val);
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
                    inviteRelationDO.setType(inviteRelationDO1.getType());
                    inviteRelationDO.setStatus(StatusEnum.INVALID.getValue());
                    inviteRelationDO.setIsDel(false);
                    inviteRelationDOMapper.insert(inviteRelationDO);
                }
            }


        }
        //获取ryToken
        TokenResult tokenResult = rongUserService.getRongToken(userNum, "E店会员", userSrvConfig.getUser_headimg());
        if (tokenResult != null && StringUtils.isNotEmpty(tokenResult.getToken())) {
            MemberDO memberDO2 = new MemberDO();
            memberDO2.setRyToken(tokenResult.getToken());
            memberDO2.setId(memberId);
            memberDOMapper.updateByPrimaryKeySelective(memberDO2);
        }
        transactionMainService.sendNotice(memberId);

        //助力注册
        if(registerRealParam.isHelp()){
            memberRegHelpRedpacketTransactionMainServiceImpl.sendNotice(memberId);
        }
    }

    @Override
    public MemberBO getMemberById(Long id) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(id);
        return MemberConverter.convertBO(memberDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMemberHeadImg(String headimg, Long mermberId) {
        MemberDO memberDO = new MemberDO();
        memberDO.setHeadimg(headimg);
        memberDO.setId(mermberId);
        memberDOMapper.updateByPrimaryKeySelective(memberDO);
        MemberDO old = memberDOMapper.selectByPrimaryKey(mermberId);
        if(old != null){
            rongUserService.refreshUserInfo(old.getNum(), memberDO.getNickname(), headimg);
        }

//        memberHelpRegUpdateHeadimgTransactionMainServiceImpl.sendNotice(mermberId);

        RegHelpRedpacktUpdateHeadimgNotification regNotification = new RegHelpRedpacktUpdateHeadimgNotification();
        regNotification.setHelpUserNum(old.getNum());
        regNotification.setHelpUserHeadimg(headimg);
        messageProducerService.sendMessage(MqConstant.TOPIC_USER_SRV,MqConstant.TAG_REG_HELP_RED_PACKET_REFLASH_HEADIMG,regNotification);

    }

    public CashUserInfoBO findCashUserInfo(Long id) {
        MemberDO mdo = memberDOMapper.selectByPrimaryKey(id);
        if (mdo == null) {
            return null;
        } 
        if (mdo.getRegionPath() == null || mdo.getRegionPath().split("/").length != 3) {
            return null;
        }
        CashUserInfoBO cashUserInfoBO = new CashUserInfoBO();
        cashUserInfoBO.setName(mdo.getName());
        cashUserInfoBO.setRegionFullName(mdo.getRegionName());
        cashUserInfoBO.setProvinceId(Integer.valueOf(mdo.getRegionPath().split("/")[0]));
        cashUserInfoBO.setCityId(Integer.valueOf(mdo.getRegionPath().split("/")[1]));
        cashUserInfoBO.setAreaId(Integer.valueOf(mdo.getRegionPath().split("/")[2]));
        return cashUserInfoBO;
    }

    public Integer findMemberCount(AreasCountQuery query) {
    	AreasCountView view = new AreasCountView();
    	view.setMinAge(query.getMinAge());
    	view.setMaxAge(query.getMaxAge());
    	if(StringUtils.isNotBlank(query.getAreas())){
    		view.setAreas(Arrays.asList(StringUtils.split(query.getAreas(), ",")));
    	}
    	if(query.getSexEnum() != null){
    		view.setSex(query.getSexEnum().val);
    	}
    	Integer count = memberDOMapperExtend.findMemberCount(view);
        return count;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer setGtAndRongYunInfo(Long id, String cid) {
        MemberDO memberDO = new MemberDO();
        memberDO.setId(id);
        memberDO.setGtCid(cid);
        Integer row = memberDOMapper.updateByPrimaryKeySelective(memberDO);
        return row;
    }

    @Override
    public MemberBO findMemberByNum(String userNum) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (memberDOS.isEmpty()) {
            return null;
        }
        return MemberConverter.convertBO(memberDOS.get(0));
    }

    @Override
    public List<MessagePushBO> findMessagePushList(String area) {
        MemberDOExample example = new MemberDOExample();
        if ("all".equals(area)) {
            example.createCriteria().andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val).andGtCidIsNotNull();
        } else {
            example.createCriteria().andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val).andGtCidIsNotNull().andRegionPathLike(area + "%");
        }
        example.setOrderByClause("id desc");
        List<MemberDO> list = memberDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<MessagePushBO> messagePushBOS = new ArrayList<>();
        for (MemberDO memberDO : list) {
            MessagePushBO messagePushBO = new MessagePushBO();
            messagePushBO.setUserNum(memberDO.getNum());
            messagePushBO.setGtCid(memberDO.getGtCid());
            messagePushBOS.add(messagePushBO);
        }
        return messagePushBOS;
    }

    @Override
    public MessagePushBO findMessagePushByMobile(String moblie) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andMobileEqualTo(moblie).andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (!memberDOS.isEmpty()) {
            MessagePushBO messagePushBO = new MessagePushBO();
            messagePushBO.setUserNum(memberDOS.get(0).getNum());
            return messagePushBO;
        }
        return null;
    }

    @Override
    public MemberBO isRegister(String mobile) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andMobileEqualTo(mobile).andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (!memberDOS.isEmpty()) {
        	MemberBO memberBO=new MemberBO();
        	memberBO.setId(memberDOS.get(0).getId());
        	memberBO.setNum(memberDOS.get(0).getNum());
            return memberBO;
        } else {
            return null;
        }

    }

    @Override
    public MemberBO getMemberByNum(String num) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (memberDOS.isEmpty()) {
            return null;
        }
        return MemberConverter.convertBO(memberDOS.get(0));
    }

    @Override
    public RongYunBO getRongYunInfoByNum(String num) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(num);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (memberDOS.isEmpty()) {
            return null;
        }
        RongYunBO rongYunBO = new RongYunBO();
        rongYunBO.setUserNum(num);
        rongYunBO.setNickName(StringUtils.isEmpty(memberDOS.get(0).getNickname()) ? "E店会员" : memberDOS.get(0).getNickname());
        rongYunBO.setHeadImg(memberDOS.get(0).getHeadimg());
        return rongYunBO;
    }

	@Override
	public Boolean isExistsMobile(String mobile) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andAccountEqualTo(mobile);
        int count = memberDOMapper.countByExample(example);
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delUserGtPush(Long memberId) {
       int rows =  memberDOMapperExtend.delUserGtPush(memberId);
       return rows;
    }

	@Override
	public List<MemberBO> getMemberByIds(List<Long> memberIds) {
		List<MemberDOView> listView=memberDOMapperExtend.getMemberByIds(memberIds);
		List<MemberBO> listBO=new ArrayList<>();
		for (MemberDOView memberDOView : listView) {
			MemberBO bo=new MemberBO();
			bo.setId(memberDOView.getId());
			bo.setMobile(memberDOView.getMobile());
			bo.setHeadimg(memberDOView.getHeadimg());
			bo.setRegionPath(memberDOView.getRegionPath());
			bo.setNickname(memberDOView.getNickname());
			listBO.add(bo);
		}
		return listBO;
	}

    @Override
    public String getMemberAccountById(Long memberId) {
        MemberDO memberDO = memberDOMapper.selectByPrimaryKey(memberId);
        return memberDO == null ? "" : memberDO.getAccount();
    }

    @Override
    public Integer getTotalCount() {
        MemberDOExample example = new MemberDOExample();
         example.createCriteria().andStatusEqualTo(UserStatusEnum.MEMBER_STATUS_VALID.val);
        Integer count = memberDOMapper.countByExample(example);
        return count;
    }

    @Override
    public Page<MemberBO> getAccountList(AccountParam param) {
        MemberDOExample example = new MemberDOExample();
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        Page<MemberBO> pages = new Page<>();
        List<MemberBO> memberBOS = new ArrayList<>();
        int total ;
        if (StringUtils.isEmpty(param.getAccount())) {
            example.setOrderByClause("id desc");
            List<MemberDO> memberDOS = memberDOMapper.selectByExampleWithRowbounds(example, rowBounds);
            if (memberDOS.isEmpty()) {
                return pages;
            }
            for (MemberDO memberDO : memberDOS) {
                MemberBO memberBO = MemberConverter.convertBO(memberDO);
                memberBOS.add(memberBO);
            }
            total = memberDOMapper.countByExample(example);
        } else {
            example.createCriteria().andAccountEqualTo(param.getAccount());
            List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
            if (memberDOS.isEmpty()) {
                return pages;
            }
            MemberBO memberBO = MemberConverter.convertBO(memberDOS.get(0));
            memberBOS.add(memberBO);

            total = 1;
        }
        pages.setCurrentPage(param.getCurrentPage());
        pages.setTotalCount(total);
        pages.setRecords(memberBOS);
        return pages;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void freezeAccount(FreezeParam param) {
        MemberDO memberDO = new MemberDO();
        memberDO.setIsFreeze(param.getIsFreeze());
        memberDO.setFreezeReason(param.getFreezeReason());
        if (param.getIsFreeze()) {
            memberDO.setFrozenType(param.getFreezeType().getValue());
        }
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(param.getNum());
        memberDOMapper.updateByExampleSelective(memberDO, example);

        example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(param.getNum());
        List<MemberDO> memberDOList = memberDOMapper.selectByExample(example);
        if (memberDOList.isEmpty()) {
            return;
        }
        memberDO = memberDOList.get(0);
        // 根据之前的冻结操作来还原用户的账号
        userCommonService.freezeAccount(FreezeTypeEnum.getEnum(memberDO.getFrozenType()), param.getIsFreeze(), param.getNum());
        
        if (FreezeTypeEnum.ROBOT_REGISTRATION.equals(FreezeTypeEnum.getEnum(memberDO.getFrozenType()))) {
            // 设置活动参与记录是否有效
            FreezeAccountNotification freezeAccountNotification = new FreezeAccountNotification();
            freezeAccountNotification.setIsFreeze(param.getIsFreeze());
            freezeAccountNotification.setUserNum(param.getNum());
            messageProducerService.sendMessage(MqConstant.TOPIC_USER_SRV, MqConstant.TAG_FREEZE_USER_ACCOUNT, freezeAccountNotification);
        }
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
    @Transactional(rollbackFor = Exception.class)
    public void updateGradeInfoByUserNum(String userNum, Integer resultMoney, Byte userCurrentGrade) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        MemberDO memberDO = new MemberDO();
        memberDO.setGrade(userCurrentGrade);
        memberDO.setGrowthValue(resultMoney);
        memberDO.setGmtModified(new Date());
        memberDOMapper.updateByExampleSelective(memberDO,example);
    }

	@Override
	public MemberBO smsLogin(String account) {
		 MemberDOExample example = new MemberDOExample();
	     example.createCriteria().andAccountEqualTo(account);
	     List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
         if(!memberDOS.isEmpty()){
        	return MemberConverter.convertBO(memberDOS.get(0));
         }else{
        	return null;
         }
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setLoginPwd(String userNum, String newPwd) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        MemberDO memberDO = new MemberDO();
        memberDO.setPwd(PwdUtil.generate(newPwd));
        memberDO.setGmtModified(new Date());
        return memberDOMapper.updateByExampleSelective(memberDO,example);
    }

    @Override
    public Boolean isFinishInformation(String userNum) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (memberDOS.isEmpty()) {
            return false;
        } else if (memberDOS.get(0).getBirthday() == null || StringUtils.isEmpty(memberDOS.get(0).getRegionPath())) {
            return false;
        }
        return true;
    }

    @Override
    public List<MemberBO> findUserInfoForGameMind(List<String> userNums) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumIn(userNums);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        List<MemberBO> rtn = new ArrayList<>();
        for (MemberDO item : memberDOS) {
            rtn.add(MemberConverter.convertBO(item));
        }
        return rtn;
    }

    @Override
    public String getRichTaskInviterNum(String userNum) {
        MemberDOExample example = new MemberDOExample();
        example.createCriteria().andNumEqualTo(userNum);
        List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
        if (memberDOS.isEmpty()) {
            return null;
        }

        MemberProfileDO memberProfileDO = memberProfileDOMapper.selectByPrimaryKey(memberDOS.get(0).getId());
        if (memberProfileDO == null || memberProfileDO.getIsHelpRichTask()) {
            return null;
        }

        if (UserInviterTypeEnum.getEnum(memberDOS.get(0).getInviterType()) == UserInviterTypeEnum.INVITER_TYPE_MEMBER) {
            MemberDO memberDO = memberDOMapper.selectByPrimaryKey(memberDOS.get(0).getInviterId());
            return memberDO.getNum();
        } else if (UserInviterTypeEnum.getEnum(memberDOS.get(0).getInviterType()) == UserInviterTypeEnum.INVITER_TYPE_MERCHANT) {
            MerchantDO merchantDO = merchantDOMapper.selectByPrimaryKey(memberDOS.get(0).getInviterId());
            return merchantDO.getNum();
        }
        return null;
    }

}
