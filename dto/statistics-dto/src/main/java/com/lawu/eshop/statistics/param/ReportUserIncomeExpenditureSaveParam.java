package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;

/**
 * 用户收入和支出保存参数
 * 
 * @author Sunny
 * @date 2017年7月4日
 */
public class ReportUserIncomeExpenditureSaveParam {
	
	/**
	 * 用户编号
	 */
	private String userNum;
	
	/**
	 * 用户账号
	 */
	private String account;
	
	/**
	 * 收入金额
	 */
	private BigDecimal income;
	
	/**
	 * 支出金额
	 */
	private BigDecimal expenditure;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}
	
}
