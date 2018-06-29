package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;
/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月4日
 */
@FeignClient(value = "activity-srv" , path="richConfig/")
public interface RichConfigService {
	
	@RequestMapping(value = "effectiveJob", method = RequestMethod.GET)
	Result effectiveJob();
	
	
	@RequestMapping(value = "effectivePowerJob", method = RequestMethod.GET)
	Result effectivePowerJob();

}
