package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/03/31
 *
 */
@FeignClient(value = "product-srv")
public interface FavoriteProductService {
	
	@RequestMapping(method = RequestMethod.POST, value = "favoriteProduct/save")
	Result save(@RequestParam("memberId") Long memberId,@RequestParam("productId") Long productId);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "favoriteProduct/remove/{productId}")
    Result remove(@PathVariable("productId") Long productId,@RequestParam("memberId") Long memberId);
	
	@RequestMapping(method = RequestMethod.GET, value = "favoriteProduct/selectMyFavoriteProduct")
	Result<Page<FavoriteProductDTO>> selectMyFavoriteProduct(@RequestParam("memberId") Long id, @RequestBody FavoriteProductQuery query);

	@RequestMapping(method = RequestMethod.GET, value = "favoriteProduct/getUserFavorite/{productId}")
	boolean getUserFavorite(@PathVariable("productId") Long productId,@RequestParam("memberId") Long memberId);

	@RequestMapping(value = "favoriteProduct/getFavoriteProductCount", method = RequestMethod.GET)
	Result<Integer> getFavoriteProductCount(@RequestParam("memberId") Long memberId);

}
