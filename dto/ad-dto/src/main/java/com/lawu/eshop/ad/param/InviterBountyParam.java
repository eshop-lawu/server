package com.lawu.eshop.ad.param;

import java.math.BigDecimal;

public class InviterBountyParam {
	
	/**
	 * 操作人
	 */
	private Long auditorId ;
	
	/**
	 * 设置金额
	 */
	private BigDecimal money;

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	

}
