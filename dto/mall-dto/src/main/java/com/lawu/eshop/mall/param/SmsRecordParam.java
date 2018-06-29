package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;

/**
 * @author meishuquan
 * @date 2018/3/2.
 */
public class SmsRecordParam {

    private String mobile;

    private String ip;

    private VerifyCodePurposeEnum purposeEnum;

    private String smsCode;

    private Boolean isSuccess;

    private String failReason;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public VerifyCodePurposeEnum getPurposeEnum() {
        return purposeEnum;
    }

    public void setPurposeEnum(VerifyCodePurposeEnum purposeEnum) {
        this.purposeEnum = purposeEnum;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
