package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface PraiseDoHanlderMinusPointService {
	
	@RequestMapping(value = "praiseDoHanlderMinusPoint/setAdPraiseIsDoPointRecord", method = RequestMethod.GET)
    Result setAdPraiseIsDoPointRecord(@RequestParam("adIdAndMemberId") String adIdAndMemberId); 

}
