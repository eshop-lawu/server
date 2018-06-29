package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class ReportAreaAdPorintDailyByAreaIdDTO {

	@ApiModelProperty(value = "投放总积分")
	private BigDecimal reportTotalPoint;
	
	@ApiModelProperty(value = "日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date gmtReport;

	public BigDecimal getReportTotalPoint() {
		return reportTotalPoint;
	}

	public void setReportTotalPoint(BigDecimal reportTotalPoint) {
		this.reportTotalPoint = reportTotalPoint;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}
}
