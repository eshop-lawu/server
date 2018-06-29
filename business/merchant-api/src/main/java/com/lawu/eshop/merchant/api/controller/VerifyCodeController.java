package com.lawu.eshop.merchant.api.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.merchant.api.service.SmsRecordService;
import com.lawu.eshop.merchant.api.service.VerifyCodeCacheService;
import com.lawu.eshop.merchant.api.service.VerifyCodeService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;
import com.lawu.utils.IpUtil;
import com.lawu.utils.VerifyCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/3/29.
 */
@Api(tags = "verifyCode")
@RestController
@RequestMapping(value = "verifyCode/")
public class VerifyCodeController extends BaseController {

    @Autowired
    private SmsRecordService smsRecordService;

    @Autowired
    private VerifyCodeService verifyCodeService;
    
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantApiConfig merchantApiConfig;

    @Autowired
    private VerifyCodeCacheService verifyCodeCacheService;

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码。[1004|1006|1007|1008|1014|1027|2013|1043|1048|1049] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "sendSms/{mobile}", method = RequestMethod.GET)
    public Result<VerifyCodeDTO> sendSms(@PathVariable @ApiParam(required = true, value = "手机号码") String mobile,
                                         @RequestParam @ApiParam(required = false, value = "图形验证码") String picCode,
                                         VerifyCodePurposeEnum purpose) {
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            return successGet(ResultCode.MOBILE_NUMBER_ILLEGAL);
        }
        Result<Boolean> isRegisterFlag = merchantService.isRegister(mobile);
        if (isSuccess(isRegisterFlag)) {
            if (purpose == VerifyCodePurposeEnum.FIND_LOGIN_PWD || purpose == VerifyCodePurposeEnum.SMS_LOGIN) { //找回登录密码
                if (!isRegisterFlag.getModel()) {
                    return successGet(ResultCode.MOBILE_IS_NOT_EXIST);
                }
            } else if (purpose == VerifyCodePurposeEnum.USER_REGISTER) { //注册
                if (isRegisterFlag.getModel()) {
                    return successGet(ResultCode.ACCOUNT_EXIST);
                }
            }
        }

        Result<Boolean> picFlag = verifyCodeCacheService.getPicCodeFlag(UserCommonConstant.MERCHANT_NUM_TAG, merchantApiConfig.getFrequencyExpireTime(), merchantApiConfig.getPicCodeVerifyExpireTime(), merchantApiConfig.getFrequencyLimit());
        if (picFlag.getModel()) {
            if (StringUtils.isEmpty(picCode)) {
                return successGet(ResultCode.PIC_CODE_VERIFY);
            } else {
                Result<Boolean> verifyResult = verifyCodeCacheService.verifyPicCode(UserCommonConstant.MERCHANT_NUM_TAG + "_" + mobile, picCode);
                if (verifyResult.getModel() == null) {
                    return successGet(ResultCode.PIC_CODE_OVERTIME);
                }
                if (!verifyResult.getModel()) {
                    return successGet(ResultCode.VERIFY_PIC_CODE_FAIL);
                }
            }
        }

        String ip = IpUtil.getIpAddress(getRequest());
        return smsRecordService.sendSms(mobile, ip, purpose);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "获取短信验证码(无需图形验证码)", notes = "获取短信验证码。[1006|1007|1008|1027|2013|1047] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getSmsCode/{mobile}", method = RequestMethod.GET)
    public Result<VerifyCodeDTO> getSmsCode(@PathVariable @ApiParam(required = true, value = "手机号码") String mobile,
                             VerifyCodePurposeEnum purpose) {
        if (merchantApiConfig.getSmsVersionFlag()) {
            return successGet(ResultCode.SMS_INTERFACE_UPDATE);
        }
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            return successGet(ResultCode.MOBILE_NUMBER_ILLEGAL);
        }

        Result<Boolean> isRegisterFlag= merchantService.isRegister(mobile);
    	if(isSuccess(isRegisterFlag)){
    		if(purpose==VerifyCodePurposeEnum.FIND_LOGIN_PWD || purpose==VerifyCodePurposeEnum.SMS_LOGIN){ //找回登录密码
    			if(!isRegisterFlag.getModel()){
        			return successGet(ResultCode.MOBILE_IS_NOT_EXIST);
        		}
        	}else if(purpose==VerifyCodePurposeEnum.USER_REGISTER){ //注册
        		if(isRegisterFlag.getModel()){
        			return successGet(ResultCode.ACCOUNT_EXIST);
        		}
        	}
    		
    	}
        String ip = IpUtil.getIpAddress(getRequest());
        return smsRecordService.sendSms(mobile, ip, purpose);
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "获取图形验证码", notes = "获取图形验证码。 (梅述全)", httpMethod = "GET")
    @RequestMapping(value = "getPicCode/{mobile}", method = RequestMethod.GET)
    public void getPicCode(@PathVariable @ApiParam(required = true, value = "手机号码") String mobile) throws IOException {
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            return;
        }

        BufferedImage buffImg = new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        String picCode = VerifyCodeUtil.getVerifyCode(g);
        verifyCodeCacheService.savePicCode(UserCommonConstant.MERCHANT_NUM_TAG + "_" + mobile, picCode, merchantApiConfig.getPicCodeExpireTime());

        HttpServletResponse response = getResponse();
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    @AutoTesting
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "查询验证码", notes = "查询验证码。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getVerifyCode/{id}", method = RequestMethod.GET)
    public Result getVerifyCode(@PathVariable @ApiParam(required = true, value = "ID") Long id) {
        return verifyCodeService.getVerifyCodeById(id);
    }

    @Audit(date = "2018-01-19", reviewer = "孙林青")
    @ApiOperation(value = "校验手机验证码", notes = "校验手机验证码。[1013|1025] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "verifySmsCode/{id}", method = RequestMethod.GET)
    public Result verifySmsCode(@PathVariable @ApiParam(required = true, value = "验证码ID") Long id,
                             @RequestParam @ApiParam(required = true, value = "验证码") String smsCode) {
        Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(id, smsCode);
        if (!isSuccess(smsResult)) {
            return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
        }
        VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
        if (DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), merchantApiConfig.getSmsValidMinutes())) {
            return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
        }
        return successGet();
    }

}
