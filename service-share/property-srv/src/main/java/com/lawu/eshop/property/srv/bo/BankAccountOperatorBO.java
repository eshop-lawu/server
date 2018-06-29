package com.lawu.eshop.property.srv.bo;

import java.util.Date;

public class BankAccountOperatorBO {
	
	private Long id;
	
	private String accountName;
	
	private String accountNumber;
	
	private String subBranchName;
	
	private String bankName;
	
	private String iconUrl;
	
	private Integer bankId;
	
	private String remark;
	
	private Integer auditorId;
	
	private Date auditorTime;


	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public Date getAuditorTime() {
		return auditorTime;
	}

	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}

	
	
	

}
