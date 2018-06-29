package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

public class InviteRecordDTO {
	
	@ApiModelProperty(value = "图像")
	private String headImg;
	
	@ApiModelProperty(value = "图像")
	private String account;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	

	
}
