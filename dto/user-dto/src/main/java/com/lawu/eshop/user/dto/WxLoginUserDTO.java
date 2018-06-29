package com.lawu.eshop.user.dto;

import com.lawu.eshop.framework.web.impl.dto.TokenDTO;

public class WxLoginUserDTO extends TokenDTO{
	
	private Boolean isBind = false;
	
	private String openId;
	
	private String accessToken;

	public Boolean getIsBind() {
		return isBind;
	}

	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	

}
