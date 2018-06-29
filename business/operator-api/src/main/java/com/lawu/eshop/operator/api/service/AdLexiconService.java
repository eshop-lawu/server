package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv")
public interface AdLexiconService {
	
	/**
	 * 添加广告词库
	 * @param adPlatformParam
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "adLexicon/save")
	Result save(@RequestParam("title") String title);

}
