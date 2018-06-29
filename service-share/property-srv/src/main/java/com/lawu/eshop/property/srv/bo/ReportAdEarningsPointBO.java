package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class ReportAdEarningsPointBO {
	
	private BigDecimal userTotalPoint;

	private BigDecimal loveTotalPoint;

	public BigDecimal getUserTotalPoint() {
		return userTotalPoint;
	}

	public void setUserTotalPoint(BigDecimal userTotalPoint) {
		this.userTotalPoint = userTotalPoint;
	}

	public BigDecimal getLoveTotalPoint() {
		return loveTotalPoint;
	}

	public void setLoveTotalPoint(BigDecimal loveTotalPoint) {
		this.loveTotalPoint = loveTotalPoint;
	}
	
	
	
}
