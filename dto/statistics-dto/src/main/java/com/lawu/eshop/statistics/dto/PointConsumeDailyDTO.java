package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PointConsumeDailyDTO {

	private Long id;

    private Date gmtReport;

    private Date gmtCreate;

    private BigDecimal memberPoint;

    private BigDecimal merchantPoint;

    private BigDecimal totalPoint;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(BigDecimal memberPoint) {
		this.memberPoint = memberPoint;
	}

	public BigDecimal getMerchantPoint() {
		return merchantPoint;
	}

	public void setMerchantPoint(BigDecimal merchantPoint) {
		this.merchantPoint = merchantPoint;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

   
}