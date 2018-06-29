package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportEarningsDailyDTO {
	
	
	private Long id;

	
	private BigDecimal adPoint;

	
	private BigDecimal userPoint;

	
	private BigDecimal lovePoint;

	
	private BigDecimal platformPoint;

	
	private Date gmtReport;

	
	private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAdPoint() {
		return adPoint;
	}

	public void setAdPoint(BigDecimal adPoint) {
		this.adPoint = adPoint;
	}

	public BigDecimal getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(BigDecimal userPoint) {
		this.userPoint = userPoint;
	}

	public BigDecimal getLovePoint() {
		return lovePoint;
	}

	public void setLovePoint(BigDecimal lovePoint) {
		this.lovePoint = lovePoint;
	}

	public BigDecimal getPlatformPoint() {
		return platformPoint;
	}

	public void setPlatformPoint(BigDecimal platformPoint) {
		this.platformPoint = platformPoint;
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
