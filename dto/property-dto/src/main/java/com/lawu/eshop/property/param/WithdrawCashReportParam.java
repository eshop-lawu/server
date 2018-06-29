package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class WithdrawCashReportParam {
	
	@NotBlank(message = "date不能为空")
	private String date;

	@NotNull(message = "status不能为空")
	private Byte status;
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
	
	
}
