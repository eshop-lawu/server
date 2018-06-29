package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantProfileDTO;
import com.lawu.eshop.user.srv.bo.MerchantProfileBO;
import com.lawu.eshop.user.srv.domain.MerchantProfileDO;

public class MerchantProfileConverter {
	
	/**
	 * BO转换
	 *
	 * @param merchantProfileDO
	 * @return
	 */
	public static MerchantProfileBO convertBO(MerchantProfileDO merchantProfileDO) {
		
		MerchantProfileBO merchantProfileBO = new MerchantProfileBO();
		if (merchantProfileDO == null) {
			return merchantProfileBO;
		}
		merchantProfileBO.setJdUrl(merchantProfileDO.getJdUrl());
		merchantProfileBO.setTaobaoUrl(merchantProfileDO.getTaobaoUrl());
		merchantProfileBO.setTmallUrl(merchantProfileDO.getTmallUrl());
		merchantProfileBO.setWebsiteUrl(merchantProfileDO.getWebsiteUrl());
		return merchantProfileBO;
	}
	
	
	/**
	 * BO转换
	 *
	 * @param addressDO
	 * @return
	 */
	public static MerchantProfileDTO convertDTO(MerchantProfileBO merchantProfileBO) {
		
		MerchantProfileDTO merchantProfileDTO = new MerchantProfileDTO();
		if (merchantProfileBO == null) {
			return merchantProfileDTO;
		}
		merchantProfileDTO.setJdUrl(merchantProfileBO.getJdUrl());
		merchantProfileDTO.setTaobaoUrl(merchantProfileBO.getTaobaoUrl());
		merchantProfileDTO.setTmallUrl(merchantProfileBO.getTmallUrl());
		merchantProfileDTO.setWebsiteUrl(merchantProfileBO.getWebsiteUrl());
		return merchantProfileDTO;
	}

}
