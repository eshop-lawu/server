package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * 用户收支记录DTO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class ReportUserIncomeExpenditureDTO {
	
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户类型
     */
    private UserTypeEnum userType;

    /**
     * 收入金额
     */
    private BigDecimal income;

    /**
     * 支出金额
     */
    private BigDecimal expenditure;

    /**
     * 差值金额
     */
    private BigDecimal difference;

    /**
     * 统计日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtReport;

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

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
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

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public Date getGmtReport() {
		return gmtReport;
	}

	public void setGmtReport(Date gmtReport) {
		this.gmtReport = gmtReport;
	}

}