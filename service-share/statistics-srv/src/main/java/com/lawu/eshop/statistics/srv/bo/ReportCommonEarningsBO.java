package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.List;

public class ReportCommonEarningsBO {
	
	private String bdate;
	private String edate;
	private List<String> xAxisData;
	private List<BigDecimal> yAxisAdPointData;
	private List<BigDecimal> yAxisUserPointData;
	private List<BigDecimal> yAxisLovePointData;
	private List<BigDecimal> yAxisPlateformPointData;
	
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		this.bdate = bdate;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	public List<String> getxAxisData() {
		return xAxisData;
	}
	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}
	public List<BigDecimal> getyAxisAdPointData() {
		return yAxisAdPointData;
	}
	public void setyAxisAdPointData(List<BigDecimal> yAxisAdPointData) {
		this.yAxisAdPointData = yAxisAdPointData;
	}
	public List<BigDecimal> getyAxisUserPointData() {
		return yAxisUserPointData;
	}
	public void setyAxisUserPointData(List<BigDecimal> yAxisUserPointData) {
		this.yAxisUserPointData = yAxisUserPointData;
	}
	public List<BigDecimal> getyAxisLovePointData() {
		return yAxisLovePointData;
	}
	public void setyAxisLovePointData(List<BigDecimal> yAxisLovePointData) {
		this.yAxisLovePointData = yAxisLovePointData;
	}
	public List<BigDecimal> getyAxisPlateformPointData() {
		return yAxisPlateformPointData;
	}
	public void setyAxisPlateformPointData(List<BigDecimal> yAxisPlateformPointData) {
		this.yAxisPlateformPointData = yAxisPlateformPointData;
	}
	
	

}
