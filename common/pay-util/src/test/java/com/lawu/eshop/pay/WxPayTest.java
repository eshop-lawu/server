package com.lawu.eshop.pay;

import com.lawu.eshop.pay.handle.WxpayBusinessHandle;
import com.lawu.eshop.pay.param.BizTypeEnum;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import org.junit.Ignore;
import org.junit.Test;

public class WxPayTest {

    @Ignore
    @Test
    public void refund() {
        WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
        wxPayConfigParam.setWxpayAppIdMember("wxe344e8e87db09469");
        wxPayConfigParam.setWxpayMchIdMember("1432662602");
        wxPayConfigParam.setWxpayCertLocalPathMember("/BOOT-INF/classes/apiclient_cert-member.p12");
        wxPayConfigParam.setWxpayCertPasswordMember("1432662602");
        wxPayConfigParam.setWxpayRefundApi("https://api.mch.weixin.qq.com/secapi/pay/refund");
        wxPayConfigParam.setWxpayHttpsRequestClassName("com.lawu.eshop.pay.sdk.weixin.sdk.common.HttpsRequest");
        wxPayConfigParam.setWxpayKeyApp("lovechuanmeiAppAndroidIos2017011");
        wxPayConfigParam.setBizTypeEnum(BizTypeEnum.REFUND);

        ThirdPayRefundParam rparam = new ThirdPayRefundParam();
        rparam.setRefundId("T10000000001");
        rparam.setRefundMoney("0.01");
        rparam.setTradeNo("R10000000001");
        rparam.setTotalMoney("0.01");

        JsonResult jsonResultTransfer = new JsonResult();
        try {
            System.out.println("调用退款接口...");
            WxpayBusinessHandle.refund(rparam, jsonResultTransfer, wxPayConfigParam);
        } catch (Exception e) {
            jsonResultTransfer.setSuccess(false);
            jsonResultTransfer.setMessage(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(jsonResultTransfer.isSuccess() + "-" + jsonResultTransfer.getMessage());
    }

    /**
     * 微信提现测试账号：18723437022，密码：123456，支付密码：123456 海文军 oMD71vsiH6G_7h2R00RtnM3LKIDE
     */
    @Ignore
    @Test
    public void mmpaymkttransfers() {
        WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
//        wxPayConfigParam.setWxpayAppIdMember("wxe344e8e87db09469");
//        wxPayConfigParam.setWxpayMchIdMember("1432662602");
//        wxPayConfigParam.setWxpayCertLocalPathMember("/BOOT-INF/classes/apiclient_cert-member.p12");
//        wxPayConfigParam.setWxpayCertPasswordMember("1432662602");

        wxPayConfigParam.setWxpayAppIdMember("wx6982532f330f0296");
        wxPayConfigParam.setWxpayMchIdMember("1432956402");
        wxPayConfigParam.setWxpayCertLocalPathMember("/BOOT-INF/classes/apiclient_cert-business_app.p12");
        wxPayConfigParam.setWxpayCertPasswordMember("1432956402");

        wxPayConfigParam.setWxpayHttpsRequestClassName("com.lawu.eshop.pay.sdk.weixin.sdk.common.HttpsRequest");
        wxPayConfigParam.setWxpayKeyApp("lovechuanmeiAppAndroidIos2017011");
        wxPayConfigParam.setWxpayMmpaymkttransfersApi("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
        wxPayConfigParam.setBizTypeEnum(BizTypeEnum.MMPAY);

        WxMmpaymkttransfersParam wxMmpaymkttransfersParam = new WxMmpaymkttransfersParam();
        wxMmpaymkttransfersParam.setPartnerTradeNo("C100000000100001");
        wxMmpaymkttransfersParam.setOpenid("oMD71vsiH6G_7h2R00RtnM3LKIDE");
        wxMmpaymkttransfersParam.setReUserName("海文军11");
        wxMmpaymkttransfersParam.setAmount("1.0001");
        wxMmpaymkttransfersParam.setDesc("提现-测试");

        JsonResult jsonResultTransfer = new JsonResult();
        try {
            System.out.println("调用企业付款接口...");
            WxpayBusinessHandle.mmpaymkttransfers(wxMmpaymkttransfersParam, jsonResultTransfer, wxPayConfigParam);
        } catch (Exception e) {
            jsonResultTransfer.setSuccess(false);
            jsonResultTransfer.setMessage(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(jsonResultTransfer.isSuccess() + "-" + jsonResultTransfer.getMessage());
    }


}
