package com.lawu.eshop.merchant.api.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.manager.TokenManager;
import com.lawu.authorization.util.UserUtil;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.common.constants.UserNameEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import com.lawu.eshop.merchant.api.service.AdService;
import com.lawu.eshop.merchant.api.service.InviterBountyService;
import com.lawu.eshop.merchant.api.service.InviterService;
import com.lawu.eshop.merchant.api.service.MemberProfileService;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.merchant.api.service.VerifyCodeService;
import com.lawu.eshop.property.dto.PropertyLoveAccountDTO;
import com.lawu.eshop.user.dto.InviteeMechantCountDTO;
import com.lawu.eshop.user.dto.InviteeMemberCountDTO;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MobileDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserHeadImgDTO;
import com.lawu.eshop.user.param.RegisterParam;
import com.lawu.eshop.user.param.RegisterRealParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.framework.web.util.HeaderUtil;
import com.lawu.media.util.UploadFileUtil;
import com.lawu.utils.DateUtil;
import com.lawu.utils.IpUtil;
import com.lawu.utils.QrCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/3/22
 */
@Api(tags = "merchant")
@RestController
@RequestMapping(value = "merchant/")
public class MerchantController extends BaseController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private PropertyInfoService propertyInfoService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private InviterService inviterService;

    @Autowired
    private MemberProfileService memberProfileService;

    @Autowired
    private MerchantApiConfig merchantApiConfig;

    @Autowired
    private AdService adService;

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private InviterBountyService inviterBountyService;
    
    @Autowired
    private  MessageService messageService ;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private EventPublisher eventPublisher;


    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "修改登录密码", notes = "根据商户ID修改登录密码。[1002|1009] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "updateLoginPwd", method = RequestMethod.PUT)
    public Result updateLoginPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                 @RequestParam @ApiParam(required = true, value = "原始密码") String originalPwd,
                                 @RequestParam @ApiParam(required = true, value = "新密码") String newPwd) {
        long id = UserUtil.getCurrentUserId(getRequest());
        return merchantService.updateLoginPwd(id, originalPwd, newPwd);
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "忘记登录密码", notes = "根据商户账号修改登录密码。[1100|1013|1016|1025] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "resetLoginPwd/{mobile}", method = RequestMethod.PUT)
    public Result resetLoginPwd(@PathVariable @ApiParam(required = true, value = "手机号码") String mobile,
                                @RequestParam @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
                                @RequestParam @ApiParam(required = true, value = "手机验证码") String smsCode,
                                @RequestParam @ApiParam(required = true, value = "新密码") String newPwd) {
        Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, smsCode);
        if (!isSuccess(smsResult)) {
            return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
        }
        VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
        if (!mobile.equals(verifyCodeDTO.getMobile())) {
            return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
        }
        if (DateUtil.smsIsOverdue(smsResult.getModel().getGmtCreate(), merchantApiConfig.getSmsValidMinutes())) {
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }
        return merchantService.updateLoginPwd(mobile, newPwd);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "修改支付密码", notes = "根据商户编号修改支付密码。[1009|1100] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "updatePayPwd", method = RequestMethod.PUT)
    public Result updatePayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                               @RequestParam @ApiParam(required = true, value = "原始密码") String originalPwd,
                               @RequestParam @ApiParam(required = true, value = "新密码") String newPwd) {
        String userNo = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.updatePayPwd(userNo, originalPwd, newPwd);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "忘记支付密码", notes = "根据商户编号重置支付密码。[1100|1013|1016|1025] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "resetPayPwd", method = RequestMethod.PUT)
    public Result resetPayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                              @RequestParam @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
                              @RequestParam @ApiParam(required = true, value = "手机验证码") String smsCode,
                              @RequestParam @ApiParam(required = true, value = "新密码") String newPwd) {
        String mobile = UserUtil.getCurrentAccount(getRequest());
        Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, smsCode);
        if (!isSuccess(smsResult)) {
            return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
        }
        VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
        if (!verifyCodeDTO.getMobile().equals(mobile)) {
            return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
        }
        if (DateUtil.smsIsOverdue(smsResult.getModel().getGmtCreate(), merchantApiConfig.getSmsValidMinutes())) {
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }
        String userNo = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.resetPayPwd(userNo, newPwd);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "设置支付密码", notes = "根据商户编号设置支付密码。[1100] (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "setPayPwd", method = RequestMethod.PUT)
    public Result setPayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                            @RequestParam @ApiParam(required = true, value = "新密码") String newPwd) {
        String userNo = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.setPayPwd(userNo, newPwd);
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "查询是否设置支付密码", notes = "查询是否设置支付密码(true--已设置，false--未设置)。[1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "isSetPayPwd", method = RequestMethod.GET)
    public Result isSetPayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.isSetPayPwd(userNum);
    }

    @SuppressWarnings("rawtypes")
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "注册", notes = "商户注册。[1026|1027|1013|1016|1025] (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "register/{verifyCodeId}", method = RequestMethod.POST)
    public Result register(@PathVariable @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
                           @ModelAttribute @ApiParam RegisterParam registerParam) {
        HttpServletRequest request = getRequest();
        RegisterRealParam registerRealParam = new RegisterRealParam();
        String num = null,userName = null;
        UserTypeEnum userType =null;
        if (StringUtils.isNotEmpty(registerParam.getInviterAccount())) {
            if (registerParam.getUserTypeEnum() != null) {
                if (registerParam.getUserTypeEnum().getValue().equals(UserTypeEnum.MEMBER.getValue())) {
                    Result<MemberDTO> memberDTOResult = memberService.getMemberByAccount(registerParam.getInviterAccount());
                    if (isSuccess(memberDTOResult)) {
                        MemberDTO memberDTO = memberDTOResult.getModel();
                        if (memberDTO.getFreeze()) {
                            return successCreated(ResultCode.WRONG_OPERATION, "邀请人账号异常");
                        }
                    	num = memberDTOResult.getModel().getNum();
						userName =UserNameEnum.MEMBER.getName();
						userType = UserTypeEnum.MEMBER;
                        registerRealParam.setInviterId(memberDTOResult.getModel().getId());
                        registerRealParam.setUserNum(num);
                    }
                } else {
                    Result<MerchantDTO> merchantDTOResult = merchantService.getMerchantByAccount(registerParam.getInviterAccount());
                    if (isSuccess(merchantDTOResult)) {
                        MerchantDTO merchantDTO = merchantDTOResult.getModel();
                        if (merchantDTO.getIsFreeze()) {
                            return successCreated(ResultCode.WRONG_OPERATION, "邀请人账号异常");
                        }
                    	num = merchantDTOResult.getModel().getNum();
                    	userName =UserNameEnum.MERCHANT.getName();
                    	userType = UserTypeEnum.MERCHANT;
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
                num = inviterDTO.getUserNum();
                userName =UserNameEnum.MERCHANT.getName();
                userType = UserTypeEnum.MERCHANT;
                registerRealParam.setInviterId(inviterDTO.getInviterId());
                registerRealParam.setUserNum(num);
            }
        }

        Result accountResult = merchantService.getMerchantByAccount(registerParam.getAccount());
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
        if (DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), merchantApiConfig.getSmsValidMinutes())) {
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }
        registerRealParam.setAccount(registerParam.getAccount());
        registerRealParam.setPwd(registerParam.getPwd());
        registerRealParam.setRegPlatformVer(HeaderUtil.getRequestPlatformVersion(request));
        registerRealParam.setRegAppVer(HeaderUtil.getRequestAppVersion(request));
        registerRealParam.setRegIp(IpUtil.getIpAddress(request));
        Result regResult = merchantService.register(registerRealParam);
       /* if(isSuccess(regResult)){
        	if(StringUtils.isNotEmpty(registerParam.getInviterAccount())){
        		//如果邀请人不为空，发送奖励金给邀请人
                Result<GetInviterBountyDTO> result = inviterBountyService.getInviterBounty(num,userType);
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
        }*/
        return successCreated(regResult);
    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "设置推送CID", notes = "设置推送CID。[1005] (章勇)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "setGetuiCid", method = RequestMethod.PUT)
    public Result setGetuiCid(@RequestParam("cid") String cid, @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long id = UserUtil.getCurrentUserId(getRequest());
        if (id == null || id <= 0 || "".equals(cid)) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
        }
        return merchantService.setGtAndRongYunInfo(id, cid);
    }


    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "商家个人中心", notes = "=基本信息查询[] (张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "selectMerchantInfo", method = RequestMethod.GET)
    public Result<MerchantSNSDTO> selectMerchantInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long id = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<MerchantSNSDTO> result = merchantService.selectMerchantInfo(id);
        MerchantSNSDTO dto = result.getModel();
        InviteeMechantCountDTO inviteeMechantCountDTO = memberProfileService.getMerchantCount(id).getModel();
        InviteeMemberCountDTO inviteeMemberCountDTO = memberProfileService.getMemberCount(id).getModel();
        PropertyLoveAccountDTO propertyLoveAccountDTO = propertyInfoService.selectLoveAccount(userNum).getModel();
        if (inviteeMechantCountDTO != null) {
            dto.setInviteeMechantCount(inviteeMechantCountDTO.getInviteeMechantCount());
        } else {
            dto.setInviteeMechantCount(0);
        }
        if (inviteeMemberCountDTO != null) {
            dto.setInviteeMemberCount(inviteeMemberCountDTO.getInviteeMemberCount());
        } else {
            dto.setInviteeMemberCount(0);
        }
        if (propertyLoveAccountDTO != null) {
            dto.setLoveAccount(propertyLoveAccountDTO.getLoveAccount());
        } else {
            dto.setLoveAccount(new BigDecimal(0));
        }
        return successGet(dto);
    }

    @Deprecated
    @Audit(date = "2017-05-02", reviewer = "孙林青")
    @ApiOperation(value = "修改头像", notes = "修改头像。 (章勇)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "saveHeadImage", method = RequestMethod.POST)
    public Result<UserHeadImgDTO> saveHeadImage(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        HttpServletRequest request = getRequest();
        Long merchantId = UserUtil.getCurrentUserId(request);
        String headImg;
        Map<String, String> retMap = UploadFileUtil.uploadOneImage(request, FileDirConstant.DIR_HEAD, merchantApiConfig.getImageUploadUrl());
        if (!"".equals(retMap.get("imgUrl"))) {
            headImg = retMap.get("imgUrl");
            return merchantService.saveHeadImage(merchantId, headImg);
        }
        return successCreated(ResultCode.IMAGE_WRONG_UPLOAD);
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "生成身份二维码", notes = "生成身份二维码。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getQrCode", method = RequestMethod.GET)
    public void getQrCode(@RequestParam @ApiParam(required = true, value = "token") String token) throws IOException {
        String merchantId = UserUtil.getCurrentUserIdByToken(token);
        if (StringUtils.isEmpty(merchantId)) {
            return;
        }
        HttpServletResponse response = getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage buffImg = QrCodeUtil.generateQrCode(merchantApiConfig.getMerchantQrCode() + merchantId);
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    @Audit(date = "2017-05-17", reviewer = "孙林青")
    @ApiOperation(value = "扫描身份二维码", notes = "扫描身份二维码。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getQrCodeContent/{merchantId}", method = RequestMethod.GET)
    public void getQrCodeContent(@PathVariable @ApiParam(required = true, value = "商家ID") Long merchantId) throws IOException {
        HttpServletResponse response = getResponse();
        if (merchantId == null || merchantId <= 0) {
            return;
        }
        Result<IsExistsRedPacketDTO> result = adService.isExistsRedPacket(merchantId);
        if (!result.getModel().getIsExistsRedPacket()) {
            response.sendRedirect(merchantApiConfig.getShareRedpacketUrl() + merchantId);
        } else {
            response.sendRedirect(merchantApiConfig.getShareRegisterUrl() + merchantId);
        }
    }

    @AutoTesting
    @Audit(date = "2017-07-04", reviewer = "孙林青")
    @ApiOperation(value = "获取商家电话", notes = "获取商家电话。 (张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectMobile/{merchantId}", method = RequestMethod.GET)
    public Result<MobileDTO> selectMobile(@PathVariable @ApiParam(required = true, value = "商家ID") Long merchantId) {
        Result<MobileDTO> result = merchantService.selectMobile(merchantId);
        return result;
    }

    @AutoTesting
    @Audit(date = "2017-05-23", reviewer = "孙林青")
    @ApiOperation(value = "查询融云需要信息", notes = "查询融云需要信息。 [1004|1100] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRongYunInfo/{num}", method = RequestMethod.GET)
    public Result<RongYunDTO> getRongYunInfo(@PathVariable @ApiParam(required = true, value = "用户编号") String num) {
        if (StringUtils.isEmpty(num)) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        if (num.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            return memberService.getRongYunInfoByNum(num);
        } else {
            return merchantService.getRongYunInfoByNum(num);
        }
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "修改头像", notes = "修改头像。 (梅述全)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "updateHeadImg", method = RequestMethod.PUT)
    public Result updateHeadImg(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                @RequestParam @ApiParam(required = true, value = "头像路径") String headImg) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return merchantService.saveHeadImage(merchantId, headImg);
    }

    @Audit(date = "2017-12-04", reviewer = "孙林青")
    @ApiOperation(value = "生成收款二维码", notes = "生成收款二维码。 (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPayQrCode", method = RequestMethod.GET)
    public void getPayQrCode(@RequestParam @ApiParam(required = true, value = "token") String token) throws IOException {
        String merchantId = UserUtil.getCurrentUserIdByToken(token);
        if (StringUtils.isEmpty(merchantId)) {
            return;
        }
        HttpServletResponse response = getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        BufferedImage buffImg = QrCodeUtil.generateQrCode("MD|" + merchantId);
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    @Audit(date = "2018-03-01", reviewer = "孙林青")
    @ApiOperation(value = "验证账号是否注册", notes = "验证账号是否注册。[1027] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "isRegister/{account}", method = RequestMethod.GET)
    public Result isRegister(@PathVariable @ApiParam(required = true, value = "注册账号") String account) {
        Result<Boolean> result = merchantService.isRegister(account);
        if (result.getModel()) {
            return successGet(ResultCode.ACCOUNT_EXIST);
        }
        return successGet();
    }
   
}
