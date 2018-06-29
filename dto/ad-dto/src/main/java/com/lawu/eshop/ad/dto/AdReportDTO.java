package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AdReportDTO {
	
	private BigDecimal loveTotlePoint;
	
	private BigDecimal userTotlePoint;
	
	private BigDecimal adTotlePoint ;
	

	public BigDecimal getLoveTotlePoint() {
		return loveTotlePoint;
	}

	public void setLoveTotlePoint(BigDecimal loveTotlePoint) {
		this.loveTotlePoint = loveTotlePoint;
	}

	public BigDecimal getUserTotlePoint() {
		return userTotlePoint;
	}

	public void setUserTotlePoint(BigDecimal userTotlePoint) {
		this.userTotlePoint = userTotlePoint;
	}

	public BigDecimal getAdTotlePoint() {
		return adTotlePoint;
	}

	public void setAdTotlePoint(BigDecimal adTotlePoint) {
		this.adTotlePoint = adTotlePoint;
	}


}
