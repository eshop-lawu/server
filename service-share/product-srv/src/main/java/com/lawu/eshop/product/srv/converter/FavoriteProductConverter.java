package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.eshop.product.srv.domain.extend.FavoriteProductView;

public class FavoriteProductConverter {
	
	/**
	 * DO转BO
	 * @param favoriteProductView
	 * @return
	 */
	public static FavoriteProductBO convertBO(FavoriteProductView favoriteProductView) {
        if (favoriteProductView == null) {
            return null;
        }
        FavoriteProductBO favoriteProductBO=new FavoriteProductBO();
        favoriteProductBO.setName(favoriteProductView.getName());
        favoriteProductBO.setFeatureImage(favoriteProductView.getFeatureImage());
        return favoriteProductBO;
    }
	
	/**
	 * DOS 转BOS
	 * @param listPM
	 * @return
	 */
	public static List<FavoriteProductBO> convertBOS(List<FavoriteProductView> favoriteProductViews) {
        if (favoriteProductViews == null) {
            return null;
        }
        List<FavoriteProductBO> BOS=new ArrayList<FavoriteProductBO>();
        for (FavoriteProductView favoriteProductExtendDO: favoriteProductViews) {
			FavoriteProductBO favoriteProductBO=new FavoriteProductBO();
			favoriteProductBO.setId(favoriteProductExtendDO.getProductId());
	        favoriteProductBO.setName(favoriteProductExtendDO.getName());
	        favoriteProductBO.setFeatureImage(favoriteProductExtendDO.getFeatureImage());
	        favoriteProductBO.setPrice(favoriteProductExtendDO.getPrice());
	        favoriteProductBO.setContent(favoriteProductExtendDO.getContent());
	        BOS.add(favoriteProductBO);
        	
        	
		}
        
        return BOS;
    }
	
	/**
	 * BO转DTO
	 * @param favoriteProductBO
	 * @return
	 */
	public static FavoriteProductDTO convertDTO(FavoriteProductBO favoriteProductBO ) {
        if (favoriteProductBO == null) {
            return null;
        }
        FavoriteProductDTO bankAccountDTO=new FavoriteProductDTO();
        bankAccountDTO.setId(favoriteProductBO.getId());
        bankAccountDTO.setName(favoriteProductBO.getName());
        bankAccountDTO.setFeatureImage(favoriteProductBO.getFeatureImage());
        bankAccountDTO.setPrice(favoriteProductBO.getPrice());
        bankAccountDTO.setContent(favoriteProductBO.getContent());
        return bankAccountDTO;
    }
	
	/**
	 * BOS 转DTOS
	 * @param favoriteProductBOS
	 * @return
	 */
	public static List<FavoriteProductDTO> convertDTOS(List<FavoriteProductBO> favoriteProductBOS ) {
        if (favoriteProductBOS == null) {
            return null;
        }
        List<FavoriteProductDTO> DTOS=new ArrayList<FavoriteProductDTO>();
        for (FavoriteProductBO favoriteProductBO: favoriteProductBOS) {
        	DTOS.add(convertDTO(favoriteProductBO));
		}
        
        return DTOS;
    }


}
