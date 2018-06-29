package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReportCommonBackDTO {
	private String bdate;
	private String edate;
	private List<String> xAxisData;
	private List<BigDecimal> yAxisMemberData;
	private List<BigDecimal> yAxisMerchantData;
	private List<BigDecimal> yAxisTotalData;
	public List<String> getxAxisData() {
		return xAxisData;
	}
	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}
	public List<BigDecimal> getyAxisMemberData() {
		return yAxisMemberData;
	}
	public void setyAxisMemberData(List<BigDecimal> yAxisMemberData) {
		this.yAxisMemberData = yAxisMemberData;
	}
	public List<BigDecimal> getyAxisMerchantData() {
		return yAxisMerchantData;
	}
	public void setyAxisMerchantData(List<BigDecimal> yAxisMerchantData) {
		this.yAxisMerchantData = yAxisMerchantData;
	}
	public List<BigDecimal> getyAxisTotalData() {
		return yAxisTotalData;
	}
	public void setyAxisTotalData(List<BigDecimal> yAxisTotalData) {
		this.yAxisTotalData = yAxisTotalData;
	}
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
	
   
}