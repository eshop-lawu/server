package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 系统参数
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午6:34:39
 *
 */
@FeignClient(value= "property-srv")
public interface PropertySrvPropertyService {
	
	@RequestMapping(value = "property/getValue", method = RequestMethod.GET)
	Result getValue(@RequestParam("name") String name);
}
