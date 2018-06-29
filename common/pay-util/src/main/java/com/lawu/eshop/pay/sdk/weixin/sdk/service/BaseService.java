package com.lawu.eshop.pay.sdk.weixin.sdk.service;

import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月13日 下午1:44:33
 *
 */
@SuppressWarnings("rawtypes")
public class BaseService{

    //API的地址
    private String apiURL;

    //发请求的HTTPS请求器
    private IServiceRequest serviceRequest;

	public BaseService(String api, String httpsRequestClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        apiURL = api;
        Class c = Class.forName(httpsRequestClassName);
        serviceRequest = (IServiceRequest) c.newInstance();
    }

    protected String sendPost(Object xmlObj, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return serviceRequest.sendPost(apiURL,xmlObj,jsonResult, wxPayConfigParam);
    }

    /**
     * 供商户想自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(IServiceRequest request){
        serviceRequest = request;
    }
}
