package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class WithdrawCashDetailDTO {
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	@ApiModelProperty(value = "出账金额", required = true)
	private BigDecimal cashMoney;
	@ApiModelProperty(value = "商品", required = true)
	private String title;
	@ApiModelProperty(value = "提现金额", required = true)
	private BigDecimal money;
	@ApiModelProperty(value = "状态", required = true)
	private String cashStatus;
	@ApiModelProperty(value = "提现申请时间", required = true)
	private String cdate;
	@ApiModelProperty(value = "受理时间")
	private String acceptDate;
	@ApiModelProperty(value = "成功/失败时间")
	private String finishDate;
	@ApiModelProperty(value = "银行信息", required = true)
	private String bankInfo;
	@ApiModelProperty(value = "提现单号", required = true)
	private String cashNumber;
	@ApiModelProperty(value = "失败原因")
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
