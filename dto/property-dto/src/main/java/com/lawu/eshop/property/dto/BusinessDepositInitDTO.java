package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class BusinessDepositInitDTO {

	@ApiModelProperty(value = "保证金记录ID")
	private Long id;
	@ApiModelProperty(value = "保证金")
	private String deposit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	
}
