package com.lawu.eshop.member.api.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.GetPlatformRedPacketDTO;
import com.lawu.framework.web.Result;

/**
 * 平台红包接口
 * 
 * @author zhangrc
 * @date 2017/10/18
 *
 */
@FeignClient(value = "ad-srv")
public interface PlatformRedPacketService {
	
	/**
	 * 领取平台红包
	 * @param userNum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "platformRedPacket/getRedPacket")
	Result<GetPlatformRedPacketDTO> getRedPacket(@RequestParam("userNum") String userNum);
	
	

}
