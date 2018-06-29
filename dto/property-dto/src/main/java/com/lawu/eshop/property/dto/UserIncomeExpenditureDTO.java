package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

/**
 * 用户支出收入DTO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class UserIncomeExpenditureDTO {

	/**
	 * 用户编号
	 */
	private String userNum;

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