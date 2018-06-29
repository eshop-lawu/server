package com.lawu.eshop.property.param;

import io.swagger.annotations.ApiParam;

public class BankParam {
	
	@ApiParam (name="userNum",required = true, value = "用户编号")
	private String userNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	
	

}
