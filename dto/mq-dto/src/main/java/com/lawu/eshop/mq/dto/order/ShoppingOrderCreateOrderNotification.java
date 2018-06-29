package com.lawu.eshop.mq.dto.order;

import java.util.List;

import com.lawu.compensating.transaction.Notification;

/**
 * 
 * @author Sunny
 * @date 2017/04/06
 */
public class ShoppingOrderCreateOrderNotification extends Notification {

	private static final long serialVersionUID = -4298398465288913709L;

	/**
	 * 购物订单id
	 */
	private Long shoppingOrderId;
	
	/**
	 * 批量更新库存的参数
	 */
	private List<ProductModeUpdateInventoryDTO> params;

	public Long getShoppingOrderId() {
		return shoppingOrderId;
	}

	public void setShoppingOrderId(Long shoppingOrderId) {
		this.shoppingOrderId = shoppingOrderId;
	}

	public List<ProductModeUpdateInventoryDTO> getParams() {
		return params;
	}

	public void setParams(List<ProductModeUpdateInventoryDTO> params) {
		this.params = params;
	}
}
