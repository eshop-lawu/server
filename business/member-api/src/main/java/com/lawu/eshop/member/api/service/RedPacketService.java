package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.RedPacketDTO;
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
	 * 领取红包
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value = "redPacket/getRedPacket", method = RequestMethod.GET)
    public Result<RedPacketDTO> getRedPacket(@RequestParam("merchantId")  Long  merchantId,@RequestParam("memberId")  Long  memberId,@RequestParam("memberNum") String memberNum);

}
