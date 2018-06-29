package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;

public class BusinessDepositQueryBO {

	private Long id;
	
	 private Long businessId;

	private String gmtPay;

	private String depositNumber;

	private String BusinessAccount;

	private String thirdNumber;

	private String BusinessName;
	
	private String userNum;

	private BigDecimal amount;

	private String payMethod;

	private BusinessDepositStatusEnum businessDepositStatusEnum;
	
	private String status;
	
	
	/////////////////////////////////////////////////////////////////////////////
	

	private String businessBankAccount;

	private String bankNo;

	private String bankName;

	private String bankBranchName;

	private String auditUserName;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getGmtPay() {
		return gmtPay;
	}

	public void setGmtPay(String gmtPay) {
		this.gmtPay = gmtPay;
	}

	public String getDepositNumber() {
		return depositNumber;
	}

	public void setDepositNumber(String depositNumber) {
		this.depositNumber = depositNumber;
	}

	public String getBusinessAccount() {
		return BusinessAccount;
	}

	public void setBusinessAccount(String businessAccount) {
		BusinessAccount = businessAccount;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusinessBankAccount() {
		return businessBankAccount;
	}

	public void setBusinessBankAccount(String businessBankAccount) {
		this.businessBankAccount = businessBankAccount;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BusinessDepositStatusEnum getBusinessDepositStatusEnum() {
		return businessDepositStatusEnum;
	}

	public void setBusinessDepositStatusEnum(BusinessDepositStatusEnum businessDepositStatusEnum) {
		this.businessDepositStatusEnum = businessDepositStatusEnum;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
