package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class IncomeMsgDOView {
	
	private String userNum;

	private BigDecimal money;

	private Byte bType;//类型

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Byte getbType() {
		return bType;
	}

	public void setbType(Byte bType) {
		this.bType = bType;
	}
}
