package com.lawu.eshop.statistics.srv.domain.extend;

import java.math.BigDecimal;

public class ReportAreaAdPointDailyInMonthDOView {

	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;
	
	private BigDecimal totalPoint;

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

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	
}
