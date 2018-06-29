package com.lawu.eshop.ad.srv.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface PraiseDoHanlderMinusPointService {
	
	@RequestMapping(value = "praiseDoHanlderMinusPoint/getAdPraiseIsDoPointRecord", method = RequestMethod.GET)
    Result getAdPraiseIsDoPointRecord(@RequestParam("adIdAndMemberId") String adIdAndMemberId); 

}
