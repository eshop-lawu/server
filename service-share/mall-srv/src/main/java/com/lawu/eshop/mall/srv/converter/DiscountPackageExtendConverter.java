package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.dto.DiscountPackageDetailForMemberDTO;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;
import com.lawu.eshop.mall.srv.domain.extend.DiscountPackageExtendDO;

/**
 * 优惠套餐扩展转换器
 * 
 *
 * @author Sunny
 * @date 2017/06/26
 */
public class DiscountPackageExtendConverter {

	/**
	 * DiscountPackageExtendBO转换
	 *
	 * @param discountPackageExtendDO
	 * @return
	 */
	public static DiscountPackageExtendBO convert(DiscountPackageExtendDO discountPackageExtendDO) {
		DiscountPackageExtendBO rtn = null;
		if (discountPackageExtendDO == null || discountPackageExtendDO.getId() == null || discountPackageExtendDO.getId() <= 0) {
			return rtn;
		}
		rtn = new DiscountPackageExtendBO();
		rtn.setCoverImage(discountPackageExtendDO.getCoverImage());
		rtn.setGmtCreate(discountPackageExtendDO.getGmtCreate());
		rtn.setGmtModified(discountPackageExtendDO.getGmtModified());
		rtn.setGmtUp(discountPackageExtendDO.getGmtUp());
		rtn.setId(discountPackageExtendDO.getId());
		rtn.setIsReservation(discountPackageExtendDO.getIsReservation());
		rtn.setMerchantId(discountPackageExtendDO.getMerchantId());
		rtn.setMerchantStoreId(discountPackageExtendDO.getMerchantStoreId());
		rtn.setName(discountPackageExtendDO.getName());
		rtn.setOriginalPrice(discountPackageExtendDO.getOriginalPrice());
		rtn.setOtherInstructions(discountPackageExtendDO.getOtherInstructions());
		rtn.setPrice(discountPackageExtendDO.getPrice());
		rtn.setStatus(DiscountPackageStatusEnum.getEnum(discountPackageExtendDO.getStatus()));
		rtn.setUseRules(discountPackageExtendDO.getUseRules());
		rtn.setUseTimeBegin(discountPackageExtendDO.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageExtendDO.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageExtendDO.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageExtendDO.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageExtendDO.getValidityPeriodEnd());
		rtn.setAdvanceBookingTime(discountPackageExtendDO.getAdvanceBookingTime());
		rtn.setPurchaseNotes(discountPackageExtendDO.getPurchaseNotes());
		rtn.setDiscountPackageContents(DiscountPackageContentConverter.convertDiscountPackageContentBOList(discountPackageExtendDO.getDiscountPackageContents()));
		rtn.setDiscountPackageImages(DiscountPackageImageConverter.convertDiscountPackageImageBOList(discountPackageExtendDO.getDiscountPackageImages()));
		return rtn;
	}
	
	/**
	 * DiscountPackageDetailDTO转换
	 *
	 * @param discountPackageExtendBO
	 * @return
	 */
	public static DiscountPackageDetailDTO convert(DiscountPackageExtendBO discountPackageExtendBO) {
		DiscountPackageDetailDTO rtn = null;
		if (discountPackageExtendBO == null) {
			return rtn;
		}
		rtn = new DiscountPackageDetailDTO();
		rtn.setCoverImage(discountPackageExtendBO.getCoverImage());
		rtn.setId(discountPackageExtendBO.getId());
		rtn.setIsReservation(discountPackageExtendBO.getIsReservation());
		rtn.setName(discountPackageExtendBO.getName());
		rtn.setOriginalPrice(discountPackageExtendBO.getOriginalPrice());
		rtn.setOtherInstructions(discountPackageExtendBO.getOtherInstructions());
		rtn.setPrice(discountPackageExtendBO.getPrice());
		rtn.setUseRules(discountPackageExtendBO.getUseRules());
		rtn.setUseTimeBegin(discountPackageExtendBO.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageExtendBO.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageExtendBO.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageExtendBO.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageExtendBO.getValidityPeriodEnd());
		rtn.setStatus(discountPackageExtendBO.getStatus());
		rtn.setAdvanceBookingTime(discountPackageExtendBO.getAdvanceBookingTime());
		rtn.setPurchaseNotes(discountPackageExtendBO.getPurchaseNotes());
		rtn.setDiscountPackageContents(DiscountPackageContentConverter.convertDiscountPackageContentDTOList(discountPackageExtendBO.getDiscountPackageContents()));
		rtn.setDiscountPackageImages(DiscountPackageImageConverter.convertDiscountPackageImageDTOList(discountPackageExtendBO.getDiscountPackageImages()));
		return rtn;
	}
	
	/**
	 * DiscountPackageDetailDTO转换
	 *
	 * @param discountPackageExtendBO
	 * @return
	 */
	public static DiscountPackageDetailForMemberDTO convertDiscountPackageDetailForMemberDTO(DiscountPackageExtendBO discountPackageExtendBO) {
		DiscountPackageDetailForMemberDTO rtn = null;
		if (discountPackageExtendBO == null) {
			return rtn;
		}
		rtn = new DiscountPackageDetailForMemberDTO();
		rtn.setCoverImage(discountPackageExtendBO.getCoverImage());
		rtn.setId(discountPackageExtendBO.getId());
		rtn.setIsReservation(discountPackageExtendBO.getIsReservation());
		rtn.setName(discountPackageExtendBO.getName());
		rtn.setOriginalPrice(discountPackageExtendBO.getOriginalPrice());
		rtn.setOtherInstructions(discountPackageExtendBO.getOtherInstructions());
		rtn.setPrice(discountPackageExtendBO.getPrice());
		rtn.setUseRules(discountPackageExtendBO.getUseRules());
		rtn.setUseTimeBegin(discountPackageExtendBO.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageExtendBO.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageExtendBO.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageExtendBO.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageExtendBO.getValidityPeriodEnd());
		rtn.setStatus(discountPackageExtendBO.getStatus());
		rtn.setAdvanceBookingTime(discountPackageExtendBO.getAdvanceBookingTime());
		rtn.setDiscountPackageContents(DiscountPackageContentConverter.convertDiscountPackageContentForMemberDTOList(discountPackageExtendBO.getDiscountPackageContents()));
		rtn.setDiscountPackageImages(DiscountPackageImageConverter.convertDiscountPackageImageForMemberDTOList(discountPackageExtendBO.getDiscountPackageImages()));
		return rtn;
	}
}
