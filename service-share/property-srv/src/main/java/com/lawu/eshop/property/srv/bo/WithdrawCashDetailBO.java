package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class WithdrawCashDetailBO {
	private Long id;
	private BigDecimal cashMoney;//出账金额
	private String title;//商品
	private BigDecimal money;//提现金额
	private String cashStatus;
	private String cdate;
	private String bankInfo;
	private String cashNumber;
	private String acceptDate;
	private String finishDate;
	private String faildReason;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCashStatus() {
		return cashStatus;
	}
	public void setCashStatus(String cashStatus) {
		this.cashStatus = cashStatus;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}
	public String getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getFaildReason() {
		return faildReason;
	}
	public void setFaildReason(String faildReason) {
		this.faildReason = faildReason;
	}
	
}
