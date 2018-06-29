package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class BankAccountDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String accountName;
	
	@ApiModelProperty(value = "卡号", required = true)
	private String accountNumber;

	@ApiModelProperty(value = "卡号后4位", required = true)
	private String accountNumberSuffix;

	@ApiModelProperty(value = "支行", required = true)
	private String subBranchName;
	
	@ApiModelProperty(value = "银行名称", required = true)
	private String bankName;

	@ApiModelProperty(value = "银行卡大图标", required = true)
	private String iconUrl;

	@ApiModelProperty(value = "银行卡小图标", required = true)
	private String iconUrlSmall;

	@ApiModelProperty(value = "所属银行主键", required = true)
	private Integer bankId;
	
	@ApiModelProperty(value = "省市区路径")
	private String regionPath;
	
	@ApiModelProperty(value = "省市区名称")
	private String regionName;

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

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getIconUrlSmall() {
		return iconUrlSmall;
	}

	public void setIconUrlSmall(String iconUrlSmall) {
		this.iconUrlSmall = iconUrlSmall;
	}

	public String getAccountNumberSuffix() {
		return accountNumberSuffix;
	}

	public void setAccountNumberSuffix(String accountNumberSuffix) {
		this.accountNumberSuffix = accountNumberSuffix;
	}
}
