package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.dto.FavoriteAdCountDTO;
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.param.FavoriteAdParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/04/8
 *
 */
@FeignClient(value = "ad-srv")
public interface FavoriteAdService {
	
	@RequestMapping(method = RequestMethod.PUT, value = "favoriteAd/save")
	Result save(@RequestParam("memberId") Long memberId,@RequestParam("adId") Long adId,@RequestParam("userNum") String userNum);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "favoriteAd/remove/{adId}")
    Result remove(@PathVariable("adId") Long adId,@RequestParam("memberId") Long memberId);
	
	@RequestMapping(method = RequestMethod.GET, value = "favoriteAd/selectMyFavoriteAd")
	Result<Page<FavoriteAdDOViewDTO>> selectMyFavoriteAd(@RequestParam("memberId") Long id, @RequestBody FavoriteAdParam param);

	@RequestMapping(value = "favoriteAd/isFavoriteAd", method = RequestMethod.GET)
    Result<Boolean> isFavoriteAd(@RequestParam("adId") Long adId,@RequestParam("memberId") Long memberId);

	@RequestMapping(value = "favoriteAd/getFavoriteAdCount", method = RequestMethod.GET)
	Result<FavoriteAdCountDTO> getFavoriteAdCount(@RequestParam("memberId") Long memberId);
}
