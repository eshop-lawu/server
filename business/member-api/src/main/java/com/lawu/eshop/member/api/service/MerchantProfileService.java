package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.user.dto.MerchantProfileDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "user-srv")
public interface MerchantProfileService {
	
	@RequestMapping(value = "merchantProfile/getMerchantProfile", method = RequestMethod.GET)
	public Result<MerchantProfileDTO> getMerchantProfile(@RequestParam("merchantId") Long merchantId);

}
