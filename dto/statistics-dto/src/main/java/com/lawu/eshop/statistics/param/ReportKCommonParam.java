package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class ReportKCommonParam {
   
	@NotNull(message="gmtReport不能为空")
    private Date gmtReport;

	@NotNull(message="gmtCreate不能为空")
    private Date gmtCreate;

	@NotNull(message="memberMoney不能为空")
	private BigDecimal memberMoney;

	@NotNull(message="merchantMoney不能为空")
    private BigDecimal merchantMoney;

	@NotNull(message="totalMoney不能为空")
    private BigDecimal totalMoney;

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