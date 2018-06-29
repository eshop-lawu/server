package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class PointConsumeReportBO {

	private BigDecimal memberRechargeMoney;

	private BigDecimal sumRechargeMoney;

	private BigDecimal merchantRechargeMoney;

	public BigDecimal getMemberRechargeMoney() {
		return memberRechargeMoney;
	}

	public void setMemberRechargeMoney(BigDecimal memberRechargeMoney) {
		this.memberRechargeMoney = memberRechargeMoney;
	}

	public BigDecimal getSumRechargeMoney() {
		return sumRechargeMoney;
	}

	public void setSumRechargeMoney(BigDecimal sumRechargeMoney) {
		this.sumRechargeMoney = sumRechargeMoney;
	}

	public BigDecimal getMerchantRechargeMoney() {
		return merchantRechargeMoney;
	}

	public void setMerchantRechargeMoney(BigDecimal merchantRechargeMoney) {
		this.merchantRechargeMoney = merchantRechargeMoney;
	}

}
