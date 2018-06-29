package com.lawu.eshop.order.srv.domain.extend;

import java.util.List;

import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;

public class ShoppingOrderExtendDO extends ShoppingOrderDO {

	private static final long serialVersionUID = 1L;

	/**********************************
	 * Extend Attributes
	 ************************************/
	/**
	 * 订单项
	 */
	private List<ShoppingOrderItemDO> items;
	
	public List<ShoppingOrderItemDO> getItems() {
		return items;
	}

	public void setItems(List<ShoppingOrderItemDO> items) {
		this.items = items;
	}

}