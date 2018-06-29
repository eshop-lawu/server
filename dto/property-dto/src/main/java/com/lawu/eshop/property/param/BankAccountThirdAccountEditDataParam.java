package com.lawu.eshop.property.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

public class BankAccountThirdAccountEditDataParam extends BankAccountThirdAccountEditParam {

	private String userNum;

	private UserTypeEnum userTypeEnum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public UserTypeEnum getUserTypeEnum() {
		return userTypeEnum;
	}

	public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
		this.userTypeEnum = userTypeEnum;
	}

}
