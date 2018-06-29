package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;

/**
 * 
 * @author Sunny
 * @date 2017/04/18
 */
public class ShoppingOrderRemindShipmentsNotification implements Serializable {

	private static final long serialVersionUID = 2965410379351297241L;

	/**
	 * 订单数量
	 */
	private Long quantity;
	
    /**
    * 商家编号
    */
    private String merchantNum;
    
    
	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}


}
