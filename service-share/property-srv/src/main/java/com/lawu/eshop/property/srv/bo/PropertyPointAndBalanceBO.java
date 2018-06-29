package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class PropertyPointAndBalanceBO {
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 积分
	 */
	private BigDecimal point;


	private BigDecimal freeze;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public BigDecimal getFreeze() {
		return freeze;
	}

	public void setFreeze(BigDecimal freeze) {
		this.freeze = freeze;
	}
}
