package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * <p>
 * Description: 系统参数
 * </p>
 * @author Yangqh
 * @date 2017年4月5日 下午7:02:03
 *
 */
@FeignClient(value= "mall-srv")
public interface PropertyService {
	
	@RequestMapping(method = RequestMethod.GET, value = "property/getValue")
	String getValue(@RequestParam("name") String name);
}
