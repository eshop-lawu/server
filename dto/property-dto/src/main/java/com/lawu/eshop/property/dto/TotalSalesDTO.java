package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

/**
 * 平台总销量DTO
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class TotalSalesDTO {

	/**
	 * 买单金额
	 */
	private BigDecimal payOrderAmount;

	/**
	 * 订单金额
	 */
	private BigDecimal shoppingOrderAmount;

	public BigDecimal getPayOrderAmount() {
		return payOrderAmount;
	}

	public void setPayOrderAmount(BigDecimal payOrderAmount) {
		this.payOrderAmount = payOrderAmount;
	}

	public BigDecimal getShoppingOrderAmount() {
		return shoppingOrderAmount;
	}

	public void setShoppingOrderAmount(BigDecimal shoppingOrderAmount) {
		this.shoppingOrderAmount = shoppingOrderAmount;
	}

}