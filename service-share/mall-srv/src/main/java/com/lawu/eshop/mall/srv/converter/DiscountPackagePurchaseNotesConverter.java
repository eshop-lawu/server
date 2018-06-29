package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDO;

/**
 * 优惠套餐购买须知转换器
 * 
 *
 * @author Sunny
 * @date 2017/07/31
 */
public class DiscountPackagePurchaseNotesConverter {
	
	/**
	 * List&lt;DiscountPackagePurchaseNotesDO&gt;转换List&lt;DiscountPackagePurchaseNotesBO&gt;
	 * 
	 * @param discountPackageDOList
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月31日
	 */
	public static List<DiscountPackagePurchaseNotesBO> convertDiscountPackagePurchaseNotesBOList(List<DiscountPackagePurchaseNotesDO> discountPackageDOList) {
		List<DiscountPackagePurchaseNotesBO> rtn = null;
		if (discountPackageDOList == null || discountPackageDOList.isEmpty()) {
			return rtn;
		}
		rtn = new ArrayList<>();
		for (DiscountPackagePurchaseNotesDO item : discountPackageDOList) {
			DiscountPackagePurchaseNotesBO discountPackagePurchaseNotesBO = new DiscountPackagePurchaseNotesBO();
			discountPackagePurchaseNotesBO.setGmtCreate(item.getGmtCreate());
			discountPackagePurchaseNotesBO.setId(item.getId());
			discountPackagePurchaseNotesBO.setNote(item.getNote());
			rtn.add(discountPackagePurchaseNotesBO);
		}
		return rtn;
	}
	
	/**
	 * List&lt;DiscountPackagePurchaseNotesBO&gt;转换List&lt;DiscountPackagePurchaseNotesDTO&gt;
	 * 
	 * @param discountPackageDOList
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月31日
	 */
	public static List<DiscountPackagePurchaseNotesDTO> convertDiscountPackagePurchaseNotesDTOList(List<DiscountPackagePurchaseNotesBO> discountPackageBOList) {
		List<DiscountPackagePurchaseNotesDTO> rtn = null;
		if (discountPackageBOList == null || discountPackageBOList.isEmpty()) {
			return rtn;
		}
		rtn = new ArrayList<>();
		for (DiscountPackagePurchaseNotesBO item : discountPackageBOList) {
			DiscountPackagePurchaseNotesDTO discountPackagePurchaseNotesDTO = new DiscountPackagePurchaseNotesDTO();
			discountPackagePurchaseNotesDTO.setId(item.getId());
			discountPackagePurchaseNotesDTO.setNote(item.getNote());
			rtn.add(discountPackagePurchaseNotesDTO);
		}
		return rtn;
	}
	
}
