package com.lawu.eshop.operator.api.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.PlatformRedPacketDTO;
import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.framework.core.page.Page;
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
	 * 设置平台红包
	 * @param param
	 */
	@RequestMapping(method = RequestMethod.POST, value = "platformRedPacket/saveRedPacket")
	Result saveRedPacket(@RequestBody PlatformRedPacketParam param);
	
	/**
	 * 禁用平台红包
	 * @param id
	 * @param auditorId
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "platformRedPacket/setRedPacket/{id}")
	Result setRedPacket(@PathVariable("id") Long id,@RequestParam("auditorId") Long auditorId);
	
	/**
	 * 平台列表查询
	 * @param query
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "platformRedPacket/queryRedPacket")
	Result<Page<PlatformRedPacketDTO>> queryRedPacket(@RequestBody PlatformRedPacketQueryParam query);
	
	

}
