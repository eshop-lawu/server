package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.AdLexiconDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "ad-srv", path = "adLexicon/")
public interface AdLexiconService {

	/**
	 * 根据不同的位置查询不同的广告
	 * @param positionEnum
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "selectList")
	Result<List<AdLexiconDTO>> selectList(@RequestParam("adId") Long  adId);
	
}
