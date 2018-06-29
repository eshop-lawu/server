package com.lawu.eshop.property.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

import io.swagger.annotations.ApiParam;

public class BankAccountParam {
	
	@ApiParam (name="accountName",required = true, value = "开户名")
	private String accountName;
	 
	@ApiParam (name="accountNumber",required = true, value = "银行卡号")
	private String accountNumber;
	 
	@ApiParam (name="bankId",required = true, value = "所属银行")
	private Integer bankId;
	 
	@ApiParam (name="subBranchName",required = true, value = "支行名称")
	private String subBranchName;
	
	@ApiParam (name="userType", value = "用户类型")
	private UserTypeEnum userType;
	
	@ApiParam (name="regionPath", value = "省市区路径  11/1101/110101")
	private String regionPath;
	
	@ApiParam (name="regionName", value = "省市区名称")
	private String regionName;

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

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
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
	 
	
	 

}
