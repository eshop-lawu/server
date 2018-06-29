package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class BankThirdAccountDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String accountName;
	
	@ApiModelProperty(value = "账户名", required = true)
	private String accountNumber;

	@ApiModelProperty(value = "账户名", required = true)
	private String accountNumberBefore;

	@ApiModelProperty(value = "类型", required = true)
	private BankAccountTypeEnum bankAccountTypeEnum;

	@ApiModelProperty(value = "图标1(账户管理内)", required = true)
	private String iconUrl;

	@ApiModelProperty(value = "图标2(选择银行卡)", required = true)
	private String iconUrlSmall;

	@ApiModelProperty(value = "图标3(选择银行卡，缺省)", required = true)
	private String iconUrlSmallDefault;

	@ApiModelProperty(value = "背景图")
	private String bgUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BankAccountTypeEnum getBankAccountTypeEnum() {
		return bankAccountTypeEnum;
	}

	public void setBankAccountTypeEnum(BankAccountTypeEnum bankAccountTypeEnum) {
		this.bankAccountTypeEnum = bankAccountTypeEnum;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconUrlSmall() {
		return iconUrlSmall;
	}

	public void setIconUrlSmall(String iconUrlSmall) {
		this.iconUrlSmall = iconUrlSmall;
	}

	public String getIconUrlSmallDefault() {
		return iconUrlSmallDefault;
	}

	public void setIconUrlSmallDefault(String iconUrlSmallDefault) {
		this.iconUrlSmallDefault = iconUrlSmallDefault;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getAccountNumberBefore() {
		return accountNumberBefore;
	}

	public void setAccountNumberBefore(String accountNumberBefore) {
		this.accountNumberBefore = accountNumberBefore;
	}
}
