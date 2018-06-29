package com.lawu.eshop.jobs.service;

import java.util.Set;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface AdViewService {
	
	@RequestMapping(value = "adView/getAdviews", method = RequestMethod.GET)
	Result<Set<String>> getAdviews(@RequestParam("adId") String adId);

}
