package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class UserRedpacketMaxMoneyDTO {
	@ApiModelProperty(value = "红包金额")
	private BigDecimal money;
	/**
	 * 是否可以领取红包
	 */
	@ApiModelProperty(value = "是否可以领取红包，true可以领取，false不能领取")
	private boolean flag;
	
	@ApiModelProperty(value = "红包是否领完")
	private boolean isGetAll;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isGetAll() {
		return isGetAll;
	}

	public void setGetAll(boolean isGetAll) {
		this.isGetAll = isGetAll;
	}
	
	

}
