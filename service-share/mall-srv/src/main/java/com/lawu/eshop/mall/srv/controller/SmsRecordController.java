package com.lawu.eshop.mall.srv.controller;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lawu.aliyun.AliyunSmsServiceImpl;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.mall.param.SmsRecordParam;
import com.lawu.eshop.mall.srv.MallSrvConfig;
import com.lawu.eshop.mall.srv.bo.SmsRecordBO;
import com.lawu.eshop.mall.srv.constants.AliYunSmsConstant;
import com.lawu.eshop.mall.srv.service.SmsRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.RandomUtil;

import net.sf.json.JSONObject;

/**
 * @author meishuquan
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "smsRecord/")
public class SmsRecordController extends BaseController {

    @Autowired
    private SmsRecordService smsRecordService;

    @Autowired
    private MallSrvConfig mallSrvConfig;

    @Autowired
    private AliyunSmsServiceImpl aliyunSmsServiceImpl;

    /**
     * 发送短信
     *
     * @param mobile  手机号码
     * @param ip      IP
     * @param purpose 短信类型
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "sendSms/{mobile}", method = RequestMethod.GET)
    public Result<VerifyCodeDTO> sendSms(@PathVariable String mobile, @RequestParam String ip, @RequestParam VerifyCodePurposeEnum purpose) throws Exception {
        if (mobile.length() != 11 || !mobile.startsWith("1")) {
            return successGet(ResultCode.MOBILE_NUMBER_ILLEGAL);
        }
        String templateCode;
        if (purpose == VerifyCodePurposeEnum.USER_REGISTER) {
            templateCode = mallSrvConfig.getRegisterTemplate();
        } else if (purpose == VerifyCodePurposeEnum.FIND_LOGIN_PWD || purpose == VerifyCodePurposeEnum.FIND_PAY_PWD) {
            templateCode = mallSrvConfig.getFindPwdTemplate();
        } else if (purpose == VerifyCodePurposeEnum.REFUND_BOND) {
            templateCode = mallSrvConfig.getRefundDepositTemplate();
        } else if (purpose == VerifyCodePurposeEnum.SMS_LOGIN) {
            templateCode = mallSrvConfig.getLoginTemplate();
        } else if (purpose == VerifyCodePurposeEnum.THIRD_LOGIN) {
            templateCode = mallSrvConfig.getBindPhoneTemplate();
        } else {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        int errorCode = smsRecordService.verifySendSms(mobile, ip);
        if (errorCode != ResultCode.SUCCESS) {
            return successGet(errorCode);
        }

        VerifyCodeDTO verifyCodeDTO = new VerifyCodeDTO();
        String smsCode = RandomUtil.getRandomString(1, 6);
        SmsRecordParam param = new SmsRecordParam();
        param.setMobile(mobile);
        param.setIp(ip);
        param.setPurposeEnum(purpose);
        param.setSmsCode(smsCode);

        if (!mallSrvConfig.getIsSend()) {
            param.setSuccess(true);
            SmsRecordBO smsRecordBO = smsRecordService.saveSmsRecord(param);
            verifyCodeDTO.setId(smsRecordBO.getVirifyCodeId());
            return successGet(verifyCodeDTO);
        }

        String sendResult = aliyunSmsServiceImpl.sendSms(mobile, smsCode, templateCode);
        JSONObject jsonObject = JSONObject.fromObject(sendResult);
        SendSmsResponse response = (SendSmsResponse) JSONObject.toBean(jsonObject, SendSmsResponse.class);
        boolean sendFlag = false;
        if (StringUtils.isNotEmpty(response.getCode()) && AliYunSmsConstant.SEND_OK.equals(response.getCode())) {
            //发送成功
            sendFlag = true;
        }
        param.setSuccess(sendFlag);
        param.setFailReason(response.getMessage());
        SmsRecordBO smsRecordBO = smsRecordService.saveSmsRecord(param);
        if (sendFlag) {
            verifyCodeDTO.setId(smsRecordBO.getVirifyCodeId());
            return successGet(verifyCodeDTO);
        }

        if (StringUtils.isNotEmpty(response.getCode()) && AliYunSmsConstant.MOBILE_NUMBER_ILLEGAL.equals(response.getCode())) {
            return successGet(ResultCode.MOBILE_NUMBER_ILLEGAL);
        } else if (StringUtils.isNotEmpty(response.getCode()) && AliYunSmsConstant.BUSINESS_LIMIT_CONTROL.equals(response.getCode())) {
            //短信限流
            String times = response.getMessage().substring(response.getMessage().indexOf(":") + 1, response.getMessage().length());
            if (AliYunSmsConstant.ONE_MINUTE_TIME.equals(times)) {

                return successGet(ResultCode.ONE_MINUTE_TIME);
            } else if (AliYunSmsConstant.ONE_HOUR_TIME.equals(times)) {

                return successGet(ResultCode.ONE_HOUR_TIME);
            } else if (AliYunSmsConstant.ONE_DAY_TIME.equals(times)) {

                return successGet(ResultCode.ONE_DAY_TIME);
            }
        }
        return successGet(ResultCode.SMS_SEND_FAIL);
    }

}
