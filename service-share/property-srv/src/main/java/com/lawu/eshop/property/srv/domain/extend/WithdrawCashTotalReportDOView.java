package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class WithdrawCashTotalReportDOView {

	private BigDecimal cashMoney;

	private Integer userType;

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
