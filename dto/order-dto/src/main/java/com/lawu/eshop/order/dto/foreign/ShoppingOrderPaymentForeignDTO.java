package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;

import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderPaymentForeignDTO extends ShoppingOrderPaymentDTO {

	/**
    * 余额
    */
	@ApiModelProperty(name = "balance", value= "余额", required = true)
    private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}