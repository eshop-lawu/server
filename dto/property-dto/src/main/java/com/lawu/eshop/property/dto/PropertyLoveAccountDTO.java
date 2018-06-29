package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class PropertyLoveAccountDTO {
	
	@ApiModelProperty(name = "loveAccount", value= "爱心账户", required = true)
	private BigDecimal loveAccount;

	public BigDecimal getLoveAccount() {
		return loveAccount;
	}

	public void setLoveAccount(BigDecimal loveAccount) {
		this.loveAccount = loveAccount;
	}
	
	
}
