package com.lawu.eshop.ad.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.FavoriteTypeEnum;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.dto.FavoriteAdDOViewDTO;
import com.lawu.eshop.ad.srv.bo.FavoriteAdDOViewBO;
import com.lawu.eshop.ad.srv.domain.extend.FavoriteAdDOView;

public class FavoriteAdConverter {
	
	/**
	 * DO转BO
	 * @param favoriteAdDOView
	 * @return
	 */
	public static FavoriteAdDOViewBO convertBO(FavoriteAdDOView favoriteAdDOView) {
        if (favoriteAdDOView == null) {
            return null;
        }
        FavoriteAdDOViewBO favoriteProductBO=new FavoriteAdDOViewBO();
        favoriteProductBO.setAdId(favoriteAdDOView.getAdId());
        favoriteProductBO.setContent(favoriteAdDOView.getContent());
        favoriteProductBO.setId(favoriteAdDOView.getId());
        favoriteProductBO.setMediaUrl(favoriteAdDOView.getMediaUrl());
        favoriteProductBO.setMerchantId(favoriteAdDOView.getMerchantId());
        favoriteProductBO.setTitle(favoriteAdDOView.getTitle());
        favoriteProductBO.setStatusEnum(AdStatusEnum.getEnum(favoriteAdDOView.getStatus()));
        favoriteProductBO.setTypeEnum(FavoriteTypeEnum.getEnum(favoriteAdDOView.getType()));
        favoriteProductBO.setVideoImgUrl(favoriteAdDOView.getVideoImgUrl());
        favoriteProductBO.setIsPraise(favoriteAdDOView.getCount()>0);
        favoriteProductBO.setPraiseType(PraiseTypeEnum.getEnum(favoriteAdDOView.getPraiseType()));
        return favoriteProductBO;
    }
	
	/**
	 * DOS 转BOS
	 * @param listPM
	 * @return
	 */
	public static List<FavoriteAdDOViewBO> convertBOS(List<FavoriteAdDOView> favoriteAdDOViews) {
		List<FavoriteAdDOViewBO> BOS=new ArrayList<FavoriteAdDOViewBO>();
        if (favoriteAdDOViews == null) {
            return null;
        }
        for (FavoriteAdDOView favoriteAdDOView: favoriteAdDOViews) {
        	FavoriteAdDOViewBO ViewBO=convertBO(favoriteAdDOView);
        	BOS.add(ViewBO);
		}
        
        return BOS;
    }
	
	/**
	 * BO转DTO
	 * @param favoriteProductBO
	 * @return
	 */
	public static FavoriteAdDOViewDTO convertDTO(FavoriteAdDOViewBO favoriteAdDOViewBO ) {
		if (favoriteAdDOViewBO == null) {
            return null;
        }
		FavoriteAdDOViewDTO favoriteAdDOViewDTO=new FavoriteAdDOViewDTO();
		favoriteAdDOViewDTO.setAdId(favoriteAdDOViewBO.getAdId());
		favoriteAdDOViewDTO.setContent(favoriteAdDOViewBO.getContent());
		favoriteAdDOViewDTO.setId(favoriteAdDOViewBO.getId());
		favoriteAdDOViewDTO.setMediaUrl(favoriteAdDOViewBO.getMediaUrl());
		favoriteAdDOViewDTO.setMerchantId(favoriteAdDOViewBO.getMerchantId());
		favoriteAdDOViewDTO.setTitle(favoriteAdDOViewBO.getTitle());
		favoriteAdDOViewDTO.setStatusEnum(favoriteAdDOViewBO.getStatusEnum());
		favoriteAdDOViewDTO.setTypeEnum(favoriteAdDOViewBO.getTypeEnum());
		favoriteAdDOViewDTO.setVideoImgUrl(favoriteAdDOViewBO.getVideoImgUrl());
		favoriteAdDOViewDTO.setIsPraise(favoriteAdDOViewBO.getIsPraise());
		favoriteAdDOViewDTO.setPraiseType(favoriteAdDOViewBO.getPraiseType());
        return favoriteAdDOViewDTO;
    }
	
	/**
	 * BOS 转DTOS
	 * @param favoriteProductBOS
	 * @return
	 */
	public static List<FavoriteAdDOViewDTO> convertDTOS(List<FavoriteAdDOViewBO> favoriteAdDOViewBOS ) {
        if (favoriteAdDOViewBOS == null) {
            return null;
        }
        List<FavoriteAdDOViewDTO> DTOS=new ArrayList<FavoriteAdDOViewDTO>();
        for (FavoriteAdDOViewBO favoriteProductBO: favoriteAdDOViewBOS) {
        	DTOS.add(convertDTO(favoriteProductBO));
		}
        return DTOS;
    }


}
