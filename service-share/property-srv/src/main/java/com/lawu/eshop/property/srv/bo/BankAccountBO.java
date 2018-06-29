package com.lawu.eshop.property.srv.bo;

public class BankAccountBO {
	
	private Long id;
	
	private String accountName;
	
	private String accountNumber;

	private Byte accountType;
	
	private String subBranchName;
	
	private String bankName;
	
	private String iconUrl;

	private String bgUrl;
	
	private Integer bankId;
	
	private String regionPath;
	
	private String regionName;

	private String note;

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

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Byte getAccountType() {
		return accountType;
	}

	public void setAccountType(Byte accountType) {
		this.accountType = accountType;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
