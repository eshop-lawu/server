package com.lawu.eshop.external.api.service;

import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商家发广告
 */
@FeignClient(value= "property-srv")
public interface PropertyinfoAdService {

	/**
	 * 处理第三方发广告（E咻&红包）回调
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "properinfoAd/doHandleMerchantAdNotify")
	Result doHandleMerchantAdNotify(@RequestBody NotifyCallBackParam param);
}
