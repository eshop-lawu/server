package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;

public class ReportAreaVolumnDailyInMonthDTO {
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;
	
	private byte type;
	
	private BigDecimal totalMoney;

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

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
}
