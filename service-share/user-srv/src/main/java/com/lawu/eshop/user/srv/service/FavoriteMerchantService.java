package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.FavoriteMerchantParam;
import com.lawu.eshop.user.param.FavoriteStoreParam;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.framework.core.page.Page;

/**
 * 商家收藏
 * @author zhangrc
 * @date 2017/3/27
 *
 */
public interface FavoriteMerchantService {
	
	/**
	 * 商家收藏
	 * @param favoriteMerchant
	 * @return
	 */
	Integer save(Long memberId ,FavoriteStoreParam param);
	
	/**
	 * 我收藏的商家
	 * @param memberId
	 * @return
	 */
	Page<FavoriteMerchantBO> getMyFavoriteMerchant(Long memberId,FavoriteMerchantParam pageQuery);

	/**
	 * 取消收藏
	 * @param merchantId
	 * @param memberId
	 * @return
	 */
	Integer remove(FavoriteStoreParam param,Long memberId);
	
	/**
	 * 是否收藏
	 * @param memberId
	 * @param pageQuery
	 * @return
	 */
	Boolean get(Long memberId,FavoriteStoreParam pageQuery);

}
