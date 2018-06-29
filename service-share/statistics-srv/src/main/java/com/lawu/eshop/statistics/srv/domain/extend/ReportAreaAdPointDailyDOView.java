package com.lawu.eshop.statistics.srv.domain.extend;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAreaAdPointDailyDOView {

	private BigDecimal reportTotalPoint;
	
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
