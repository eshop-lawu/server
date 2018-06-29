package com.lawu.eshop.user.srv.bo;

import com.lawu.eshop.user.constants.UserSexEnum;

public class QqLoginMemberBO {
	
	private Long id;

	private String num;

	private String account;

	private String ryToken;

	private Boolean isFreeze;

	private UserSexEnum userSex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRyToken() {
		return ryToken;
	}

	public void setRyToken(String ryToken) {
		this.ryToken = ryToken;
	}

	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public UserSexEnum getUserSex() {
		return userSex;
	}

	public void setUserSex(UserSexEnum userSex) {
		this.userSex = userSex;
	}


}
