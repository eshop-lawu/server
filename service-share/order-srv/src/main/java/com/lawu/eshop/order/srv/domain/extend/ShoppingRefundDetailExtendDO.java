package com.lawu.eshop.order.srv.domain.extend;

import java.io.Serializable;
import java.util.List;

import com.lawu.eshop.order.srv.domain.ShoppingRefundDetailDO;
import com.lawu.eshop.order.srv.domain.ShoppingRefundProcessDO;

public class ShoppingRefundDetailExtendDO extends ShoppingRefundDetailDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 退款流程列表
	 */
	private List<ShoppingRefundProcessDO> shoppingRefundProcessList;

	public List<ShoppingRefundProcessDO> getShoppingRefundProcessList() {
		return shoppingRefundProcessList;
	}

	public void setShoppingRefundProcessList(List<ShoppingRefundProcessDO> shoppingRefundProcessList) {
		this.shoppingRefundProcessList = shoppingRefundProcessList;
	}
	
}