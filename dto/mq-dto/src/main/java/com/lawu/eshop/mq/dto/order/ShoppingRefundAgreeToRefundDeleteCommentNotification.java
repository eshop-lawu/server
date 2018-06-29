package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingRefundAgreeToRefundDeleteCommentNotification extends Notification {

	private static final long serialVersionUID = -5000118367632280701L;
	
	/**
	 * 购物订单id
	 */
	private Long shoppingOrderItemId;

	public Long getShoppingOrderItemId() {
		return shoppingOrderItemId;
	}

	public void setShoppingOrderItemId(Long shoppingOrderItemId) {
		this.shoppingOrderItemId = shoppingOrderItemId;
	}
}
