package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.framework.core.page.Page;

/**
 * 优惠套餐转换器
 * 
 *
 * @author Sunny
 * @date 2017/06/26
 */
public class DiscountPackageConverter {
	
	/**
	 * DiscountPackageBO转换
	 *
	 * @param discountPackageDO
	 * @return
	 */
	public static DiscountPackageBO convert(DiscountPackageDO discountPackageDO) {
		DiscountPackageBO rtn = null;
		if (discountPackageDO == null || discountPackageDO.getId() == null || discountPackageDO.getId() <= 0) {
			return rtn;
		}

		rtn = new DiscountPackageBO();
		rtn.setCoverImage(discountPackageDO.getCoverImage());
		rtn.setGmtCreate(discountPackageDO.getGmtCreate());
		rtn.setGmtModified(discountPackageDO.getGmtModified());
		rtn.setGmtUp(discountPackageDO.getGmtUp());
		rtn.setId(discountPackageDO.getId());
		rtn.setIsReservation(discountPackageDO.getIsReservation());
		rtn.setMerchantId(discountPackageDO.getMerchantId());
		rtn.setMerchantStoreId(discountPackageDO.getMerchantStoreId());
		rtn.setName(discountPackageDO.getName());
		rtn.setOriginalPrice(discountPackageDO.getOriginalPrice());
		rtn.setOtherInstructions(discountPackageDO.getOtherInstructions());
		rtn.setPrice(discountPackageDO.getPrice());
		rtn.setStatus(DiscountPackageStatusEnum.getEnum(discountPackageDO.getStatus()));
		rtn.setUseRules(discountPackageDO.getUseRules());
		rtn.setUseTimeBegin(discountPackageDO.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageDO.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageDO.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageDO.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageDO.getValidityPeriodEnd());
		rtn.setAdvanceBookingTime(discountPackageDO.getAdvanceBookingTime());
		rtn.setPurchaseNotes(discountPackageDO.getPurchaseNotes());
		return rtn;
	}
	
	/**
	 * DiscountPackageBOList转换
	 *
	 * @param discountPackageDO
	 * @return
	 */
	public static List<DiscountPackageBO> convertDiscountPackageBOList(List<DiscountPackageDO> discountPackageDOList) {
		List<DiscountPackageBO> rtn = null;
		if (discountPackageDOList == null || discountPackageDOList.isEmpty()) {
			return rtn;
		}

		rtn = new ArrayList<>();
		for (DiscountPackageDO discountPackageDO : discountPackageDOList) {
			rtn.add(convert(discountPackageDO));
		}
		return rtn;
	}
	
