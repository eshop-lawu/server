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
 * @date 2017年4月13日 下午1:47:03
 *
 */
public interface IServiceRequest {

    //Service依赖的底层https请求器必须实现这么一个接口
    public String sendPost(String api_url, Object xmlObj, JsonResult jsonResult, WxPayConfigParam wxPayConfigParam) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException;

}
