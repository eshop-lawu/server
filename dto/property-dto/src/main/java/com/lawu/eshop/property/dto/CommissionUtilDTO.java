package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

public class CommissionUtilDTO {
	
	private BigDecimal actureMoneyIn;//实际收入
	private BigDecimal actureLoveIn;//爱心账户
	
	public BigDecimal getActureMoneyIn() {
		return actureMoneyIn;
	}
	public void setActureMoneyIn(BigDecimal actureMoneyIn) {
		this.actureMoneyIn = actureMoneyIn;
	}
	public BigDecimal getActureLoveIn() {
		return actureLoveIn;
	}
	public void setActureLoveIn(BigDecimal actureLoveIn) {
		this.actureLoveIn = actureLoveIn;
	}
	
}
