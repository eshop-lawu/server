package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.cache.dto.SysMaintainConfigDTO;
import com.lawu.eshop.cache.param.SysMaintainConfigParam;
import com.lawu.framework.web.Result;

/** 
 * 
 * @author lihj
 * @date 2018年5月10日
 */
@FeignClient(value = "cache-srv", path = "sysMaintainCache/")
public interface SysMaintainConfigService {

	@RequestMapping(value="getSysMaintainConfig",method=RequestMethod.GET)
	Result<SysMaintainConfigDTO> getSysMaintainConfig();
	
	@RequestMapping(value="updateSysMaintainConfig",method=RequestMethod.POST)
	Result updateSysMaintainConfig(@RequestBody SysMaintainConfigParam param);
	
}
