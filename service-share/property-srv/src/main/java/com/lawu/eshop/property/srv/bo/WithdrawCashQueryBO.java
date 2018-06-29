package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.property.constants.CashStatusEnum;

public class WithdrawCashQueryBO {
	
	private Long id;
	
	private String title;
	
	private BigDecimal cashMoney;
	
	private BigDecimal ActualCashMoney;
	
	private BigDecimal chargedMoney;
	
	private CashStatusEnum cashStatusEnum;
	
	private Date cdate;
	
	private Date acceptDate;

	private Date finishDate;
	
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public CashStatusEnum getCashStatusEnum() {
		return cashStatusEnum;
	}

	public void setCashStatusEnum(CashStatusEnum cashStatusEnum) {
		this.cashStatusEnum = cashStatusEnum;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getActualCashMoney() {
		return ActualCashMoney;
	}

	public void setActualCashMoney(BigDecimal actualCashMoney) {
		ActualCashMoney = actualCashMoney;
	}

	public BigDecimal getChargedMoney() {
		return chargedMoney;
	}

	public void setChargedMoney(BigDecimal chargedMoney) {
		this.chargedMoney = chargedMoney;
	}
	
}
