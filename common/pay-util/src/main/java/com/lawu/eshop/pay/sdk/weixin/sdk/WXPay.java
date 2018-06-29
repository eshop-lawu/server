package com.lawu.eshop.pay.sdk.weixin.sdk;

import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.MmpaymkttransfersReqData;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.RefundReqData;
import com.lawu.eshop.pay.sdk.weixin.sdk.service.MmpaymkttransfersService;
import com.lawu.eshop.pay.sdk.weixin.sdk.service.RefundService;

/**
 * <p>
 * Description: 微信客户端接口api
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月13日 下午1:47:17
 */
public class WXPay {

    /**
     * 请求退款服务
     *
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param jsonResult
     * @return API返回的XML数据
     * @throws Exception
     */
    public static String requestRefundService(RefundReqData refundReqData, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {
        return new RefundService(wxPayConfigParam.getWxpayRefundApi(), wxPayConfigParam.getWxpayHttpsRequestClassName()).request(refundReqData, jsonResult, wxPayConfigParam);
    }

    /**
     * 请求企业付款服务
     *
     * @param reqData
     * @param jsonResult
     * @param wxPayConfigParam
     * @return
     * @throws Exception
     */
    public static String requestMmpaymkttransfersService(MmpaymkttransfersReqData reqData, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {
        return new MmpaymkttransfersService(wxPayConfigParam.getWxpayMmpaymkttransfersApi(), wxPayConfigParam.getWxpayHttpsRequestClassName()).request(reqData, jsonResult, wxPayConfigParam);
    }
}
