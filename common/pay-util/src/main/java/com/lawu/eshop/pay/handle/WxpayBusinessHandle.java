package com.lawu.eshop.pay.handle;

import com.lawu.eshop.pay.ThirdPayRefundParam;
import com.lawu.eshop.pay.WxMmpaymkttransfersParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.WXPay;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.MmpaymkttransfersReqData;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.RefundReqData;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;

import java.math.BigDecimal;

/**
 * <p>
 * Description: 微信支付对接业务
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月14日 下午3:25:25
 */
public class WxpayBusinessHandle {

    /**
     * 微信退款
     *
     * @param rparam     参数
     * @param jsonResult 返回参数
     * @throws Exception
     */
    public static void refund(ThirdPayRefundParam rparam, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {
        double refundMoney = Double.parseDouble(rparam.getRefundMoney());
        double totalMoney = Double.parseDouble(rparam.getTotalMoney());
        int refundMoneyInt = (int) (refundMoney * 100);
        int totalMoneyInt = (int) (totalMoney * 100);
        RefundReqData refundReqData = new RefundReqData(wxPayConfigParam.getWxpayAppIdMember(), wxPayConfigParam.getWxpayMchIdMember(), rparam.getTradeNo(), rparam.getRefundId(), totalMoneyInt, refundMoneyInt, wxPayConfigParam.getWxpayMchIdMember(), wxPayConfigParam.getWxpayKeyApp());
        WXPay.requestRefundService(refundReqData, jsonResult, wxPayConfigParam);
    }

    /**
     * 企业付款
     * @param wxMmpaymkttransfersParam
     * @param jsonResult
     * @param wxPayConfigParam
     * @throws Exception
     */
    public static void mmpaymkttransfers(WxMmpaymkttransfersParam wxMmpaymkttransfersParam, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {
        BigDecimal bAmount = new BigDecimal(wxMmpaymkttransfersParam.getAmount());
        bAmount = bAmount.setScale(2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
        int amoutFen = bAmount.intValue();
        MmpaymkttransfersReqData reqData = new MmpaymkttransfersReqData(wxPayConfigParam.getWxpayAppIdMember(), wxPayConfigParam.getWxpayMchIdMember(), wxMmpaymkttransfersParam.getPartnerTradeNo(), wxMmpaymkttransfersParam.getOpenid(), wxMmpaymkttransfersParam.getReUserName(), amoutFen, wxMmpaymkttransfersParam.getDesc(), wxPayConfigParam.getWxpayKeyApp());
        WXPay.requestMmpaymkttransfersService(reqData, jsonResult, wxPayConfigParam);
    }

}
