package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ReportEarningParam {

	@NotNull(message = "adPoint不能为空")
	private BigDecimal adPoint;

	@NotNull(message = "userPoint不能为空")
	private BigDecimal userPoint;

	@NotNull(message = "lovePoint不能为空")
	private BigDecimal lovePoint;

	@NotNull(message = "platformPoint不能为空")
	private BigDecimal platformPoint;

	@NotNull(message = "gmtReport不能为空")
	private Date gmtReport;

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

}
