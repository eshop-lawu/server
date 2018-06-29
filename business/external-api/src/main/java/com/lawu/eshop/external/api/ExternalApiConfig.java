package com.lawu.eshop.external.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author meishuquan
 * @date 2017/5/4
 */
@Component
public class ExternalApiConfig {

    //支付宝支付参数
    @Value(value = "${alipay_partner}")
    private String alipayPartner;

    @Value(value = "${alipay_app_id_member}")
    private String alipayAppIdMember;

    @Value(value = "${alipay_app_id_business}")
    private String alipayAppIdBusiness;

    @Value(value = "${alipay_public_key}")
    private String alipayPublicKey;

    @Value(value = "${alipay_edian_member_public_key}")
    private String alipayEdianMemberPublicKey;

    @Value(value = "${alipay_edian_business_public_key}")
    private String alipayEdianBusinessPublicKey;

    @Value(value = "${alipay_sign_type}")
    private String alipaySignType;

    @Value(value = "${alipay_input_charset}")
    private String alipayInputCharset;

    @Value(value = "${alipay_https_verify_url}")
    private String alipayHttpsVerifyUrl;


    //微信支付参数
    @Value(value = "${wxpay_key}")
    private String wxpayKey;

    @Value(value = "${wxpay_key_app}")
    private String wxpayKeyApp;

    public String getAlipayPartner() {
        return alipayPartner;
    }

    public String getAlipayAppIdMember() {
        return alipayAppIdMember;
    }

    public String getAlipayAppIdBusiness() {
        return alipayAppIdBusiness;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public String getAlipayEdianMemberPublicKey() {
        return alipayEdianMemberPublicKey;
    }

    public String getAlipayEdianBusinessPublicKey() {
        return alipayEdianBusinessPublicKey;
    }

    public String getAlipaySignType() {
        return alipaySignType;
    }

    public String getAlipayInputCharset() {
        return alipayInputCharset;
    }

    public String getAlipayHttpsVerifyUrl() {
        return alipayHttpsVerifyUrl;
    }

    public String getWxpayKey() {
        return wxpayKey;
    }

    public String getWxpayKeyApp() {
        return wxpayKeyApp;
    }
}
