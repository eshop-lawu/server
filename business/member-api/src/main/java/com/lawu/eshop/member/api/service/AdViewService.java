package com.lawu.eshop.member.api.service;

import java.util.Set;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.AdSeeDetailDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "cache-srv")
public interface AdViewService {

	@RequestMapping(value = "adView/setAdView", method = RequestMethod.GET)
	Result setAdView(@RequestParam("adId") String adId, @RequestParam("memberId") String memberId);


	@RequestMapping(value = "adView/getAdviews", method = RequestMethod.GET)
	Result<Set<String>> getAdviews(@RequestParam("adId") String adId);
	
	/**
	 * 用户看广告记录
	 * 
	 * @param adId
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "adView/setMemeberSeeDetail", method = RequestMethod.GET)
    Result<AdSeeDetailDTO> setMemeberSeeDetail(@RequestParam("adId") String adId, @RequestParam("memberId") String memberId);

}
