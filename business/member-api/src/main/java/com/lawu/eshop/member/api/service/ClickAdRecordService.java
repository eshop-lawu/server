package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface ClickAdRecordService {
	
	
	@RequestMapping(value = "clickAdRecord/setClickAdRecord", method = RequestMethod.POST)
    Result setClickAdRecord(@RequestParam("key") String key);
	
	
	@RequestMapping(value = "clickAdRecord/getClickAdRecord", method = RequestMethod.GET)
    Result<Boolean> getClickAdRecord(@RequestParam("key") String key);
	
	
}
