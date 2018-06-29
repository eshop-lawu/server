package com.lawu.eshop.mq.dto.order;

import com.lawu.compensating.transaction.Notification;

/**
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingRefundRefundFailedRemindNotification extends Notification {

	private static final long serialVersionUID = 6048554730530763951L;

	/**
	 * 购物订单id
	 */
	private Long shoppingOrderId;
	
    /**
    * 商家编号
    */
    private String merchantNum;

	public Long getShoppingOrderId() {
		return shoppingOrderId;
	}

	public void setShoppingOrderId(Long shoppingOrderId) {
		this.shoppingOrderId = shoppingOrderId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
}
