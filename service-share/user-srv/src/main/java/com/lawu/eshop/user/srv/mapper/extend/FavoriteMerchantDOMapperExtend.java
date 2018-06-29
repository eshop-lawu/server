package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.user.srv.domain.extend.FavoriteMerchantDOView;

public interface FavoriteMerchantDOMapperExtend {
	
	List<FavoriteMerchantDOView> selectFavoriteMerchantByRowbounds(FavoriteMerchantDOView favoriteMerchantDOView,RowBounds rowBounds);

	List<FavoriteMerchantDOView> selectFansMerchantByRowbounds(FavoriteMerchantDOView favoriteMerchantDOView,RowBounds rowBounds);

}
