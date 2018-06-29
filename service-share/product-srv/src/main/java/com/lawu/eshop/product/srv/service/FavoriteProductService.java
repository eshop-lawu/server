package com.lawu.eshop.product.srv.service;

import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.framework.core.page.Page;

/**
 * 商品收藏接口
 * @author zhangrc
 * @date 2047/03/30
 *
 */
public interface FavoriteProductService {
	
	/**
	 * 商品收藏
	 * @param memberId
	 * @param productId
	 * @return
	 */
	Integer save(Long memberId,Long productId);
	
	/**
	 * 取消收藏
	 * @param id
	 * @return
	 */
	Integer remove(Long productId,Long memberId);
	
	/**
	 * 我收藏的商品
	 * @param memberId
	 * @param query
	 * @return
	 */
	Page<FavoriteProductBO> selectMyFavoriteProduct(Long memberId ,FavoriteProductQuery query);

	Integer getUserFavorite(Long productId, Long memberId);


    Integer getFavoriteProductCount(Long memberId);
}
