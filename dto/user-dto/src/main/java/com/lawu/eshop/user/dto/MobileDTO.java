package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class MobileDTO {
	
	 @ApiModelProperty(value = "手机")
	 private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	 

}
