package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdPlatformService {

	@RequestMapping(method = RequestMethod.GET, value = "adPlatform/selectByProductIdAndStatus")
	Result<Boolean> selectByProductIdAndStatus(@RequestParam("productId") Long productId);

}