	/**
	 * DiscountPackageDO转换
	 *
	 * @param discountPackageDO
	 * @return
	 */
	public static DiscountPackageDO convert(Long merchantId, DiscountPackageSaveParam discountPackageSaveParam) {
		DiscountPackageDO rtn = null;
		if (discountPackageSaveParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageDO();
		rtn.setCoverImage(discountPackageSaveParam.getCoverImage());
		rtn.setIsReservation(discountPackageSaveParam.getIsReservation());
		rtn.setMerchantId(merchantId);
		rtn.setMerchantStoreId(discountPackageSaveParam.getMerchantStoreId());
		rtn.setName(discountPackageSaveParam.getName());
		
		rtn.setOriginalPrice(new BigDecimal(0));
		for (DiscountPackageContentSaveForeignParam discountPackageContent : discountPackageSaveParam.getDiscountPackageContents()) {
			rtn.setOriginalPrice(rtn.getOriginalPrice().add(discountPackageContent.getUnitPrice().multiply(new BigDecimal(discountPackageContent.getQuantity()))));
		}
		rtn.setOtherInstructions(discountPackageSaveParam.getOtherInstructions());
		rtn.setPrice(discountPackageSaveParam.getPrice());
		rtn.setUseRules(discountPackageSaveParam.getUseRules());
		rtn.setUseTimeBegin(discountPackageSaveParam.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageSaveParam.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageSaveParam.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageSaveParam.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageSaveParam.getValidityPeriodEnd());
		
		rtn.setGmtCreate(new Date());
		rtn.setGmtModified(new Date());
		// 添加的新记录默认为上架状态
		rtn.setGmtUp(new Date());
		rtn.setStatus(DiscountPackageStatusEnum.UP.getValue());
		rtn.setAdvanceBookingTime(discountPackageSaveParam.getAdvanceBookingTime());
		rtn.setPurchaseNotes(discountPackageSaveParam.getPurchaseNotes());
		return rtn;
	}
	
	/**
	 * DiscountPackageDO转换
	 * 
	 * @param id
	 * @param discountPackageUpdateParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	public static DiscountPackageDO convert(Long id, DiscountPackageUpdateParam discountPackageUpdateParam) {
		DiscountPackageDO rtn = null;
		if (discountPackageUpdateParam == null) {
			return rtn;
		}

		rtn = new DiscountPackageDO();
		rtn.setId(id);
		rtn.setCoverImage(discountPackageUpdateParam.getCoverImage());
		rtn.setIsReservation(discountPackageUpdateParam.getIsReservation());
		rtn.setName(discountPackageUpdateParam.getName());
		rtn.setOriginalPrice(new BigDecimal(0));
		for (DiscountPackageContentSaveForeignParam discountPackageContent : discountPackageUpdateParam.getDiscountPackageContents()) {
			rtn.setOriginalPrice(rtn.getOriginalPrice().add(discountPackageContent.getUnitPrice().multiply(new BigDecimal(discountPackageContent.getQuantity()))));
		}
		rtn.setOtherInstructions(discountPackageUpdateParam.getOtherInstructions());
		rtn.setPrice(discountPackageUpdateParam.getPrice());
		rtn.setUseRules(discountPackageUpdateParam.getUseRules());
		rtn.setUseTimeBegin(discountPackageUpdateParam.getUseTimeBegin());
		rtn.setUseTimeEnd(discountPackageUpdateParam.getUseTimeEnd());
		rtn.setUseTimeWeek(discountPackageUpdateParam.getUseTimeWeek());
		rtn.setValidityPeriodBegin(discountPackageUpdateParam.getValidityPeriodBegin());
		rtn.setValidityPeriodEnd(discountPackageUpdateParam.getValidityPeriodEnd());
		rtn.setGmtModified(new Date());
		rtn.setAdvanceBookingTime(discountPackageUpdateParam.getAdvanceBookingTime());
		rtn.setPurchaseNotes(discountPackageUpdateParam.getPurchaseNotes());
		rtn.setStatus(discountPackageUpdateParam.getStatus() != null ? discountPackageUpdateParam.getStatus().getValue() : null);
		return rtn;
	}
	
	/**
	 * DiscountPackageQueryDTO转换
	 *
	 * @param discountPackageBO
	 * @return
	 */
	public static DiscountPackageQueryDTO convert(DiscountPackageBO discountPackageBO) {
		DiscountPackageQueryDTO rtn = null;
		if (discountPackageBO == null) {
			return rtn;
		}

		rtn = new DiscountPackageQueryDTO();
		rtn.setCoverImage(discountPackageBO.getCoverImage());
		rtn.setGmtUp(discountPackageBO.getGmtUp());
		rtn.setId(discountPackageBO.getId());
		rtn.setIsReservation(discountPackageBO.getIsReservation());
		rtn.setName(discountPackageBO.getName());
		rtn.setOriginalPrice(discountPackageBO.getOriginalPrice());
		rtn.setPrice(discountPackageBO.getPrice());
		return rtn;
	}
	
	/**
	 * DiscountPackageQueryDTOList转换
	 *
	 * @param discountPackageBOList
	 * @return
	 */
	public static List<DiscountPackageQueryDTO> convertDiscountPackageQueryDTOList(List<DiscountPackageBO> discountPackageBOList) {
		List<DiscountPackageQueryDTO> rtn = new ArrayList<>();
		if (discountPackageBOList == null || discountPackageBOList.isEmpty()) {
			return rtn;
		}
		
		for (DiscountPackageBO discountPackageBO : discountPackageBOList) {
			rtn.add(convert(discountPackageBO));
		}
		return rtn;
	}
	
	/**
	 * <code>Page&lt;DiscountPackageQueryDTO&gt;<code>转换<code>List&lt;DiscountPackageBO&gt;<code>
	 *
	 * @param discountPackageBOList
	 * @return
	 */
	public static Page<DiscountPackageQueryDTO> convertDiscountPackageQueryDTOPage(List<DiscountPackageBO> discountPackageBOList) {
		Page<DiscountPackageQueryDTO> rtn = new Page<>();
		if (discountPackageBOList == null || discountPackageBOList.isEmpty()) {
			return rtn;
		}
		rtn.setTotalCount(discountPackageBOList.size());
		rtn.setCurrentPage(1);
		rtn.setRecords(convertDiscountPackageQueryDTOList(discountPackageBOList));
		return rtn;
	}
}
