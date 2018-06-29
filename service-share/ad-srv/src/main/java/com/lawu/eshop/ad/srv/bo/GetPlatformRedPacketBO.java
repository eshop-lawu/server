package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;

public class GetPlatformRedPacketBO {
	
	private Long id;
	
	private BigDecimal money;
	
	private  boolean isGet;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public boolean isGet() {
		return isGet;
	}

	public void setGet(boolean isGet) {
		this.isGet = isGet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
