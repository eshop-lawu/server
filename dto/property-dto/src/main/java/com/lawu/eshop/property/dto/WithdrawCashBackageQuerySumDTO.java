package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class WithdrawCashBackageQuerySumDTO {

	@ApiModelProperty(value = "总成功笔数")
	private Integer successNums;
	
	@ApiModelProperty(value = "总成功金额")
	private BigDecimal successMoney;

	public Integer getSuccessNums() {
		return successNums;
	}

	public void setSuccessNums(Integer successNums) {
		this.successNums = successNums;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
	}

	

}
