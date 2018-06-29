package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiParam;

public class WeixinUserBindParam {
	
	@ApiParam(name = "account", value = "账号",required = true)
	private String account;
	
	@ApiParam(name = "pwd", value = "密码",required = true)
	private String pwd;
	
	@ApiParam(name = "openId", value = "openId",required = true)
	private String openId;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	

}
