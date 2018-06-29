package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * 
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingOrderDeleteOrderNotification extends Notification {
	
	private static final long serialVersionUID = -5967264070363853765L;
	
	/**
	 * 购物订单id
	 */
	private Long shoppingOrderId;
	
	public Long getShoppingOrderId() {
		return shoppingOrderId;
	}

	public void setShoppingOrderId(Long shoppingOrderId) {
		this.shoppingOrderId = shoppingOrderId;
	}
}
