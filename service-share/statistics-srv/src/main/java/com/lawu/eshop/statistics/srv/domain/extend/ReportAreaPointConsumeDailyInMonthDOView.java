package com.lawu.eshop.statistics.srv.domain.extend;

import java.math.BigDecimal;

public class ReportAreaPointConsumeDailyInMonthDOView {
	
	private BigDecimal memberRechargePoint;
	
	private BigDecimal merchantRechargePoint;
	
	private BigDecimal memberPoint;
	
	private BigDecimal merchantPoint;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;

	public BigDecimal getMemberRechargePoint() {
		return memberRechargePoint;
	}

	public void setMemberRechargePoint(BigDecimal memberRechargePoint) {
		this.memberRechargePoint = memberRechargePoint;
	}

	public BigDecimal getMerchantRechargePoint() {
		return merchantRechargePoint;
	}

	public void setMerchantRechargePoint(BigDecimal merchantRechargePoint) {
		this.merchantRechargePoint = merchantRechargePoint;
	}

	public BigDecimal getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(BigDecimal memberPoint) {
		this.memberPoint = memberPoint;
	}

	public BigDecimal getMerchantPoint() {
		return merchantPoint;
	}

	public void setMerchantPoint(BigDecimal merchantPoint) {
		this.merchantPoint = merchantPoint;
	}

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
	
}
