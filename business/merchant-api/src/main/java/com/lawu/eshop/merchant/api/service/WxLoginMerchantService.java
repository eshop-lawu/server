package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.user.param.WxLoginMerchantParam;
import com.lawu.framework.web.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信登录
 * 
 */
@FeignClient(value = "user-srv")
public interface WxLoginMerchantService {
	
	/**
	 * 保存微信用户信息
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "wxLoginMerchant/wxLoginMerchantSave", method = RequestMethod.POST)
	Result wxLoginMerchantSave(@RequestBody WxLoginMerchantParam param);
}
