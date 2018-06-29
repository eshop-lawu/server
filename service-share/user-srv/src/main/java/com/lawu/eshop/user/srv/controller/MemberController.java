package com.lawu.eshop.user.srv.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.UserSexEnum;
import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.constants.UserStatusEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.AdQueryMemberInfoDTO;
import com.lawu.eshop.user.dto.CashUserInfoDTO;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MemberInfoForShoppingOrderDTO;
import com.lawu.eshop.user.dto.MemberMineInfoDTO;
import com.lawu.eshop.user.dto.MessagePushDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.dto.UserRedPacketDTO;
import com.lawu.eshop.user.dto.UserSexAgeDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.eshop.user.srv.bo.CashUserInfoBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MemberProfileBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.bo.RongYunBO;
import com.lawu.eshop.user.srv.converter.LoginUserConverter;
import com.lawu.eshop.user.srv.converter.MemberConverter;
import com.lawu.eshop.user.srv.converter.MemberProfileConverter;
import com.lawu.eshop.user.srv.converter.RongYunConverter;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.eshop.user.srv.service.InviteService;
import com.lawu.eshop.user.srv.service.MemberProfileService;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.BeanUtil;
import com.lawu.utils.DateUtil;
import com.lawu.utils.PwdUtil;

/**
 * @author Leach
 * @date 2017/3/13
 */
