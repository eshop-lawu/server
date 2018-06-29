package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.FansInviteContentDTO;
import com.lawu.eshop.user.param.FansInviteContentParam;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;
import com.lawu.eshop.user.srv.domain.FansInviteContentDO;

public class FansInviteContentConverter {

	
	public static FansInviteContentDO converterFansInviteContentParam(FansInviteContentParam param) {
		FansInviteContentDO fansInviteContentDO = new FansInviteContentDO();
		if(param == null) {
			return fansInviteContentDO;
		}
		if(param.getLogoUrl() != null)
			fansInviteContentDO.setLogoUrl(param.getLogoUrl());
		if(param.getMerchantStoreIntro() != null)
			fansInviteContentDO.setMerchantStoreIntro(param.getMerchantStoreIntro());
		if(param.getMerchantStoreName() != null)
			fansInviteContentDO.setMerchantStoreName(param.getMerchantStoreName());
		fansInviteContentDO.setInviteContent(param.getInviteContent());
		fansInviteContentDO.setMerchantId(param.getMerchantId());
		fansInviteContentDO.setMerchantNum(param.getMerchantNum());
		fansInviteContentDO.setFansInviteDetailId(param.getFansInviteDetailId());
		fansInviteContentDO.setUrl(param.getUrl());
		return fansInviteContentDO;
	}
	
	public static FansInviteContentBO converterDoToBo(FansInviteContentDO ficDo) {
		FansInviteContentBO fansInviteContentBO = new FansInviteContentBO();
		if(ficDo == null) {
			return fansInviteContentBO;
		}
		fansInviteContentBO.setFansInviteDetailId(ficDo.getFansInviteDetailId());
		fansInviteContentBO.setGmtCreate(ficDo.getGmtCreate());
		fansInviteContentBO.setGmtModified(ficDo.getGmtModified());
		fansInviteContentBO.setId(ficDo.getId());
		fansInviteContentBO.setInviteContent(ficDo.getInviteContent());
		fansInviteContentBO.setLogoUrl(ficDo.getLogoUrl());
		fansInviteContentBO.setMerchantId(ficDo.getMerchantId());
		fansInviteContentBO.setMerchantNum(ficDo.getMerchantNum());
		fansInviteContentBO.setMerchantStoreIntro(ficDo.getMerchantStoreIntro());
		fansInviteContentBO.setMerchantStoreName(ficDo.getMerchantStoreName());
		fansInviteContentBO.setUrl(ficDo.getUrl());
		fansInviteContentBO.setIsOverdue(ficDo.getIsOverdue());
		fansInviteContentBO.setRefuseNumber(ficDo.getRefuseNumber());
		return fansInviteContentBO;
	}
	
	public static FansInviteContentDTO converterBoToDto(FansInviteContentBO ficDo) {
		FansInviteContentDTO fansInviteContentDTO = new FansInviteContentDTO();
		if(ficDo == null) {
			return fansInviteContentDTO;
		}
		fansInviteContentDTO.setFansInviteDetailId(ficDo.getFansInviteDetailId());
		fansInviteContentDTO.setGmtCreate(ficDo.getGmtCreate());
		fansInviteContentDTO.setGmtModified(ficDo.getGmtModified());
		fansInviteContentDTO.setId(ficDo.getId());
		fansInviteContentDTO.setInviteContent(ficDo.getInviteContent());
		fansInviteContentDTO.setLogoUrl(ficDo.getLogoUrl());
		fansInviteContentDTO.setMerchantId(ficDo.getMerchantId());
		fansInviteContentDTO.setMerchantNum(ficDo.getMerchantNum());
		fansInviteContentDTO.setMerchantStoreIntro(ficDo.getMerchantStoreIntro());
		fansInviteContentDTO.setMerchantStoreName(ficDo.getMerchantStoreName());
		fansInviteContentDTO.setUrl(ficDo.getUrl());
		fansInviteContentDTO.setIsOverdue(ficDo.getIsOverdue());
		return fansInviteContentDTO;
	}
}
