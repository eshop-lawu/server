package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.AppVersionDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "mall-srv")
public interface AppVersionService {

	
	@RequestMapping(value = "appVersion/getAppVersion/{appType}", method = RequestMethod.GET)
    public Result<AppVersionDTO> getAppVersion(@PathVariable("appType") byte appType,@RequestParam("mobileType") byte mobileType);
	
}
