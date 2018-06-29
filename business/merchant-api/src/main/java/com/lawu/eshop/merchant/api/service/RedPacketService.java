package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.param.RedPacketParam;
import com.lawu.framework.web.Result;

/**
 * 红包接口
 * @author zhangrc
 * @date 2017/4/8
 *
 */
@FeignClient(value = "ad-srv")
public interface RedPacketService {
	
	/**
	 * 創建紅包
	 * @param param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "redPacket/save")
	Result save(@RequestBody RedPacketParam param,@RequestParam("merchantId") Long merchantId,@RequestParam("num") String num);

}
