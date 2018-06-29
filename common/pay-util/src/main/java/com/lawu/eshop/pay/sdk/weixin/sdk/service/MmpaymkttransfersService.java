package com.lawu.eshop.pay.sdk.weixin.sdk.service;

import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.pay.sdk.weixin.sdk.protocol.MmpaymkttransfersReqData;


public class MmpaymkttransfersService extends BaseService{

    public MmpaymkttransfersService(String refund_api, String httpsRequestClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(refund_api, httpsRequestClassName);
    }

    /**
     * 请求企业付款服务
     * @param reqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @param jsonResult 
     * @return API返回的XML数据
     * @throws Exception
     */
    public String request(MmpaymkttransfersReqData reqData, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(reqData,jsonResult, wxPayConfigParam);

        return responseString;
    }

}
