/**
 * 
 */
package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author lihj
 * @date 2017年8月7日
 */

@FeignClient(value = "ad-srv")
public interface UserRedPacketJobService {

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "userRedPacket/executeUserRedPacketData", method = RequestMethod.POST)
	Result executeUserRedPacketData(@RequestParam("pageSize") Integer pageSize);
}
