package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReportAreaPointConsumeDailyDTO {

	private BigDecimal memberRechargePoint;

    private BigDecimal merchantRechargePoint;

    private BigDecimal totalRechargePoint;

    private BigDecimal memberPoint;

    private BigDecimal merchantPoint;

    private BigDecimal totalPoint;
    
    private Date gmtReport;

	public BigDecimal getMemberRechargePoint() {
		return memberRechargePoint;
	}

	public void setMemberRechargePoint(BigDecimal memberRechargePoint) {
		this.memberRechargePoint = memberRechargePoint;
	}

	public BigDecimal getMerchantRechargePoint() {
		return merchantRechargePoint;
	}

	public void setMerchantRechargePoint(BigDecimal merchantRechargePoint) {
		this.merchantRechargePoint = merchantRechargePoint;
	}

	public BigDecimal getTotalRechargePoint() {
		return totalRechargePoint;
	}

	public void setTotalRechargePoint(BigDecimal totalRechargePoint) {
		this.totalRechargePoint = totalRechargePoint;
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

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}
}
