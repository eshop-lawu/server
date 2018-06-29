package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

public class WxMemberBindParam {
	
	@ApiModelProperty(value = "注册账号", required = true)
	private String account;

	@ApiModelProperty(value = "手机验证码", required = true)
	private String smsCode;
	
	@ApiModelProperty(value = "openId", required = true)
	private String openId;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	

}
