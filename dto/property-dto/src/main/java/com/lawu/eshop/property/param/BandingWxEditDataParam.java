package com.lawu.eshop.property.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

public class BandingWxEditDataParam extends BandingWxEditParam {

	private String userNum;

	private UserTypeEnum userTypeEnum;

	private String accountNumber;//openid

	private String wxNickname;//微信昵称

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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}
}
