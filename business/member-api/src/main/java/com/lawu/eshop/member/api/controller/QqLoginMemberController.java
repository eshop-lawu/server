package com.lawu.eshop.member.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.manager.TokenManager;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserSexEnum;
import com.lawu.eshop.framework.web.impl.dto.TokenDTO;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.event.EventPublisher;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.QqLoginMemberService;
import com.lawu.eshop.member.api.service.VerifyCodeService;
import com.lawu.eshop.user.dto.QqLoginMemberDTO;
import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.param.WxMemberBindParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.fastdfs.ClientParams;
import com.lawu.media.util.UploadParam;
import com.lawu.media.util.WxUploadUtil;
import com.lawu.media.util.upload.FileUploadTypeEnum;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * QQ登录授权接口
 * 
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
@Api(tags = "qqLoginMember", value = "QQ登录授权接口")
@RestController
@RequestMapping(value = "qqLoginMember/")
public class QqLoginMemberController extends BaseController{
	
	@Autowired
	private MemberApiConfig memberApiConfig;
    
    @Autowired
    private QqLoginMemberService qqLoginMemberService;
    
    @Autowired
    private VerifyCodeService verifyCodeService;
    
    @Autowired
    private MemberService memberService;

	@Audit(date = "2018-01-17", reviewer = "孙林青")

	@ApiOperation(value = "确定授权保存QQ用户信息", notes = "保存QQ用户信息(张荣成)", httpMethod = "POST")
	@RequestMapping(value = "qqLoginMemberSave")
	public Result qqLoginMemberSave(@ModelAttribute @ApiParam  QqLoginMemberParam param){
		Result result = qqLoginMemberService.qqLoginMemberSave(param);
		return successCreated(result);
	}

	@Audit(date = "2018-01-17", reviewer = "孙林青")

	@ApiOperation(value = "QQ绑定账户", notes = "QQ绑定账户 [1027|1013|1016|1026](张荣成)", httpMethod = "POST")
	@RequestMapping(value = "qqMemberBind/{verifyCodeId}")
	public Result qqMemberBind(@PathVariable @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
			@ModelAttribute @ApiParam  WxMemberBindParam bindParam){
		
		Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, bindParam.getSmsCode());
		if (!isSuccess(smsResult)) {
			return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
		}
		VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
		if (!bindParam.getAccount().equals(verifyCodeDTO.getMobile())) {
			return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
		}
		if (DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), memberApiConfig.getSmsValidMinutes())) {
			return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
		}
		
		
		Result<String> resultImg = qqLoginMemberService.getHeadImg(bindParam.getOpenId());

		UploadParam uparam = new UploadParam();
		uparam.setBaseImageDir(memberApiConfig.getImageUploadUrl());
		uparam.setDir(FileDirConstant.DIR_HEAD);
		uparam.setFileUploadTypeEnum(FileUploadTypeEnum.IMG);
		ClientParams cp = new ClientParams();
		cp.setTrackerServer(memberApiConfig.getTrackerServers());
		cp.setTrackerHttpPort(memberApiConfig.getTrackerHttpPort());
		uparam.setCparam(cp);

		Result accountResult = memberService.getMemberByAccount(bindParam.getAccount());
		
		String imgUrl = "";
		if(!isSuccess(accountResult)){
			try {
				imgUrl = WxUploadUtil.upload(resultImg.getModel(), uparam);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		Result result = qqLoginMemberService.qqMemberBind(bindParam.getOpenId(),bindParam.getAccount(),imgUrl);
		
		return successCreated(result); 
	}
	

}
