package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.List;

public class ReportGamePointBO {
	
	private String bdate;
	private String edate;
	private List<String> xAxisData;
	private List<BigDecimal> yAxisStandAloneExpendPointData;
	private List<BigDecimal> yAxisStandAloneIncomePointData;
	private List<BigDecimal> yAxisRandomExpendPointData;
	private List<BigDecimal> yAxisRandomIncomePointData;
	private List<BigDecimal> yAxisManyPeopleExpendPointData;
	private List<BigDecimal> yAxisManyPeopleIncomePointData;
	private List<BigDecimal> totalIncomePointData;
	
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
	public List<BigDecimal> getyAxisStandAloneExpendPointData() {
		return yAxisStandAloneExpendPointData;
	}
	public void setyAxisStandAloneExpendPointData(List<BigDecimal> yAxisStandAloneExpendPointData) {
		this.yAxisStandAloneExpendPointData = yAxisStandAloneExpendPointData;
	}
	public List<BigDecimal> getyAxisStandAloneIncomePointData() {
		return yAxisStandAloneIncomePointData;
	}
	public void setyAxisStandAloneIncomePointData(List<BigDecimal> yAxisStandAloneIncomePointData) {
		this.yAxisStandAloneIncomePointData = yAxisStandAloneIncomePointData;
	}
	public List<BigDecimal> getyAxisRandomExpendPointData() {
		return yAxisRandomExpendPointData;
	}
	public void setyAxisRandomExpendPointData(List<BigDecimal> yAxisRandomExpendPointData) {
		this.yAxisRandomExpendPointData = yAxisRandomExpendPointData;
	}
	public List<BigDecimal> getyAxisRandomIncomePointData() {
		return yAxisRandomIncomePointData;
	}
	public void setyAxisRandomIncomePointData(List<BigDecimal> yAxisRandomIncomePointData) {
		this.yAxisRandomIncomePointData = yAxisRandomIncomePointData;
	}
	public List<BigDecimal> getyAxisManyPeopleExpendPointData() {
		return yAxisManyPeopleExpendPointData;
	}
	public void setyAxisManyPeopleExpendPointData(List<BigDecimal> yAxisManyPeopleExpendPointData) {
		this.yAxisManyPeopleExpendPointData = yAxisManyPeopleExpendPointData;
	}
	public List<BigDecimal> getyAxisManyPeopleIncomePointData() {
		return yAxisManyPeopleIncomePointData;
	}
	public void setyAxisManyPeopleIncomePointData(List<BigDecimal> yAxisManyPeopleIncomePointData) {
		this.yAxisManyPeopleIncomePointData = yAxisManyPeopleIncomePointData;
	}

	public List<BigDecimal> getTotalIncomePointData() {
		return totalIncomePointData;
	}

	public void setTotalIncomePointData(List<BigDecimal> totalIncomePointData) {
		this.totalIncomePointData = totalIncomePointData;
	}
}
