package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForOperatorDTO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.framework.core.page.Page;

/**
 *
 * 购物订单扩展转换器
 *
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderItemExtendConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingOrderItemExtendConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * ShoppingOrderItemExtendBO转换
	 * 
	 * @param shoppingOrderItemExtendDO
	 * @return
	 */
	public static ShoppingOrderItemExtendBO convert(ShoppingOrderItemExtendDO shoppingOrderItemExtendDO) {
		ShoppingOrderItemExtendBO rtn = null;
		if (shoppingOrderItemExtendDO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderItemExtendBO();
		rtn.setId(shoppingOrderItemExtendDO.getId());
		rtn.setIsAllowRefund(shoppingOrderItemExtendDO.getIsAllowRefund());
		rtn.setIsEvaluation(shoppingOrderItemExtendDO.getIsEvaluation());
		rtn.setProductFeatureImage(shoppingOrderItemExtendDO.getProductFeatureImage());
		rtn.setProductId(shoppingOrderItemExtendDO.getProductId());
		rtn.setProductModelId(shoppingOrderItemExtendDO.getProductModelId());
		rtn.setProductModelName(shoppingOrderItemExtendDO.getProductModelName());
		rtn.setProductName(shoppingOrderItemExtendDO.getProductName());
		rtn.setQuantity(shoppingOrderItemExtendDO.getQuantity());
		rtn.setRegularPrice(shoppingOrderItemExtendDO.getRegularPrice());
		rtn.setSalesPrice(shoppingOrderItemExtendDO.getSalesPrice());
		rtn.setSendTime(shoppingOrderItemExtendDO.getSendTime());
		rtn.setShoppingOrderId(shoppingOrderItemExtendDO.getShoppingOrderId());
		rtn.setGmtCreate(shoppingOrderItemExtendDO.getGmtCreate());
		rtn.setGmtModified(shoppingOrderItemExtendDO.getGmtModified());
		
		rtn.setOrderStatus(ShoppingOrderStatusEnum.getEnum(shoppingOrderItemExtendDO.getOrderStatus()));
		rtn.setRefundStatus(RefundStatusEnum.getEnum(shoppingOrderItemExtendDO.getRefundStatus()));

		rtn.setShoppingRefundDetail(ShoppingRefundDetailExtendConverter.convert(shoppingOrderItemExtendDO.getShoppingRefundDetail()));
		rtn.setShoppingOrder(ShoppingOrderConverter.convertShoppingOrderBO(shoppingOrderItemExtendDO.getShoppingOrder()));

		return rtn;
	}

	/**
	 * ShoppingOrderItemExtendBO List转换
	 * 
	 * @param shoppingOrderItemExtendDOList
	 * @return
	 */
	public static List<ShoppingOrderItemExtendBO> convert(List<ShoppingOrderItemExtendDO> shoppingOrderItemExtendDOList) {
		List<ShoppingOrderItemExtendBO> rtn = null;
		if (shoppingOrderItemExtendDOList == null || shoppingOrderItemExtendDOList.isEmpty()) {
			return rtn;
		}

		rtn = new ArrayList<>();
		for (ShoppingOrderItemExtendDO shoppingOrderItemExtendDO : shoppingOrderItemExtendDOList) {
			rtn.add(convert(shoppingOrderItemExtendDO));
		}

		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForMerchantDTO转换
	 * 
	 * @param shoppingOrderItemExtendBO
	 * @return
	 */
	public static ShoppingOrderItemRefundForMerchantDTO convert(ShoppingOrderItemExtendBO shoppingOrderItemExtendBO) {
		ShoppingOrderItemRefundForMerchantDTO rtn = null;
		if (shoppingOrderItemExtendBO == null) {
			return rtn;
		}
		rtn = new ShoppingOrderItemRefundForMerchantDTO();
		rtn.setId(shoppingOrderItemExtendBO.getId());
		rtn.setProductFeatureImage(shoppingOrderItemExtendBO.getProductFeatureImage());
		rtn.setProductModelName(shoppingOrderItemExtendBO.getProductModelName());
		rtn.setProductName(shoppingOrderItemExtendBO.getProductName());
		rtn.setRefundStatus(shoppingOrderItemExtendBO.getRefundStatus());
		
		if (shoppingOrderItemExtendBO.getShoppingOrder() != null) {
			rtn.setConsigneeName(shoppingOrderItemExtendBO.getShoppingOrder().getConsigneeName());
		}
		if (shoppingOrderItemExtendBO.getShoppingRefundDetail() != null) {
			rtn.setShoppingRefundDetailId(shoppingOrderItemExtendBO.getShoppingRefundDetail().getId());
			rtn.setGmtCreate(shoppingOrderItemExtendBO.getShoppingRefundDetail().getGmtCreate());
		}
		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForMerchantDTO List转换
	 * 
	 * @param shoppingOrderItemExtendBOList
	 * @return
	 */
	public static List<ShoppingOrderItemRefundForMerchantDTO> convertShoppingOrderItemRefundForMerchantDTOList(List<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOList) {
		List<ShoppingOrderItemRefundForMerchantDTO> rtn = new ArrayList<>();

		if (shoppingOrderItemExtendBOList == null || shoppingOrderItemExtendBOList.isEmpty()) {
			return rtn;
		}

		for (ShoppingOrderItemExtendBO shoppingOrderItemExtendBO : shoppingOrderItemExtendBOList) {
			rtn.add(convert(shoppingOrderItemExtendBO));
		}

		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForMerchantDTO Page转换
	 * 
	 * @param shoppingOrderItemExtendBOPage
	 * @return
	 */
	public static Page<ShoppingOrderItemRefundForMerchantDTO> convertShoppingOrderItemRefundForMerchantDTOPage(Page<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOPage) {
		Page<ShoppingOrderItemRefundForMerchantDTO> rtn = new Page<>();
		rtn.setCurrentPage(shoppingOrderItemExtendBOPage.getCurrentPage());
		rtn.setTotalCount(shoppingOrderItemExtendBOPage.getTotalCount());
		rtn.setRecords(convertShoppingOrderItemRefundForMerchantDTOList(shoppingOrderItemExtendBOPage.getRecords()));
		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForOperatorDTO转换
	 * 
	 * @param shoppingOrderItemExtendBO
	 * @return
	 */
	public static ShoppingOrderItemRefundForOperatorDTO convertShoppingOrderItemRefundForOperatorDTO(ShoppingOrderItemExtendBO shoppingOrderItemExtendBO) {
		ShoppingOrderItemRefundForOperatorDTO rtn = null;
		if (shoppingOrderItemExtendBO == null) {
			return rtn;
		}
		rtn = new ShoppingOrderItemRefundForOperatorDTO();
		rtn.setId(shoppingOrderItemExtendBO.getId());
		rtn.setProductFeatureImage(shoppingOrderItemExtendBO.getProductFeatureImage());
		rtn.setProductModelName(shoppingOrderItemExtendBO.getProductModelName());
		rtn.setProductName(shoppingOrderItemExtendBO.getProductName());
		rtn.setRefundStatus(shoppingOrderItemExtendBO.getRefundStatus());
		
		if (shoppingOrderItemExtendBO.getShoppingOrder() != null) {
			rtn.setConsigneeName(shoppingOrderItemExtendBO.getShoppingOrder().getConsigneeName());
			rtn.setConsigneeAddress(shoppingOrderItemExtendBO.getShoppingOrder().getConsigneeAddress());
			rtn.setConsigneeMobile(shoppingOrderItemExtendBO.getShoppingOrder().getConsigneeMobile());
			rtn.setOrderNum(shoppingOrderItemExtendBO.getShoppingOrder().getOrderNum());
		}
		if (shoppingOrderItemExtendBO.getShoppingRefundDetail() != null) {
			rtn.setShoppingRefundDetailId(shoppingOrderItemExtendBO.getShoppingRefundDetail().getId());
			rtn.setGmtCreate(shoppingOrderItemExtendBO.getShoppingRefundDetail().getGmtCreate());
			rtn.setGmtIntervention(shoppingOrderItemExtendBO.getShoppingRefundDetail().getGmtIntervention());
		}
		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForOperatorDTO List转换
	 * 
	 * @param shoppingOrderItemExtendBOList
	 * @return
	 */
	public static List<ShoppingOrderItemRefundForOperatorDTO> convertShoppingOrderItemRefundForOperatorDTOList(List<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOList) {
		List<ShoppingOrderItemRefundForOperatorDTO> rtn = new ArrayList<>();

		if (shoppingOrderItemExtendBOList == null || shoppingOrderItemExtendBOList.isEmpty()) {
			return rtn;
		}

		for (ShoppingOrderItemExtendBO shoppingOrderItemExtendBO : shoppingOrderItemExtendBOList) {
			rtn.add(convertShoppingOrderItemRefundForOperatorDTO(shoppingOrderItemExtendBO));
		}

		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundForOperatorDTO Page转换
	 * 
	 * @param shoppingOrderItemExtendBOPage
	 * @return
	 */
	public static Page<ShoppingOrderItemRefundForOperatorDTO> convertShoppingOrderItemRefundForOperatorDTOPage(Page<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOPage) {
		Page<ShoppingOrderItemRefundForOperatorDTO> rtn = new Page<>();
		rtn.setCurrentPage(shoppingOrderItemExtendBOPage.getCurrentPage());
		rtn.setTotalCount(shoppingOrderItemExtendBOPage.getTotalCount());
		rtn.setRecords(convertShoppingOrderItemRefundForOperatorDTOList(shoppingOrderItemExtendBOPage.getRecords()));
		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundDTO转换
	 * 
	 * @param shoppingOrderItemExtendBO
	 * @return
	 */
	public static ShoppingOrderItemRefundDTO convertShoppingOrderItemRefundDTO(ShoppingOrderItemExtendBO shoppingOrderItemExtendBO) {
		ShoppingOrderItemRefundDTO rtn = null;
		if (shoppingOrderItemExtendBO == null) {
			return rtn;
		}
		rtn = new ShoppingOrderItemRefundDTO();
		rtn.setId(shoppingOrderItemExtendBO.getId());
		rtn.setProductFeatureImage(shoppingOrderItemExtendBO.getProductFeatureImage());
		rtn.setProductModelName(shoppingOrderItemExtendBO.getProductModelName());
		rtn.setProductName(shoppingOrderItemExtendBO.getProductName());
		rtn.setRefundStatus(shoppingOrderItemExtendBO.getRefundStatus());
		rtn.setQuantity(shoppingOrderItemExtendBO.getQuantity());

		if (shoppingOrderItemExtendBO.getShoppingOrder() != null) {
			rtn.setMerchantId(shoppingOrderItemExtendBO.getShoppingOrder().getMerchantId());
			rtn.setMerchantName(shoppingOrderItemExtendBO.getShoppingOrder().getMerchantName());
			rtn.setIsNoReasonReturn(shoppingOrderItemExtendBO.getShoppingOrder().getIsNoReasonReturn());
			rtn.setMerchantStoreId(shoppingOrderItemExtendBO.getShoppingOrder().getMerchantStoreId());
		}
		if (shoppingOrderItemExtendBO.getShoppingRefundDetail() != null) {
			rtn.setShoppingRefundDetailId(shoppingOrderItemExtendBO.getShoppingRefundDetail().getId());
			rtn.setAmount(shoppingOrderItemExtendBO.getShoppingRefundDetail().getAmount());
			rtn.setType(shoppingOrderItemExtendBO.getShoppingRefundDetail().getType());
		}
		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundDTO List转换
	 * 
	 * @param shoppingOrderItemExtendBOList
	 * @return
	 */
	public static List<ShoppingOrderItemRefundDTO> convertShoppingOrderItemRefundDTOList(List<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOList) {
		List<ShoppingOrderItemRefundDTO> rtn = new ArrayList<>();

		if (shoppingOrderItemExtendBOList == null || shoppingOrderItemExtendBOList.isEmpty()) {
			return rtn;
		}

		for (ShoppingOrderItemExtendBO shoppingOrderItemExtendBO : shoppingOrderItemExtendBOList) {
			rtn.add(convertShoppingOrderItemRefundDTO(shoppingOrderItemExtendBO));
		}

		return rtn;
	}

	/**
	 * ShoppingOrderItemRefundDTO Page转换
	 * 
	 * @param shoppingOrderItemExtendBOPage
	 * @return
	 */
	public static Page<ShoppingOrderItemRefundDTO> convertShoppingOrderItemRefundDTOPage(Page<ShoppingOrderItemExtendBO> shoppingOrderItemExtendBOPage) {
		Page<ShoppingOrderItemRefundDTO> rtn = new Page<>();
		rtn.setCurrentPage(shoppingOrderItemExtendBOPage.getCurrentPage());
		rtn.setTotalCount(shoppingOrderItemExtendBOPage.getTotalCount());
		rtn.setRecords(convertShoppingOrderItemRefundDTOList(shoppingOrderItemExtendBOPage.getRecords()));
		return rtn;
	}
}
