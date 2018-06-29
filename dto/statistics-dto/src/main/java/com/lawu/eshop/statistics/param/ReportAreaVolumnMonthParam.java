package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAreaVolumnMonthParam {

	private BigDecimal reportTotalMoney;
	
	private byte type;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private Integer areaId;
	
	private Date gmtCreate;
	
	private Date gmtReport;
	

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}

	public BigDecimal getReportTotalMoney() {
		return reportTotalMoney;
	}

	public void setReportTotalMoney(BigDecimal reportTotalMoney) {
		this.reportTotalMoney = reportTotalMoney;
	}


	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
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
