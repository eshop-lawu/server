package com.lawu.eshop.member.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.param.DoHelpDataParam;
import com.lawu.eshop.activity.param.DoHelpParam;
import com.lawu.eshop.ad.dto.GetInviterBountyDTO;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.UserNameEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.HelpRedpacketInviteService;
import com.lawu.eshop.member.api.service.InviterBountyService;
import com.lawu.eshop.member.api.service.InviterService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantService;
import com.lawu.eshop.member.api.service.MessageService;
import com.lawu.eshop.member.api.service.VerifyCodeService;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.param.RegisterHelpParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.framework.web.util.HeaderUtil;
import com.lawu.utils.DateUtil;
import com.lawu.utils.IpUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 红包助力
 *
 * @author yangqh
 * @date 2017/12/28
 */
@Api(tags = "helpRedpacketInvite", value = "分享助力")
@RestController
@RequestMapping(value = "helpRedpacketInvite/")
public class HelpRedpacketInviteController extends BaseController {

    @Autowired
    private HelpRedpacketInviteService helpRedpacketInviteService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private InviterService inviterService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private MemberApiConfig memberApiConfig;

    @Autowired
    private InviterBountyService inviterBountyService;

    @Autowired
    private MessageService messageService;

    /**
     * 助力页面登陆确认帮忙
     *
     * @param param
     * @return
     */
    @Audit(date = "2018-01-02", reviewer = "孙林青")
    @ApiOperation(value = "确认帮忙", notes = "第一步输入账号密码确认帮忙接口[]（杨清华）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "doLoginHelp", method = RequestMethod.POST)
    public Result doLoginHelp(@ModelAttribute @Valid DoHelpParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        Result<MemberDTO> memberResult = memberService.getMemberByAccount(param.getAccout());
        
		if (memberResult.getModel() == null ) {
			return successCreated(ResultCode.NOT_FOUND_DATA);
		}
		
		if (memberResult.getModel().getFreeze()) {
			return successCreated(ResultCode.ACCOUNT_IS_FREEZE);
		}

        Result<HelpRedpacketActivityDTO> activityResult = null;
        if(StringUtils.isBlank(param.getRegOrigin())){
            activityResult = helpRedpacketInviteService.getHelpRedpacketActivitydetail();
        } else {
            activityResult = helpRedpacketInviteService.getHelpRedpacketActivitydetail(Integer.valueOf(param.getRegOrigin()));
        }
        Date regStartTime = activityResult.getModel().getRegStartTime();
        Date regEndTime = activityResult.getModel().getRegEndTime();

        boolean isvalid1 = DateUtil.belongCalendar(new Date(), regStartTime, regEndTime);
        if (!isvalid1) {
            //助力结束
            return successCreated(ResultCode.HELP_REG_END);
        }

        //判断用户是否存在
        if (memberResult.getRet() == ResultCode.RESOURCE_NOT_FOUND) {
            return successCreated(ResultCode.HELP_MEMBER_IS_EMPTY);
        }

        Date gmtCreate = memberResult.getModel().getGmtCreate();
        boolean isvalid = DateUtil.belongCalendar(gmtCreate, regStartTime, regEndTime);
        if (!isvalid) {
            //活动期间外注册
            return successCreated(ResultCode.HELP_REG_OUT_TIME);
        }

        //登陆
        Result<LoginUserDTO> loginResult = memberService.find(param.getAccout(), param.getPassword());
        if (!isSuccess(loginResult)) {
            return successCreated(loginResult.getRet());
        }

        //助力
        DoHelpDataParam dparam = new DoHelpDataParam();
        dparam.setAttendId(param.getAttendId());
        dparam.setAccout(param.getAccout());
        dparam.setHeadimg(memberResult.getModel().getHeadimg());
        dparam.setUserNum(memberResult.getModel().getNum());
        return helpRedpacketInviteService.doLoginHelp(dparam);
    }

    @Audit(date = "2018-01-02", reviewer = "孙林青")
    @ApiOperation(value = "助力注册", notes = "助力注册。[] (杨清华)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "register/{verifyCodeId}", method = RequestMethod.POST)
    public Result register(@PathVariable @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
                           @ModelAttribute @ApiParam @Valid RegisterHelpParam registerParam, BindingResult errorResult) {
        String message = validate(errorResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        HttpServletRequest request = getRequest();
        Result<HelpRedpacketActivityDTO> activityResult = null;
        if(StringUtils.isBlank(registerParam.getRegOrigin())){
            activityResult = helpRedpacketInviteService.getHelpRedpacketActivitydetail();
        } else {
            activityResult = helpRedpacketInviteService.getHelpRedpacketActivitydetail(Integer.valueOf(registerParam.getRegOrigin()));
        }

        Date regStartTime = activityResult.getModel().getRegStartTime();
        Date regEndTime = activityResult.getModel().getRegEndTime();
        boolean isvalid = DateUtil.belongCalendar(new Date(), regStartTime, regEndTime);
        if (!isvalid) {
            //助力结束
            return successCreated(ResultCode.HELP_REG_END);
        }

        RegisterRealParam registerRealParam = new RegisterRealParam();
        String num = null, userName = null;
        if (StringUtils.isNotEmpty(registerParam.getInviterAccount())) {
            if (registerParam.getUserTypeEnum() != null) {
                if (registerParam.getUserTypeEnum().getValue().equals(UserTypeEnum.MEMBER.getValue())) {
                    Result<MemberDTO> memberDTOResult = memberService.getMemberByAccount(registerParam.getInviterAccount());
                    if (isSuccess(memberDTOResult)) {
                        if (memberDTOResult.getModel().getFreeze()) {
                            return successCreated(ResultCode.WRONG_OPERATION, "邀请人账号异常");
                        }
                        num = memberDTOResult.getModel().getNum();
                        userName = UserNameEnum.MEMBER.getName();
                        registerRealParam.setInviterId(memberDTOResult.getModel().getId());
                        registerRealParam.setUserNum(num);
                    }
                } else {
                    Result<MerchantDTO> merchantDTOResult = merchantService.getMerchantByAccount(registerParam.getInviterAccount());
                    if (isSuccess(merchantDTOResult)) {
                        if (merchantDTOResult.getModel().getIsFreeze()) {
                            return successCreated(ResultCode.WRONG_OPERATION, "邀请人账号异常");
                        }
                        num = merchantDTOResult.getModel().getNum();
                        userName = UserNameEnum.MERCHANT.getName();
                        registerRealParam.setInviterId(merchantDTOResult.getModel().getId());
                        registerRealParam.setUserNum(num);
                    }
                }
            } else {
                Result<InviterDTO> inviterResult = inviterService.getInviterByAccount(registerParam.getInviterAccount());
                if (!isSuccess(inviterResult)) {
                    return successGet(ResultCode.INVITER_NO_EXIST);
                }
                InviterDTO inviterDTO = inviterResult.getModel();
                if (inviterDTO.getFreeze()) {
                    return successCreated(ResultCode.WRONG_OPERATION, "邀请人账号已经被冻结");
                }
                registerRealParam.setInviterId(inviterDTO.getInviterId());
                num = inviterDTO.getUserNum();
                userName = UserNameEnum.MERCHANT.getName();
                registerRealParam.setUserNum(num);
            }
        }

        Result accountResult = memberService.getMemberByAccount(registerParam.getAccount());
        if (isSuccess(accountResult)) {
            return successGet(ResultCode.ACCOUNT_EXIST);
        }
        Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, registerParam.getSmsCode());
        if (!isSuccess(smsResult)) {
            return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
        }
        VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
        if (!registerParam.getAccount().equals(verifyCodeDTO.getMobile())) {
            return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
        }
        if (DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), memberApiConfig.getSmsValidMinutes())) {
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }
        registerRealParam.setAccount(registerParam.getAccount());
        registerRealParam.setPwd(registerParam.getPwd());
        registerRealParam.setHelp(true);
        registerRealParam.setRegOrigin(registerParam.getRegOrigin());
        registerRealParam.setRegPlatformVer(HeaderUtil.getRequestPlatformVersion(request));
        registerRealParam.setRegAppVer(HeaderUtil.getRequestAppVersion(request));
        registerRealParam.setRegIp(IpUtil.getIpAddress(request));
        Result regResult = memberService.register(registerRealParam);
        
        if (isSuccess(regResult)) {
            if (StringUtils.isNotEmpty(registerParam.getInviterAccount())) {
                //如果邀请人不为空，发送奖励金给邀请人
            	Result<MemberDTO> userResult = memberService.getMemberByAccount(registerParam.getAccount());
                Result<GetInviterBountyDTO> result = inviterBountyService.getInviterBounty(num,userResult.getModel().getNum(),UserTypeEnum.MEMBER);
                if (result.getRet() != 1100) { //奖励金启用发送消息和推送
                    MessageInfoParam messageInfoParam = new MessageInfoParam();
                    MessageTempParam messageTempParam = new MessageTempParam();
                    messageTempParam.setUserName(userName);
                    messageTempParam.setInviteBounty(result.getModel().getMoney());
                    messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITER_BOUNTY);
                    messageInfoParam.setMessageParam(messageTempParam);
                    messageService.saveMessage(num, messageInfoParam);
                }
            }
        }
        return regResult;
    }

}
