package com.lawu.eshop.mall.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/3/13
 */
@Component
public class MallSrvConfig {

    @Value(value = "${sms_url}")
    private String smsUrl;

    @Value(value = "${sms_encoding}")
    private String smsEncoding;

    @Value(value = "${sms_sp_code}")
    private String smsSpCode;

    @Value(value = "${sms_login_name}")
    private String smsLoginName;

    @Value(value = "${sms_password}")
    private String smsPassword;

    @Value(value = "${sms_serial_number}")
    private String smsSerialNumber;

    @Value(value = "${sms_f}")
    private String smsF;

    @Value(value = "${sms_template}")
    private String smsTemplate;

    @Value(value = "${sms_is_send}")
    private Boolean isSend;

    @Value(value = "${sms.send.hour.count}")
    private Integer smsSendHourCount;

    @Value(value = "${sms.send.ip.count}")
    private Integer smsSendIpCount;

    @Value(value = "${sms.send.mobile.count}")
    private Integer smsSendMobileCount;

    @Value(value = "${aliyun.sms.templateCode}")
    private String templateCode;

    @Value(value = "${sms.can.send.minute}")
    private Integer smsSendMinute;
    
    @Value(value = "${message.type.send.notice.img}")
    private String messageSendNoticeImg;

    @Value(value = "${aliyun.sms.template.register}")
    private String registerTemplate;

    @Value(value = "${aliyun.sms.template.login}")
    private String loginTemplate;

    @Value(value = "${aliyun.sms.template.bind.phone}")
    private String bindPhoneTemplate;

    @Value(value = "${aliyun.sms.template.find.pwd}")
    private String findPwdTemplate;

    @Value(value = "${aliyun.sms.template.refund.deposit}")
    private String refundDepositTemplate;

    @Value(value = "${aliyun.sms.template.register.content}")
    private String registerTemplateContent;

    @Value(value = "${aliyun.sms.template.login.content}")
    private String loginTemplateContent;

    @Value(value = "${aliyun.sms.template.bind.phone.content}")
    private String bindPhoneTemplateContent;

    @Value(value = "${aliyun.sms.template.find.pwd.content}")
    private String findPwdTemplateContent;

    @Value(value = "${aliyun.sms.template.refund.deposit.content}")
    private String refundDepositTemplateContent;

    public String getSmsUrl() {
        return smsUrl;
    }

    public String getSmsEncoding() {
        return smsEncoding;
    }

    public String getSmsSpCode() {
        return smsSpCode;
    }

    public String getSmsLoginName() {
        return smsLoginName;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public String getSmsSerialNumber() {
        return smsSerialNumber;
    }

    public String getSmsF() {
        return smsF;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public Integer getSmsSendHourCount() {
        return smsSendHourCount;
    }

    public Integer getSmsSendIpCount() {
        return smsSendIpCount;
    }

    public Integer getSmsSendMobileCount() {
        return smsSendMobileCount;
    }

    public String getTemplateCode() {
        return templateCode;
    }

	public Integer getSmsSendMinute() {
		return smsSendMinute;
	}

	public String getMessageSendNoticeImg() {
		return messageSendNoticeImg;
	}

    public String getRegisterTemplate() {
        return registerTemplate;
    }

    public String getLoginTemplate() {
        return loginTemplate;
    }

    public String getBindPhoneTemplate() {
        return bindPhoneTemplate;
    }

    public String getFindPwdTemplate() {
        return findPwdTemplate;
    }

    public String getRefundDepositTemplate() {
        return refundDepositTemplate;
    }

    public String getRegisterTemplateContent() {
        return registerTemplateContent;
    }

    public String getLoginTemplateContent() {
        return loginTemplateContent;
    }

    public String getBindPhoneTemplateContent() {
        return bindPhoneTemplateContent;
    }

    public String getFindPwdTemplateContent() {
        return findPwdTemplateContent;
    }

    public String getRefundDepositTemplateContent() {
        return refundDepositTemplateContent;
    }
}