@RestController
@RequestMapping(value = "member/")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;
    
	@Autowired
	private  MemberProfileService memberProfileService;

    @Autowired
    private FansMerchantService fansMerchantService;

    @Autowired
    private InviteService inviteService;
    
    /**
     * 根据用户名密码查找会员
     *
     * @param account
     * @param pwd
     * @return
     */
    @RequestMapping(value = "withPwd/{account}", method = RequestMethod.GET)
    public Result<LoginUserDTO> find(@PathVariable("account") String account, @RequestParam String pwd) {
        MemberBO memberBO = memberService.find(account, pwd);
        if (memberBO == null) {
            return successGet(ResultCode.MEMBER_WRONG_PWD);
        }
        if(memberBO.getIsFreeze()){
            return successGet(ResultCode.ACCOUNT_IS_FREEZE,memberBO.getFreezeReason());
        }
        if (memberBO.getStatus().byteValue() == UserStatusEnum.MEMBER_STATUS_NOVALID.val) {
            return successGet(ResultCode.ACCOUNT_IS_INVALID);
        }

        //更新最后登录时间
        memberProfileService.updateLastLoginTime(memberBO.getId());
        //更新推荐关系
        inviteService.updateInviteUserProfileInfo(memberBO.getNum(),memberBO.getId());
        return successGet(LoginUserConverter.convert(memberBO));
    }

    /**
     * 会员个人资料查询
     *
     * @param memberId 会员id
     * @return
     */
    @RequestMapping(value = "findMemberInfo/{memberId}", method = RequestMethod.GET)
    public Result<UserDTO> findMemberInfo(@PathVariable("memberId") Long memberId) {
        MemberBO memberBO = memberService.findMemberInfoById(memberId);
        if (memberBO == null) {
            return successGet();
        } else {
            return successGet(MemberConverter.convertDTO(memberBO));
        }
    }

    /**
     * 会员个人资料修改
     *
     * @param memberParam 会员信息
     * @return
     */
    @RequestMapping(value = "updateMemberInfo/{id}", method = RequestMethod.PUT)
    public Result updateMemberInfo(@RequestBody UserParam memberParam, @PathVariable("id") Long id) {
        int result = memberService.updateMemberInfo(memberParam, id);
        if (result == 1) {
            return successCreated();
        } else {
            return successCreated(ResultCode.USER_WRONG_ID);
        }
    }

    /**
     * 修改登录密码
     *
     * @param id          ID
     * @param originalPwd 原始密码
     * @param newPwd      新密码
     */
    @RequestMapping(value = "updateLoginPwd/{id}", method = RequestMethod.PUT)
    public Result updateLoginPwd(@PathVariable Long id, @RequestParam String originalPwd, @RequestParam String newPwd) {
        MemberBO memberBO = memberService.getMemberById(id);
        if (memberBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (!PwdUtil.verify(originalPwd, memberBO.getPwd()) ) {
            return successGet(ResultCode.VERIFY_PWD_FAIL);
        }
        memberService.updateLoginPwd(id, newPwd);
        return successCreated();
    }

    /**
     * 重置登录密码
     *
     * @param mobile 账号
     * @param newPwd 新密码
     */
    @RequestMapping(value = "resetLoginPwd/{mobile}", method = RequestMethod.PUT)
    public Result resetLoginPwd(@PathVariable String mobile, @RequestParam String newPwd) {
        MemberBO memberBO = memberService.getMemberByAccount(mobile);
        if (memberBO == null) {
            return successCreated(ResultCode.NOT_FOUND_DATA);
        }
        memberService.updateLoginPwd(memberBO.getId(), newPwd);
        return successCreated();
    }

    /**
     * 根据账号查询会员信息
     *
     * @param account 会员账号
     * @return
     */
    @RequestMapping(value = "getMember/{account}", method = RequestMethod.GET)
    public Result<MemberDTO> getMemberByAccount(@PathVariable String account) {
        MemberBO memberBO = memberService.getMemberByAccount(account);
        if (memberBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MemberConverter.convertMDTO(memberBO));
    }

    /**
     * 我的E友
     *
     * @param memberQuery 登录用户
     * @return
     * @author zhangrc
     * @date 2017/03/23
     */
    @RequestMapping(value = "findMemberListByUser", method = RequestMethod.POST)
    public Result<Page<EfriendDTO>> findMemberListByUser(@RequestParam Long userId, @RequestBody MemberQuery memberQuery, @RequestParam byte inviterType) {
        Page<MemberBO> pageMemberBOS = memberService.findMemberListByUser(userId, memberQuery, inviterType);
        Page<EfriendDTO> page = MemberConverter.convertPageDOTS(pageMemberBOS);
        return successGet(page);
    }

    /**
     * 会员注册
     *
     * @param registerRealParam 会员注册信息
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Result register(@RequestBody RegisterRealParam registerRealParam) {
        memberService.register(registerRealParam);
        return successCreated();
    }

    /**
     * 修改头像
     *
     * @param memberId
     * @param headimg
     * @return
     */
    @RequestMapping(value = "saveHeadImage/{memberId}", method = RequestMethod.POST)
    public Result<UserHeadImgDTO> saveHeadImage(@PathVariable("memberId") Long memberId, @RequestParam String headimg) {
        memberService.updateMemberHeadImg(headimg, memberId);
        UserHeadImgDTO userHeadImgDTO = new UserHeadImgDTO();
        userHeadImgDTO.setHeadImg(headimg);
        return successCreated(userHeadImgDTO);
    }

    /**
     * 用户、商家提现时根据用户ID获取账号、名称、省市区信息冗余到提现表中
     *
     * @param id
     * @return
     * @throws Exception
     * @author Yangqh
     */
    @RequestMapping(value = "findCashUserInfo/{id}", method = RequestMethod.GET)
    public CashUserInfoDTO findCashUserInfo(@PathVariable("id") Long id) throws Exception {
        CashUserInfoBO cashUserInfoBO = memberService.findCashUserInfo(id);
        if (cashUserInfoBO == null) {
            return null;
        }
        CashUserInfoDTO dto = new CashUserInfoDTO();
        BeanUtil.copyProperties(cashUserInfoBO, dto);
        return dto;
    }

    /**
     * 根据地区查询人数
     *
     * @param areas
     * @return
     */
    @RequestMapping(value = "findMemberCount", method = RequestMethod.POST)
    public Integer findMemberCount(@RequestBody AreasCountQuery query) {
        Integer count = memberService.findMemberCount(query);
        return count;
    }

    /**
     * 增加推送、即时通讯token
     *
     * @param id
     * @param cid
     * @return
     */
    @RequestMapping(value = "setGtAndRongYunInfo/{id}", method = RequestMethod.PUT)
    public Result setGtAndRongYunInfo(@PathVariable("id") Long id, @RequestParam("cid") String cid) {
        Integer row = memberService.setGtAndRongYunInfo(id, cid);
        if (row == null || row <= 0) {
            successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 创建商品订单需要添加用户的一些信息
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "getMemberInfoForShoppingOrder/{id}", method = RequestMethod.GET)
    public Result<MemberInfoForShoppingOrderDTO> getMemberInfoForShoppingOrder(@PathVariable("id") Long id) {
        MemberBO memberBO = memberService.getMemberById(id);
        if (memberBO == null || memberBO.getId() == null || memberBO.getId() <= 0) {
            successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successCreated(MemberConverter.convert(memberBO));
    }


    @RequestMapping(value = "findMember/{memberId}", method = RequestMethod.GET)
    public Result<MemberDTO> findMember(@PathVariable("memberId") Long memberId) {
        MemberBO memberBO = memberService.findMemberInfoById(memberId);
        if (memberBO == null) {
            return successGet();
        } else {
            return successGet(MemberConverter.convertMDTO(memberBO));
        }
    }

    /**
     * 查询所有用户userNum，cid
     *
     * @return
     */
    @RequestMapping(value = "findMessagePushList", method = RequestMethod.GET)
    public Result<List<MessagePushDTO>> findMessagePushList(@RequestParam(value = "area", required = false) String area) {
        List<MessagePushBO> list = memberService.findMessagePushList(area);
        if (list.isEmpty()) {
            return successGet(new ArrayList<MessagePushDTO>());
        }
        List<MessagePushDTO> pushDTOS = new ArrayList<>();
        for (MessagePushBO messagePushBO : list) {
            MessagePushDTO messagePushDTO = new MessagePushDTO();
            messagePushDTO.setUserNum(messagePushBO.getUserNum());
            messagePushDTO.setGtCid(messagePushBO.getGtCid());
            pushDTOS.add(messagePushDTO);
        }
        return successGet(pushDTOS);
    }

    /**
     * 根据手机号查询userNum
     *
     * @param moblie
     * @return
     */
    @RequestMapping(value = "findMessagePushByMobile", method = RequestMethod.GET)
    public MessagePushDTO findMessagePushByMobile(@RequestParam("moblie") String moblie) {
        MessagePushBO messagePushBO = memberService.findMessagePushByMobile(moblie);
        if (messagePushBO == null) {
            return null;
        }
        MessagePushDTO messagePushDTO = new MessagePushDTO();
        messagePushDTO.setUserNum(messagePushBO.getUserNum());
        return messagePushDTO;
    }
    
    /**
     * 判断用户是否注册
     * @param moblie
     * @return
     */
    @RequestMapping(value = "isRegister", method = RequestMethod.GET)
    public Result<UserRedPacketDTO> isRegister(@RequestParam("moblie") String moblie) {
    	 MemberBO memberBO = memberService.isRegister(moblie);
    	 if(memberBO==null){
    		 return null;
    	 }else{
    		 UserRedPacketDTO dto=new UserRedPacketDTO();
        	 dto.setMemberId(memberBO.getId());
        	 dto.setUserNum(memberBO.getNum());
            return successGet(dto);
    	 }
    }

    /**
     * 根据会员编号查询会员信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "getMemberByNum", method = RequestMethod.GET)
    public Result<UserDTO> getMemberByNum(@RequestParam String num) {
        MemberBO memberBO = memberService.getMemberByNum(num);
        if (memberBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(MemberConverter.convertDTO(memberBO));
    }

    /**
     * 根据会员编号查询融云需要的信息
     *
     * @param num
     * @return
     */
    @RequestMapping(value = "getRongYunInfo/{num}", method = RequestMethod.GET)
    public Result<RongYunDTO> getRongYunInfo(@PathVariable String num) {
        RongYunBO rongYunBO = memberService.getRongYunInfoByNum(num);
        if (rongYunBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(RongYunConverter.convertDTO(rongYunBO));
    }
    
    
    /**
     * 是否存在手机号
     * @param mobile
     * @return
     */
    @RequestMapping(value = "isExistsMobile", method = RequestMethod.GET)
    public Result<Boolean> isExistsMobile(@RequestParam String mobile) {
    	Boolean flag = memberService.isExistsMobile(mobile);
        return successGet(flag);
    }

    @RequestMapping(value = "delUserGtPush", method = RequestMethod.PUT)
    public Result delUserGtPush(@RequestParam Long memberId){
        memberService.delUserGtPush(memberId);
        return successCreated();
    }
    
    @RequestMapping(value = "getMemberByIds", method = RequestMethod.GET)
	public Result<List<MemberDTO>> getMemberByIds(@RequestParam("memberIds") List<Long> memberIds) {
    	 List<MemberBO>  list=memberService.getMemberByIds(memberIds);
    	 List<MemberDTO> dtoList=new ArrayList<>();
    	 for (MemberBO memberBO : list) {
    		 MemberDTO dto=new MemberDTO();
    		 dto.setId(memberBO.getId());
    		 dto.setHeadimg(memberBO.getHeadimg());
    		 dto.setMobile(memberBO.getMobile());
    		 dto.setRegionPath(memberBO.getRegionPath());
    		 dto.setNickname(memberBO.getNickname());
    		 dtoList.add(dto);
		}
		return successGet(dtoList);
	}
    
    /**
     * 返回我的页面所需要的资料
     * 
     * @param memberId
     * @return
     * @author Sunny
     * @date 2017年6月16日
     */
    @RequestMapping(value = "findMemberMineInfo/{memberId}", method = RequestMethod.GET)
    public Result<MemberMineInfoDTO> findMemberMineInfo(@PathVariable("memberId") Long memberId) {
        MemberBO memberBO = memberService.findMemberInfoById(memberId);
        if (memberBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        
        MemberProfileBO memberProfileBO = memberProfileService.get(memberId);
        
        if (memberProfileBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        
        MemberMineInfoDTO memberMineInfoDTO = MemberProfileConverter.convert(memberProfileBO, memberBO);
        return successGet(memberMineInfoDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getMemberAccountById")
    public String getMemberAccountById(@RequestParam("memberId") Long memberId){
        String account = memberService.getMemberAccountById(memberId);
        return account;
    }

    /**
     * 根据用户Num 查询用户账号及市级ID
     * @param userNum
     * @return
     */
    @RequestMapping(value = "findUserAccountAndRegionPathByNum", method = RequestMethod.GET)
    public VisitUserInfoDTO findUserAccountAndRegionPathByNum(@RequestParam("userNum") String userNum) {
        MemberBO memberBO = memberService.getMemberByNum(userNum);
        if (memberBO == null) {
            return null;
        }
        VisitUserInfoDTO userInfoDTO = new VisitUserInfoDTO();
        userInfoDTO.setAccount(memberBO.getAccount());
        if (StringUtils.isEmpty(memberBO.getRegionPath())) {
            userInfoDTO.setRegionPath("");
        } else {
            userInfoDTO.setRegionPath(memberBO.getRegionPath());
        }

        return userInfoDTO;
    }

	/**
	 * 查询用户总数
	 *
	 * @return
	 */
	@RequestMapping(value = "getTotalCount", method = RequestMethod.GET)
	public Integer getTotalCount() {
		Integer count = memberService.getTotalCount();
		return count;
	}

	/**
	 * 查询广告所需要的用户信息
	 *
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	@RequestMapping(value = "adQueryMemberInfo/{memberId}", method = RequestMethod.GET)
	public Result<AdQueryMemberInfoDTO> adQueryMemberInfo(@PathVariable Long memberId) {
		AdQueryMemberInfoDTO rtn = new AdQueryMemberInfoDTO();
		// 查询用户区域
		MemberBO memberBO = memberService.getMemberById(memberId);
		rtn.setRegionPath(memberBO.getRegionPath());
		// 查询用户的粉丝列表
		List<Long> fansList = fansMerchantService.findMerchant(memberId);
		rtn.setFansList(fansList);
		return successGet(rtn);
	}

    /**
     * 查询会员记录
     * @param param
     * @return
     */
    @RequestMapping(value = "getAccountList", method = RequestMethod.POST)
    public Result<Page<AccountDTO>> getAccountList(@RequestBody AccountParam param) {
        Page<MemberBO> memberBOPage = memberService.getAccountList(param);
        if (memberBOPage.getRecords().isEmpty()) {
            return successGet(new Page<>());
        }
        List<AccountDTO> accountDTOS = MemberConverter.convertAccountDOTS(memberBOPage.getRecords());
        Page<AccountDTO> pages = new Page<>();
        pages.setCurrentPage(memberBOPage.getCurrentPage());
        pages.setTotalCount(memberBOPage.getTotalCount());
        pages.setRecords(accountDTOS);
        return successGet(pages);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "freezeAccount", method = RequestMethod.PUT)
    public Result freezeAccount(@RequestBody @Validated FreezeParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        memberService.freezeAccount(param);
        return successCreated();
    }

    /**
     * 保存会员登录日志
     *
     * @param loginLogParam
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "saveLoginLog", method = RequestMethod.POST)
    public Result saveLoginLog(@RequestBody UserLoginLogParam loginLogParam) {
        memberService.saveLoginLog(loginLogParam);
        return successCreated();
    }
    
    
    /**
     * 短信登录
     * @param account
     * @return
     */
    @RequestMapping(value = "smsLogin/{account}", method = RequestMethod.GET)
    public Result<LoginUserDTO> smsLogin(@PathVariable("account") String account){
    	 MemberBO memberBO = memberService.smsLogin(account);
         if (memberBO == null) {
             return successGet(ResultCode.MEMBER_WRONG_PWD);
         }
         if(memberBO.getIsFreeze()){
             return successGet(ResultCode.ACCOUNT_IS_FREEZE,memberBO.getFreezeReason());
         }
         if (memberBO.getStatus().byteValue() == UserStatusEnum.MEMBER_STATUS_NOVALID.val) {
             return successGet(ResultCode.ACCOUNT_IS_INVALID);
         }
         //更新最后登录时间
         memberProfileService.updateLastLoginTime(memberBO.getId());
         //更新推荐关系
         inviteService.updateInviteUserProfileInfo(memberBO.getNum(),memberBO.getId());
         return successGet(LoginUserConverter.convert(memberBO));
    }

    /**
     * 设置登陆密码
     * @param userNum
     * @param newPwd
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "setLoginPwd", method = RequestMethod.PUT)
    public Result setLoginPwd(@RequestParam("userNum") String userNum, @RequestParam("newPwd") String newPwd) {
        memberService.setLoginPwd(userNum,newPwd);
        return successCreated();
    }

    /**
     * 是否完善资料
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "isFinishInformation", method = RequestMethod.GET)
    public Result<Boolean> isFinishInformation(@RequestParam String userNum) {
        Boolean result = memberService.isFinishInformation(userNum);
        return successGet(result);
    }
    
    
    /**
     * 通过用户编号集合<p>
     * 查找头脑PK所需要的用户信息
     * 
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "findUserInfoForGameMind", method = RequestMethod.GET)
    public Result<List<GameUserInfoDTO>> findUserInfoForGameMind(@RequestParam List<String> userNums) {
        /*
         *  考虑userNums中会存在重复的用户编号
         *  要去除重复的用户编号
         *  要保证参数的传入顺序
         */
        List<String> userNumList = new ArrayList<>();
        for (String item : userNums) {
            if (!userNumList.contains(item)) {
                userNumList.add(item);
            }
        }
        List<MemberBO> memberBOs = memberService.findUserInfoForGameMind(userNumList);
        if (memberBOs == null || memberBOs.isEmpty() || memberBOs.size() != userNumList.size()) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        List<GameUserInfoDTO> model = new ArrayList<>();
        for (MemberBO item : memberBOs) {
            GameUserInfoDTO gameUserInfoDTO = new GameUserInfoDTO();
            gameUserInfoDTO.setHeadImg(item.getHeadimg());
            gameUserInfoDTO.setNickName(item.getNickname());
            gameUserInfoDTO.setRegionPath(item.getRegionPath());
            gameUserInfoDTO.setRegionName(item.getRegionName());
            gameUserInfoDTO.setUserNum(item.getNum());
            model.add(gameUserInfoDTO);
        }
        return successGet(model);
    }

    /**
     * 获取完成E友邀请人的编号
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getRichTaskInviterNum", method = RequestMethod.GET)
    public Result<String> getRichTaskInviterNum(@RequestParam String userNum) {
        String inviterNum = memberService.getRichTaskInviterNum(userNum);
        return successGet(inviterNum);
    }

    /**
     * 查询用户性别年龄信息
     *
     * @param memberId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getMemberSexAge/{memberId}", method = RequestMethod.GET)
    public Result<UserSexAgeDTO> getMemberSexAge(@PathVariable("memberId") Long memberId) {
        MemberBO memberBO = memberService.findMemberInfoById(memberId);
        UserSexAgeDTO dto = new UserSexAgeDTO();
        if (memberBO == null) {
            dto.setAge(0);
            dto.setSexEnum(UserSexEnum.SEX_SECRET);
            return successGet(dto);
        }
        if (memberBO.getBirthday() == null) {
            dto.setAge(1);
        } else {
            int age = DateUtil.getNowYear() - DateUtil.getFieldValue(memberBO.getBirthday(), Calendar.YEAR);
            dto.setAge(age > 0 ? age : 1);
        }
        dto.setSexEnum(UserSexEnum.getEnum(memberBO.getUserSex().val));
        return successGet(dto);
    }

}
