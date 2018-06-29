package com.lawu.eshop.mq.dto.mall;

import com.lawu.compensating.transaction.Notification;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
public class CommentProductNotification extends Notification{

	private static final long serialVersionUID = -7788009032853163112L;
	
	private Long shoppingOrderItemId;

	public Long getShoppingOrderItemId() {
		return shoppingOrderItemId;
	}

	public void setShoppingOrderItemId(Long shoppingOrderItemId) {
		this.shoppingOrderItemId = shoppingOrderItemId;
	}
	
}
