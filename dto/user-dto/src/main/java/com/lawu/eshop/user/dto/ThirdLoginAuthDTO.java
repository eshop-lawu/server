package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class ThirdLoginAuthDTO {
	
	@ApiModelProperty(value = "wxAppId")
	private String wxAppId;
	
	@ApiModelProperty(value = "qqAppId")
	private String qqAppId;

	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getQqAppId() {
		return qqAppId;
	}

	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}

	


	
	

}
