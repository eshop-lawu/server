package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

public class ReportWithdrawDailyBO {

	private Long id;

    private Date gmtReport;

    private Date gmtCreate;

	private BigDecimal memberMoney;

    private BigDecimal merchantMoney;

    private BigDecimal totalMoney;
	
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

	public BigDecimal getMemberMoney() {
		return memberMoney;
	}

	public void setMemberMoney(BigDecimal memberMoney) {
		this.memberMoney = memberMoney;
	}

	public BigDecimal getMerchantMoney() {
		return merchantMoney;
	}

	public void setMerchantMoney(BigDecimal merchantMoney) {
		this.merchantMoney = merchantMoney;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

}