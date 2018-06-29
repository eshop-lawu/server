package com.lawu.eshop.ad.srv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface AdCountRecordService {
	
	@RequestMapping(value = "adCount/getAdCountRecord", method = RequestMethod.GET)
    Result<Object> getAdCountRecord(@RequestParam("id") Long id);
	
	
	@RequestMapping(value = "userRedPacketCount/getUserRedPacketCountRecord", method = RequestMethod.GET)
	Result<Object> getUserRedPacketCountRecord(@RequestParam("id") Long id);
	 
	 
	@RequestMapping(value = "adCount/getSurplusCount", method = RequestMethod.GET)
	Result<Integer> getSurplusCount(@RequestParam("id") Long id);
	

}
