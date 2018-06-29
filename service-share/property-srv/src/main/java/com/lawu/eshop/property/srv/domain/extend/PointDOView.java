package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class PointDOView {
	
	private String userType;
	
	private BigDecimal sumRechargeMoney;


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public BigDecimal getSumRechargeMoney() {
		return sumRechargeMoney;
	}

	public void setSumRechargeMoney(BigDecimal sumRechargeMoney) {
		this.sumRechargeMoney = sumRechargeMoney;
	}
	
	

}
