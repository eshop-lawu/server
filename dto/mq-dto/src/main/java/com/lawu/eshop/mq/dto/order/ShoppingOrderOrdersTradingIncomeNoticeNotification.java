package com.lawu.eshop.mq.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Sunny
 * @date 2017/06/08
 */
public class ShoppingOrderOrdersTradingIncomeNoticeNotification implements Serializable {

	private static final long serialVersionUID = 4555529600516908386L;
	
    /**
    * 订单id
    */
    private Long shoppingOrderId;
	
    /**
    *
    * 实际支付给商家的金额
    */
	private BigDecimal actualAmount;
	
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

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	
}
