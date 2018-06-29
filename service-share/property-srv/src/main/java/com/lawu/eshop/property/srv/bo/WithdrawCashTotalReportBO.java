package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class WithdrawCashTotalReportBO {

	//用户提现金额
	private BigDecimal memberCashMoney;

	//商家提现金额
	private BigDecimal merchantCashMoney;

	public BigDecimal getMemberCashMoney() {
		return memberCashMoney;
	}

	public void setMemberCashMoney(BigDecimal memberCashMoney) {
		this.memberCashMoney = memberCashMoney;
	}

	public BigDecimal getMerchantCashMoney() {
		return merchantCashMoney;
	}

	public void setMerchantCashMoney(BigDecimal merchantCashMoney) {
		this.merchantCashMoney = merchantCashMoney;
	}
	
	
}
