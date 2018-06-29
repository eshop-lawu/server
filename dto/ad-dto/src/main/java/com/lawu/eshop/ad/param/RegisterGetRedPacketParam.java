package com.lawu.eshop.ad.param;

import io.swagger.annotations.ApiParam;

public class RegisterGetRedPacketParam {
	
	@ApiParam (name="verifyCodeId",required = true, value = "验证码id")
	private Long verifyCodeId;
	
	@ApiParam (name="account",required = true, value = "注册账号")
	private String account;
	
	@ApiParam (name="pwd",required = true, value = "密码")
	private String pwd;
	
	@ApiParam (name="smsCode",required = true, value = "手机验证码")
	private String smsCode;
	
	@ApiParam (name="merchantId",required = true, value = "商家id")
	private Long merchantId;

	
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

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getVerifyCodeId() {
		return verifyCodeId;
	}

	public void setVerifyCodeId(Long verifyCodeId) {
		this.verifyCodeId = verifyCodeId;
	}

	
	

}
