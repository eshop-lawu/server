package com.lawu.eshop.property.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/3/13
 */
@Component
public class PropertySrvConfig {

    //支付宝支付参数
    @Value(value = "${alipay_partner}")
    private String alipayPartner;

    @Value(value = "${alipay_seller_id}")
    private String alipaySellerId;

    @Value(value = "${alipay_app_id_member}")
    private String alipayAppIdMember;

    @Value(value = "${alipay_app_id_business}")
    private String alipayAppIdBusiness;

    @Value(value = "${alipay_private_key}")
    private String alipayPrivateKey;

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

    @Value(value = "${alipay_refund_url}")
    private String alipayRefundUrl;

    @Value(value = "${alipay_notify_url}")
    private String alipayNotifyUrl;

    @Value(value = "${alipay_notify_url_pc}")
    private String alipayNotifyUrlPc;

    @Value(value = "${alipay_return_url_pc}")
    private String alipayReturnUrlPc;

    @Value(value = "${alipay_gateway_url}")
    private String alipayGateway;


    //微信支付参数
    @Value(value = "${wxpay_key}")
    private String wxpayKey;

    @Value(value = "${wxpay_mch_id}")
    private String wxpayMchId;

    @Value(value = "${wxpay_app_id}")
    private String wxpayAppId;

    @Value(value = "${wxpay_mch_id_member}")
    private String wxpayMchIdMember;

    @Value(value = "${wxpay_app_id_member}")
    private String wxpayAppIdMember;

    @Value(value = "${wxpay_mch_id_business}")
    private String wxpayMchIdBusiness;

    @Value(value = "${wxpay_app_id_business}")
    private String wxpayAppIdBusiness;

    @Value(value = "${wxpay_cert_password_business_pc}")
    private String wxpayCertPasswordBusinessPc;

    @Value(value = "${wxpay_cert_password_business_app}")
    private String wxpayCertPasswordBusinessApp;

    @Value(value = "${wxpay_cert_password_member}")
    private String wxpayCertPasswordMember;

    @Value(value = "${wxpay_key_app}")
    private String wxpayKeyApp;

    @Value(value = "${wxpay_native_pay_api}")
    private String wxpayNativePayApi;

    @Value(value = "${wxpay_refund_api}")
    private String wxpayRefundApi;

    @Value(value = "${wxpay_https_request_class_name}")
    private String wxpayHttpsRequestClassName;

    @Value(value = "${wxpay_cert_local_path_business_pc}")
    private String wxpayCertLocalPathBusinessPc;

    @Value(value = "${wxpay_cert_local_path_business_app}")
    private String wxpayCertLocalPathBusinessApp;

    @Value(value = "${wxpay_cert_local_path_member}")
    private String wxpayCertLocalPathMember;

    @Value(value = "${wxpay_notify_url}")
    private String wxpayNotifyUrl;

    @Value(value = "${wxpay_notify_url_pc}")
    private String wxpayNotifyUrlPc;

    @Value(value = "${wxpay_ip}")
    private String wxpayIp;

//    @Value(value = "${third.transfer.payer_show_name}")
//    private String payerShowName;
//
//    @Value(value = "${third.transfer.remark}")
//    private String thirdTransferRemark;

    @Value(value = "${cash.max}")
    private String cashMax;

    @Value(value = "${cash.max.wx}")
    private String cashMaxWx;

    @Value(value = "${wxpay_mmpaymkttransfers_api}")
    private String wxpayMmpaymkttransfersApi;

    public String getAlipayPartner() {
        return alipayPartner;
    }

    public String getAlipaySellerId() {
        return alipaySellerId;
    }

    public String getAlipayAppIdMember() {
        return alipayAppIdMember;
    }

    public String getAlipayAppIdBusiness() {
        return alipayAppIdBusiness;
    }

    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public String getAlipayEdianMemberPublicKey() {
        return alipayEdianMemberPublicKey;
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

    public String getAlipayRefundUrl() {
        return alipayRefundUrl;
    }

    public String getAlipayNotifyUrl() {
        return alipayNotifyUrl;
    }

    public String getAlipayNotifyUrlPc() {
        return alipayNotifyUrlPc;
    }

    public String getAlipayReturnUrlPc() {
        return alipayReturnUrlPc;
    }

    public String getWxpayKey() {
        return wxpayKey;
    }

    public String getWxpayMchId() {
        return wxpayMchId;
    }

    public String getWxpayAppId() {
        return wxpayAppId;
    }

    public String getWxpayMchIdMember() {
        return wxpayMchIdMember;
    }

    public String getWxpayAppIdMember() {
        return wxpayAppIdMember;
    }

    public String getWxpayMchIdBusiness() {
        return wxpayMchIdBusiness;
    }

    public String getWxpayAppIdBusiness() {
        return wxpayAppIdBusiness;
    }

    public String getWxpayCertPasswordMember() {
        return wxpayCertPasswordMember;
    }

    public String getWxpayKeyApp() {
        return wxpayKeyApp;
    }

    public String getWxpayNativePayApi() {
        return wxpayNativePayApi;
    }

    public String getWxpayRefundApi() {
        return wxpayRefundApi;
    }

    public String getWxpayHttpsRequestClassName() {
        return wxpayHttpsRequestClassName;
    }

    public String getWxpayCertLocalPathMember() {
        return wxpayCertLocalPathMember;
    }

    public String getWxpayNotifyUrl() {
        return wxpayNotifyUrl;
    }

    public String getWxpayNotifyUrlPc() {
        return wxpayNotifyUrlPc;
    }

    public String getWxpayIp() {
        return wxpayIp;
    }

    public String getAlipayEdianBusinessPublicKey() {
        return alipayEdianBusinessPublicKey;
    }

    public String getAlipayGateway() {
        return alipayGateway;
    }

    public String getWxpayCertPasswordBusinessPc() {
        return wxpayCertPasswordBusinessPc;
    }

    public String getWxpayCertPasswordBusinessApp() {
        return wxpayCertPasswordBusinessApp;
    }

    public String getWxpayCertLocalPathBusinessPc() {
        return wxpayCertLocalPathBusinessPc;
    }

    public String getWxpayCertLocalPathBusinessApp() {
        return wxpayCertLocalPathBusinessApp;
    }

//    public String getPayerShowName() {
//        return payerShowName;
//    }
//
//    public String getThirdTransferRemark() {
//        return thirdTransferRemark;
//    }

    public String getCashMax() {
        return cashMax;
    }

    public String getWxpayMmpaymkttransfersApi() {
        return wxpayMmpaymkttransfersApi;
    }

    public String getCashMaxWx() {
        return cashMaxWx;
    }
}
