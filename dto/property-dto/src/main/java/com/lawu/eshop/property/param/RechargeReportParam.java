package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RechargeReportParam {
	
	@NotBlank(message = "date不能为空")
	private String date;
	
	@NotNull(message = "status不能为空")
	private Byte status;
	
	@NotNull(message = "rechargeType不能为空")
	private Byte rechargeType;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Byte getRechargeType() {
		return rechargeType;
	}
	public void setRechargeType(Byte rechargeType) {
		this.rechargeType = rechargeType;
	}
}
