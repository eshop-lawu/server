package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface AdCountCacheService {
	
	/**
	 * 广告总数存入缓存
	 * @param id
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "adCount/setAdCountRecord", method = RequestMethod.GET)
    Result setAdCountRecord(@RequestParam("id") Long id, @RequestParam("count") Integer count);
	

}
