package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class WithdrawCashReportDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	
	@ApiModelProperty(value = "提现金额", required = true)
	private BigDecimal cashMoney;
	
	@ApiModelProperty(value = "成功/失败时间")
	private String finishDate;
	
	@ApiModelProperty(value = "用户编号")
	private String userNum;

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

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	
}
