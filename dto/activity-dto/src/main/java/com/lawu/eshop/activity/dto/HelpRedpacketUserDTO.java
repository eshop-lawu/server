package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class HelpRedpacketUserDTO {
	
	@ApiModelProperty(value = "账号")
	private String account;
	
	@ApiModelProperty(value = "所得金额")
	private BigDecimal money;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	

}
