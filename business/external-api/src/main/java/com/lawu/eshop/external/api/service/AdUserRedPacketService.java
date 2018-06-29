/**
 * 
 */
package com.lawu.eshop.external.api.service;

import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yqh
 * @date 2017年8月3日
 */
@FeignClient(value = "ad-srv")
public interface AdUserRedPacketService {

	/**
	 * 获取需要充值的金额
	 *
	 * @param redPacketId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "userRedPacket/selectUserRedPacketInfoForThrid")
	Result<ThirdPayCallBackQueryPayOrderDTO> selectUserRedPacketInfoForThrid(@RequestParam("redPacketId") Long redPacketId);

}
