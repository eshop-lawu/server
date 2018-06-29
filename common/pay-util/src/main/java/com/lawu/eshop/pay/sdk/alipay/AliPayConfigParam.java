package com.lawu.eshop.pay.sdk.alipay;

/**
 * @author meishuquan
 * @date 2017/5/4.
 */
public class AliPayConfigParam {

    private String alipayRefundUrl;

    private String alipayGatewayUrl;

    private String alipayAppIdMember;

    private String alipayPrivateKey;

    private String alipayEdianMemberPublicKey;

    private String alipayPublicKey;

    private String alipayPartner;

    private String alipayHttpsVerifyUrl;

    private String alipaySignType;

    private String alipayInputCharset;

    private String alipayAppIdMerchant;

    private String alipayEdianMerchantPublicKey;

    //pc支付回调异步地址
    private String alipayNotifyUrlPc;
    //pc回调同步地址
    private String alipayReturnUrlPc;

    public String getAlipayRefundUrl() {
        return alipayRefundUrl;
    }

    public void setAlipayRefundUrl(String alipayRefundUrl) {
        this.alipayRefundUrl = alipayRefundUrl;
    }

    public String getAlipayAppIdMember() {
        return alipayAppIdMember;
    }

    public void setAlipayAppIdMember(String alipayAppIdMember) {
        this.alipayAppIdMember = alipayAppIdMember;
    }

    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public void setAlipayPrivateKey(String alipayPrivateKey) {
        this.alipayPrivateKey = alipayPrivateKey;
    }

    public String getAlipayEdianMemberPublicKey() {
        return alipayEdianMemberPublicKey;
    }

    public void setAlipayEdianMemberPublicKey(String alipayEdianMemberPublicKey) {
        this.alipayEdianMemberPublicKey = alipayEdianMemberPublicKey;
    }

    public String getAlipayEdianMerchantPublicKey() {
        return alipayEdianMerchantPublicKey;
    }

    public void setAlipayEdianMerchantPublicKey(String alipayEdianMerchantPublicKey) {
        this.alipayEdianMerchantPublicKey = alipayEdianMerchantPublicKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getAlipayPartner() {
        return alipayPartner;
    }

    public void setAlipayPartner(String alipayPartner) {
        this.alipayPartner = alipayPartner;
    }

    public String getAlipayHttpsVerifyUrl() {
        return alipayHttpsVerifyUrl;
    }

    public void setAlipayHttpsVerifyUrl(String alipayHttpsVerifyUrl) {
        this.alipayHttpsVerifyUrl = alipayHttpsVerifyUrl;
    }

    public String getAlipayAppIdMerchant() {
        return alipayAppIdMerchant;
    }

    public void setAlipayAppIdMerchant(String alipayAppIdMerchant) {
        this.alipayAppIdMerchant = alipayAppIdMerchant;
    }

    public String getAlipayNotifyUrlPc() {
        return alipayNotifyUrlPc;
    }

    public void setAlipayNotifyUrlPc(String alipayNotifyUrlPc) {
        this.alipayNotifyUrlPc = alipayNotifyUrlPc;
    }

    public String getAlipayReturnUrlPc() {
        return alipayReturnUrlPc;
    }

    public void setAlipayReturnUrlPc(String alipayReturnUrlPc) {
        this.alipayReturnUrlPc = alipayReturnUrlPc;
    }

    public String getAlipaySignType() {
        return alipaySignType;
    }

    public void setAlipaySignType(String alipaySignType) {
        this.alipaySignType = alipaySignType;
    }

    public String getAlipayInputCharset() {
        return alipayInputCharset;
    }

    public void setAlipayInputCharset(String alipayInputCharset) {
        this.alipayInputCharset = alipayInputCharset;
    }

    public String getAlipayGatewayUrl() {
        return alipayGatewayUrl;
    }

    public void setAlipayGatewayUrl(String alipayGatewayUrl) {
        this.alipayGatewayUrl = alipayGatewayUrl;
    }
}
