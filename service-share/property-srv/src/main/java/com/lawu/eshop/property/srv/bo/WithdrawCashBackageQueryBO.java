package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import io.swagger.annotations.ApiModelProperty;

public class WithdrawCashBackageQueryBO {

	private Long id;
	
	private String userNum;

	private String account;

	private String name;

	private String regionFullName;

	private BigDecimal cashMoney;

	private BigDecimal poundage;

	private BigDecimal money;

	private String status;
	
	private CashStatusEnum cashStatsuEnum;

	private String businessBankAccount;
	
	private String bankNo;
	
	private String bankName;
	
	private String bankBranchName;

	private String cashNumber;

	private String auditUserName;

	private String auditFaildReason;

	private String gmtCreate;

	private String gmtModified;

	private String bankRegionName;

	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
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

	public String getCashNumber() {
		return cashNumber;
	}

	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getAuditFaildReason() {
		return auditFaildReason;
	}

	public void setAuditFaildReason(String auditFaildReason) {
		this.auditFaildReason = auditFaildReason;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}

	public CashStatusEnum getCashStatsuEnum() {
		return cashStatsuEnum;
	}

	public void setCashStatsuEnum(CashStatusEnum cashStatsuEnum) {
		this.cashStatsuEnum = cashStatsuEnum;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public BigDecimal getPoundage() {
		return poundage;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}

	public String getBankRegionName() {
		return bankRegionName;
	}

	public void setBankRegionName(String bankRegionName) {
		this.bankRegionName = bankRegionName;
	}
}
