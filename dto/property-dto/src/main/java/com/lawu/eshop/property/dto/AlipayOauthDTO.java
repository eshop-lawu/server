package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class AlipayOauthDTO {
	
	@ApiModelProperty(value = "app支付宝授权参数", required = true)
    private String oauthInfo;

	public String getOauthInfo() {
		return oauthInfo;
	}

	public void setOauthInfo(String oauthInfo) {
		this.oauthInfo = oauthInfo;
	}
}