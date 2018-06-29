package com.lawu.eshop.order.srv.bo;

import java.util.List;

public class ShoppingRefundDetailExtendBO extends ShoppingRefundDetailBO {
	
	/**
	 * 退款流程列表
	 */
	private List<ShoppingRefundProcessBO> shoppingRefundProcessList;

	public List<ShoppingRefundProcessBO> getShoppingRefundProcessList() {
		return shoppingRefundProcessList;
	}

	public void setShoppingRefundProcessList(List<ShoppingRefundProcessBO> shoppingRefundProcessList) {
		this.shoppingRefundProcessList = shoppingRefundProcessList;
	}
	
}