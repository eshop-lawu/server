package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserSexEnum;
import com.lawu.eshop.framework.web.impl.dto.TokenDTO;
import com.lawu.eshop.mall.dto.MemberConfigDTO;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.QqLoginMemberService;
import com.lawu.eshop.member.api.service.VerifyCodeService;
import com.lawu.eshop.member.api.service.WxLoginMemberService;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Leach
 * @date 2017/3/11
 */
@Api(tags = "common")
@RestController
@RequestMapping(value = "/")
public class CommonController extends BaseController {

    @Autowired
    private MemberApiConfig memberApiConfig;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private EventPublisher eventPublisher;
    
    @Autowired
    private WxLoginMemberService wxLoginMemberService;
    
    @Autowired
    private QqLoginMemberService qqLoginMemberService;
    
    @Autowired
    private VerifyCodeService verifyCodeService;

    @AutoTesting
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-03-29", reviewer = "孙林青")
    @ApiOperation(value = "登录", notes = "根据账号密码登录，成功返回token。[2000|2015|2019]（孙林青）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "login/{account}", method = RequestMethod.POST)
    public Result<TokenDTO> login(@PathVariable @ApiParam(required = true, value = "账号") String account,
                                  @RequestParam @ApiParam(required = true, value = "密码") String pwd) {

        Result<LoginUserDTO> result = memberService.find(account, pwd);
        if (!isSuccess(result)) {
            return successGet(result);
        }

        TokenDTO tokenDTO = getLoginUser(result);
        
        return successCreated(tokenDTO);
    }

    @SuppressWarnings("rawtypes")
	@Audit(date = "2017-03-29", reviewer = "孙林青")
    @ApiOperation(value = "退出", notes = "退出登录，清除token。（孙林青）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @Authorization
    @RequestMapping(value = "logout", method = RequestMethod.DELETE)
    public Result logout(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        memberService.delUserGtPush(userId);
        tokenManager.delRelationshipByToken(token);
        return successDelete();
    }

    @AutoTesting
    @Audit(date = "2017-04-19", reviewer = "孙林青")
    @ApiOperation(value = "获取配置信息", notes = "获取配置信息。（章勇）", httpMethod = "GET")
    @RequestMapping(value = "getConfig", method = RequestMethod.GET)
    public Result<MemberConfigDTO> getConfig() {
    	MemberConfigDTO configDTO = new MemberConfigDTO();
        configDTO.setImageUrl(memberApiConfig.getImageUrl());
        configDTO.setVideoUrl(memberApiConfig.getVideoUrl());
        configDTO.setInviterMemberUrl(memberApiConfig.getInviterMemberUrl());
        configDTO.setInviterMerchantUrl(memberApiConfig.getInviterMerchantUrl());
        configDTO.setMemberH5Ip(memberApiConfig.getMemberH5Ip());
        configDTO.setShareEPraiseAdDefaultImagePath(memberApiConfig.getShareEPraiseAdDefaultImagePath());
        configDTO.setShareFlatAdDefaultImagePath(memberApiConfig.getShareFlatAdDefaultImagePath());
        configDTO.setShareVideoAdDefaultImagePath(memberApiConfig.getShareVideoAdDefaultImagePath());
        configDTO.setShareAdLogoPath(memberApiConfig.getShareAdLogoPath());
        configDTO.setShareRedPacketDefaultImagePath(memberApiConfig.getShareRedPacketDefaultImagePath());
        configDTO.setGameMindShareImg(memberApiConfig.getGameMindShareImg());
        configDTO.setGamePuzzleShareImg(memberApiConfig.getGamePuzzleShareImg());
        configDTO.setGameDialShareImg(memberApiConfig.getGameDialShareImg());
        configDTO.setAppMemberUrl(memberApiConfig.getAppMemberUrl());
        return successCreated(configDTO);
    }

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "判断是否登录", notes = "判断是否登录。（章勇）", httpMethod = "GET")
    @RequestMapping(value = "isLogin", method = RequestMethod.GET)
    public Result<Boolean> isLogin(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        if (memberId == null || memberId == 0L) {
            return successGet(false);
        }
        return successGet(true);
    }


    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "微信登录", notes = "微信登录,调用此接口之前先授权 [2025](张荣成)", httpMethod = "GET")
	@RequestMapping(value = "wxLogin")
	public Result<TokenDTO> wxLogin(@RequestParam @ApiParam(name = "openId", required = true, value = "openId") String openId){
		Result<LoginUserDTO> result = wxLoginMemberService.wxLogin(openId);
		
		if (!isSuccess(result)) {
			return successGet(result);
		}

		TokenDTO tokenDTO = getLoginUser(result);
		
		return successGet(tokenDTO); 
	}

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "QQ登录", notes = "QQ登录,调用此接口之前先授权 [2026](张荣成)", httpMethod = "GET")
	@RequestMapping(value = "qqLogin")
	public Result<TokenDTO> qqLogin(@RequestParam @ApiParam(name = "openId", required = true, value = "openId") String openId){
		Result<LoginUserDTO> result = qqLoginMemberService.qqLogin(openId);
		
		if (!isSuccess(result)) {
			return successGet(result);
		}

		TokenDTO tokenDTO = getLoginUser(result);
		return successGet(tokenDTO); 
	}


    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "短信登录", notes = "短信登录，成功返回token。[2000|2015|2019]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "smsLogin/{account}", method = RequestMethod.POST)
    public Result<TokenDTO> smsLogin(@PathVariable @ApiParam(required = true, value = "账号") String account,
                                  @RequestParam @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
                                  @RequestParam @ApiParam(required = true, value = "短信验证码") String smsCode) {

    	Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, smsCode);
        if (!isSuccess(smsResult)) {
            return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
        }
        VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
        if (!account.equals(verifyCodeDTO.getMobile())) {
            return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
        }
        if(DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), memberApiConfig.getSmsValidMinutes())){
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }

        Result<LoginUserDTO> result = memberService.smsLogin(account);

        if (!isSuccess(result)) {
            return successGet(result);
        }
        
        TokenDTO tokenDTO = getLoginUser(result);
        
        return successCreated(tokenDTO);
    }

    private TokenDTO getLoginUser(Result<LoginUserDTO> result){
    	
    	LoginUserDTO userDTO = result.getModel();

        String token = tokenManager.createToken(UserConstant.MEMBER_TOKEN_TYPE, userDTO.getNum(), userDTO.getId(), userDTO.getAccount());

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserNum(userDTO.getNum());
        tokenDTO.setToken(token);
        tokenDTO.setRyToken(userDTO.getRyToken());
        tokenDTO.setIsFreeze(userDTO.getIsFreeze());
        tokenDTO.setUserSex(UserSexEnum.getEnum(userDTO.getUserSex().val));

        eventPublisher.publishLoginEvent(getRequest(), userDTO.getNum(), userDTO.getAccount());

        //助力邀请人瑞奇岛动力任务
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(userDTO.getNum());
        taskRecordParam.setType(PowerTaskTypeEnum.INVITE);
        eventPublisher.publishRichPowerTaskEvent(taskRecordParam);

        return tokenDTO;
    }


}
