package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.FavoriteMerchantDTO;
import com.lawu.eshop.user.srv.bo.FavoriteMerchantBO;
import com.lawu.eshop.user.srv.domain.extend.FavoriteMerchantDOView;

/**
 * 我的商家收藏转化
 * @author zhangrc
 *
 */
public class FavoriteMerchantConverter {
	
	public static FavoriteMerchantBO convertListBO(FavoriteMerchantDOView favoriteMerchantDOView) {
		if (favoriteMerchantDOView==null) {
	       return null;
	    }
		FavoriteMerchantBO FBO=new FavoriteMerchantBO();
		FBO.setMerchantId(favoriteMerchantDOView.getMerchantId());
		FBO.setName(favoriteMerchantDOView.getName());
		FBO.setIndustryName(favoriteMerchantDOView.getIndustryName());
		FBO.setFeedbackRate(favoriteMerchantDOView.getFeedbackRate());
		FBO.setPath(favoriteMerchantDOView.getPath());
		FBO.setMerchantStoreId(favoriteMerchantDOView.getMerchantStoreId());
		FBO.setFansCount(favoriteMerchantDOView.getCountFs());
		return FBO;
	}
	
	public static FavoriteMerchantDTO convertDTO(FavoriteMerchantBO favoriteMerchantBO) {
    	
        if (favoriteMerchantBO == null) {
            return null;
        }  
        FavoriteMerchantDTO dto=new FavoriteMerchantDTO();
        dto.setMerchantId(favoriteMerchantBO.getMerchantId());
        dto.setName(favoriteMerchantBO.getName());
        dto.setFeedbackRate(favoriteMerchantBO.getFeedbackRate());
        dto.setIndustryName(favoriteMerchantBO.getIndustryName());
        dto.setPath(favoriteMerchantBO.getPath());
        dto.setDistance(favoriteMerchantBO.getDistance());
        dto.setMerchantStoreId(favoriteMerchantBO.getMerchantStoreId());
        dto.setFansCount(favoriteMerchantBO.getFansCount());
        return dto;
    }
	
}
