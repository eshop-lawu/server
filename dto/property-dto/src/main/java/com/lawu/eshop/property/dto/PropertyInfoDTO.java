package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class PropertyInfoDTO {

	@ApiModelProperty(value = "账号", required = true)
	private String account;

	@ApiModelProperty(value = "账号类型", required = true)
	private String accountType;

	@ApiModelProperty(value = "用户编号")
	private String userNum;

	@ApiModelProperty(value = "余额")
	private BigDecimal balance;

	@ApiModelProperty(value = "积分")
	private BigDecimal point;

	@ApiModelProperty(value = "爱心账户")
	private BigDecimal loveAccount;
	
	@ApiModelProperty(value = "是否冻结(NO-否、YES-是)")
	private PropertyinfoFreezeEnum freeze;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public PropertyinfoFreezeEnum getFreeze() {
		return freeze;
	}

	public void setFreeze(PropertyinfoFreezeEnum freeze) {
		this.freeze = freeze;
	}

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

	public BigDecimal getLoveAccount() {
		return loveAccount;
	}

	public void setLoveAccount(BigDecimal loveAccount) {
		this.loveAccount = loveAccount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
