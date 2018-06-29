package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class HelpRedpacketDTO {
	
	@ApiModelProperty(value = "获得金额")
	private BigDecimal money;
	
	@ApiModelProperty(value = "对应倍数")
	private Double multiple;
	
	@ApiModelProperty(value = "原始金额")
	private BigDecimal originalMoney;
	

	public BigDecimal getOriginalMoney() {
		return originalMoney;
	}

	public void setOriginalMoney(BigDecimal originalMoney) {
		this.originalMoney = originalMoney;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Double getMultiple() {
		return multiple;
	}

	public void setMultiple(Double multiple) {
		this.multiple = multiple;
	}
	
	

}
