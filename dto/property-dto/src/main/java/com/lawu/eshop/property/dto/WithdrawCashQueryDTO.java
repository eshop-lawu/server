package com.lawu.eshop.property.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.CashStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class WithdrawCashQueryDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	
	@ApiModelProperty(value = "标题", required = true)
	private String title;
	
	@ApiModelProperty(value = "提现金额", required = true)
	private BigDecimal cashMoney;
	
	@ApiModelProperty(value = "实际到账金额", required = true)
	private BigDecimal ActualCashMoney;
	
	@ApiModelProperty(value = "手续费", required = true)
	private BigDecimal chargedMoney;
	
	@ApiModelProperty(value = "状态(APPLY-申请中-, ACCEPT-受理中, SUCCESS-成功, FAILURE-失败)", required = true)
	private CashStatusEnum cashStatusEnum;
	
	@ApiModelProperty(value = "提现时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date cdate;
	
	@ApiModelProperty(value = "受理时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date acceptDate;

	@ApiModelProperty(value = "完成时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date finishDate;
	
	@ApiModelProperty(value = "提现描述(失败时显示原因)", required = true)
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
