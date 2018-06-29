package com.lawu.eshop.agent.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class AreaAdPointDailyDTO {

	private Long id;
	
	@ApiModelProperty(value = "投放总积分")
	private BigDecimal reportTotalPoint;
	
	@ApiModelProperty(value = "省编号")
	private Integer provinceId;
	
	@ApiModelProperty(value = "市编号")
	private Integer cityId;
	
	@ApiModelProperty(value = "区编号")
	private Integer areaId;
	
	@ApiModelProperty(value = "统计的时间")
	private Date gmtReport;
	
	@ApiModelProperty(value = "创建的时间")
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getReportTotalPoint() {
		return reportTotalPoint;
	}

	public void setReportTotalPoint(BigDecimal reportTotalPoint) {
		this.reportTotalPoint = reportTotalPoint;
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

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
