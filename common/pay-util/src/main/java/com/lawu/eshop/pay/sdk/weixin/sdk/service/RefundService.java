package com.lawu.eshop.pay.sdk.weixin.sdk.service;

import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.RefundReqData;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月13日 下午1:47:09
 *
 */
public class RefundService extends BaseService{

    public RefundService(String refund_api, String httpsRequestClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(refund_api, httpsRequestClassName);
    }

    /**
     * 请求退款服务
     * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param jsonResult 
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(RefundReqData refundReqData, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(refundReqData,jsonResult, wxPayConfigParam);

        return responseString;
    }

}
