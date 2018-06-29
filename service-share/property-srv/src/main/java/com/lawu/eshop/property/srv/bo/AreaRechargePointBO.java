package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class AreaRechargePointBO {

private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;
	
	private BigDecimal totalMoney;
	
	private String type;

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
