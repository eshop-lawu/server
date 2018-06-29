package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class WithdrawCashReportBO {
	
	private Long id;
	
	private BigDecimal cashMoney;
	
	private String finishDate;
	
	private String userNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	
}
