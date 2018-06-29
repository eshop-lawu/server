package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

public class BusinessDepositSaveDataParam extends BusinessDepositSaveParam {

	@NotNull(message="businessId不能为空")
	private Long businessId;
	
	@NotBlank(message="userNum不能为空")
	private String userNum;
	
	@NotBlank(message="businessAccount不能为空")
	private String businessAccount;
	
	@ApiParam(name = "businessName",required = true, value = "商家门店负责人姓名")
	@NotBlank(message="businessName不能为空")
	private String businessName;
	
	@ApiParam(name = "provinceId",required = true, value = "省ID")
	@NotBlank(message="provinceId不能为空")
	private String provinceId;
	
	@ApiParam(name = "cityId",required = true, value = "市ID")
	@NotBlank(message="cityId不能为空")
	private String cityId;
	
	@ApiParam(name = "areaId",required = true, value = "区ID")
	@NotBlank(message="areaId不能为空")
	private String areaId;
	
	private String deposit;

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	public String getBusinessAccount() {
		return businessAccount;
	}

	public void setBusinessAccount(String businessAccount) {
		this.businessAccount = businessAccount;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}
