package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class BankAccountNameDTO {
	
	@ApiModelProperty(value = "银行卡用户名称")
	private String accountName;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	

}
