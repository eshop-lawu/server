package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class RechargeDOView {
	
	private byte userType;
	
	private BigDecimal sumRechargeMoney;

	public byte getUserType() {
		return userType;
	}

	public void setUserType(byte userType) {
		this.userType = userType;
	}

	public BigDecimal getSumRechargeMoney() {
		return sumRechargeMoney;
	}

	public void setSumRechargeMoney(BigDecimal sumRechargeMoney) {
		this.sumRechargeMoney = sumRechargeMoney;
	}
	
	

}
