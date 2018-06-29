package com.lawu.eshop.order.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderPaymentDTO {

	/**
	 * 购物订单id
	 */
	@ApiModelProperty(value = "购物订单id", required = true)
	private Long id;
	
	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号", required = true)
	private String orderNum;
	
	/**
	 * 订单总价
	 */
	@ApiModelProperty(value = "订单总价", required = true)
	private BigDecimal orderTotalPrice;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	
}