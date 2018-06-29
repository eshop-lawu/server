package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.ad.dto.RedPacketDTO;
import com.lawu.eshop.member.api.service.RedPacketService;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockRedPacketService implements RedPacketService {


	@Override
	public Result<RedPacketDTO> getRedPacket(@RequestParam("merchantId") Long merchantId, @RequestParam("memberId") Long memberId, @RequestParam("memberNum") String memberNum) {
		return null;
	}
}
