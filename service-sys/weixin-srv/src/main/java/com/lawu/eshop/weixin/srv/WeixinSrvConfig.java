package com.lawu.eshop.weixin.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2018/1/18
 */
@Component
public class WeixinSrvConfig {

    @Value(value = "${lawu.eshop.weixin.mp.member.appId}")
    private String memberAppId;

    @Value(value = "${lawu.eshop.weixin.mp.member.appSecret}")
    private String memberAppSecret;

    @Value(value = "${lawu.eshop.weixin.mp.merchant.appId}")
    private String merchantAppId;

    @Value(value = "${lawu.eshop.weixin.mp.merchant.appSecret}")
    private String merchantAppSecret;

    /**
     * 设置微信公众号的appid
     */
    @Value(value = "${lawu.eshop.weixin.pay.member.appId}")
    private String memberPayAppId;

    /**
     * 微信支付商户号
     */
    @Value(value = "${lawu.eshop.weixin.pay.member.mchId}")
    private String memberPayMchId;

    /**
     * 微信支付商户密钥
     */
    @Value(value = "${lawu.eshop.weixin.pay.member.mchKey}")
    private String memberPayMchKey;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    @Value(value = "${lawu.eshop.weixin.pay.member.mkeyPathchKey}")
    private String memberPayKeyPath;

    /**
     * 是否启用红包发放
     */
    @Value(value = "${lawu.eshop.weixin.enableRedpack}")
    private boolean enableRedpack;

    public String getMemberAppId() {
        return memberAppId;
    }

    public void setMemberAppId(String memberAppId) {
        this.memberAppId = memberAppId;
    }

    public String getMemberAppSecret() {
        return memberAppSecret;
    }

    public void setMemberAppSecret(String memberAppSecret) {
        this.memberAppSecret = memberAppSecret;
    }

    public String getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(String merchantAppId) {
        this.merchantAppId = merchantAppId;
    }

    public String getMerchantAppSecret() {
        return merchantAppSecret;
    }

    public void setMerchantAppSecret(String merchantAppSecret) {
        this.merchantAppSecret = merchantAppSecret;
    }

    public String getMemberPayAppId() {
        return memberPayAppId;
    }

    public void setMemberPayAppId(String memberPayAppId) {
        this.memberPayAppId = memberPayAppId;
    }

    public String getMemberPayMchId() {
        return memberPayMchId;
    }

    public void setMemberPayMchId(String memberPayMchId) {
        this.memberPayMchId = memberPayMchId;
    }

    public String getMemberPayMchKey() {
        return memberPayMchKey;
    }

    public void setMemberPayMchKey(String memberPayMchKey) {
        this.memberPayMchKey = memberPayMchKey;
    }

    public String getMemberPayKeyPath() {
        return memberPayKeyPath;
    }

    public void setMemberPayKeyPath(String memberPayKeyPath) {
        this.memberPayKeyPath = memberPayKeyPath;
    }

    public boolean isEnableRedpack() {
        return enableRedpack;
    }

    public void setEnableRedpack(boolean enableRedpack) {
        this.enableRedpack = enableRedpack;
    }
}
