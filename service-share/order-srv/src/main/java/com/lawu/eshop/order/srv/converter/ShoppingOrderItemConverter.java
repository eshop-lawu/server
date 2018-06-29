package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.CommentOrderDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemDTO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;

/**
 *
 * 购物订单项转换器
 *
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderItemConverter {
	
	/**
	 * 隐藏默认的构造器
	 */
	private ShoppingOrderItemConverter() {
		throw new IllegalAccessError("Utility class");
	}

	/**
	 * ShoppingOrderItemBO转换
	 * 
	 * @param shoppingOrderItemDO
	 * @return
	 */
	public static ShoppingOrderItemBO convert(ShoppingOrderItemDO shoppingOrderItemDO) {
		ShoppingOrderItemBO rtn = null;
		if (shoppingOrderItemDO == null) {
			return rtn;
		}
		rtn = new ShoppingOrderItemBO();
		rtn.setId(shoppingOrderItemDO.getId());
		rtn.setIsAllowRefund(shoppingOrderItemDO.getIsAllowRefund());
		rtn.setIsEvaluation(shoppingOrderItemDO.getIsEvaluation());
		rtn.setProductFeatureImage(shoppingOrderItemDO.getProductFeatureImage());
		rtn.setProductId(shoppingOrderItemDO.getProductId());
		rtn.setProductModelId(shoppingOrderItemDO.getProductModelId());
		rtn.setProductModelName(shoppingOrderItemDO.getProductModelName());
		rtn.setProductName(shoppingOrderItemDO.getProductName());
		rtn.setQuantity(shoppingOrderItemDO.getQuantity());
		rtn.setRegularPrice(shoppingOrderItemDO.getRegularPrice());
		rtn.setSalesPrice(shoppingOrderItemDO.getSalesPrice());
		rtn.setDeductionPoints(shoppingOrderItemDO.getDeductionPoints());
		rtn.setDeductionPointsAmount(shoppingOrderItemDO.getDeductionPointsAmount());
		rtn.setSendTime(shoppingOrderItemDO.getSendTime());
		rtn.setShoppingOrderId(shoppingOrderItemDO.getShoppingOrderId());
		rtn.setGmtCreate(shoppingOrderItemDO.getGmtCreate());
		rtn.setGmtModified(shoppingOrderItemDO.getGmtModified());
		rtn.setOrderStatus(ShoppingOrderStatusEnum.getEnum(shoppingOrderItemDO.getOrderStatus()));
		rtn.setRefundStatus(RefundStatusEnum.getEnum(shoppingOrderItemDO.getRefundStatus()));
		rtn.setActivityProductModelId(shoppingOrderItemDO.getActivityProductModelId());
		return rtn;
	}
	
	/**
	 * ShoppingOrderItemBO List转换
	 * 
	 * @param shoppingOrderItemDO
	 * @return
	 */
	public static List<ShoppingOrderItemBO> convert(List<ShoppingOrderItemDO> shoppingOrderItemDOList) {
		List<ShoppingOrderItemBO> rtn = null;
		if (shoppingOrderItemDOList == null || shoppingOrderItemDOList.isEmpty()) {
			return rtn;
		}
		
		rtn = new ArrayList<>();
		for (ShoppingOrderItemDO shoppingOrderItemDO : shoppingOrderItemDOList) {
			rtn.add(convert(shoppingOrderItemDO));
		}
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderItemBO转换
	 * 
	 * @param shoppingOrderItemDO
	 * @return
	 */
	public static ShoppingOrderItemDTO convert(ShoppingOrderItemBO shoppingOrderItemBO) {
		ShoppingOrderItemDTO rtn = null;
		if (shoppingOrderItemBO == null) {
			return rtn;
		}

		rtn = new ShoppingOrderItemDTO();
		rtn.setId(shoppingOrderItemBO.getId());
		rtn.setIsAllowRefund(shoppingOrderItemBO.getIsAllowRefund());
		rtn.setIsEvaluation(shoppingOrderItemBO.getIsEvaluation());
		rtn.setOrderStatus(shoppingOrderItemBO.getOrderStatus());
		rtn.setProductFeatureImage(shoppingOrderItemBO.getProductFeatureImage());
		rtn.setProductId(shoppingOrderItemBO.getProductId());
		rtn.setProductModelId(shoppingOrderItemBO.getProductModelId());
		rtn.setProductModelName(shoppingOrderItemBO.getProductModelName());
		rtn.setProductName(shoppingOrderItemBO.getProductName());
		rtn.setQuantity(shoppingOrderItemBO.getQuantity());
		rtn.setRefundStatus(shoppingOrderItemBO.getRefundStatus());
		rtn.setRegularPrice(shoppingOrderItemBO.getRegularPrice());
		rtn.setSalesPrice(shoppingOrderItemBO.getSalesPrice());
		
		return rtn;
	}
	
	/**
	 * ShoppingOrderItemBO List转换
	 * 
	 * @param shoppingOrderItemDO
	 * @return
	 */
	public static List<ShoppingOrderItemDTO> convertShoppingOrderItemDTOList(List<ShoppingOrderItemBO> shoppingOrderItemBOList) {
		List<ShoppingOrderItemDTO> rtn = new ArrayList<>();
		if (shoppingOrderItemBOList == null || shoppingOrderItemBOList.isEmpty()) {
			return rtn;
		}
		
		for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderItemBOList) {
			rtn.add(convert(shoppingOrderItemBO));
		}
		
		return rtn;
	}
	
	/**
	 * 
	 * @param shoppingOrderItemBO
	 * @return
	 * @author Sunny
	 * @date 2017年6月16日
	 */
	public static CommentOrderDTO coverCommentStatusDTO(ShoppingOrderItemBO shoppingOrderItemBO) {
		CommentOrderDTO rtn = null;
		if (shoppingOrderItemBO == null) {
			return rtn;
		}
		rtn = new CommentOrderDTO();
		rtn.setEvaluation(shoppingOrderItemBO.getIsEvaluation());
		return rtn;
	}
}
