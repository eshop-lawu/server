package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class UserTopDTO {

	@ApiModelProperty(value = "头像")
	private String headimg;

	@ApiModelProperty(value = "手机")
	private String mobile;
	
	@ApiModelProperty(value = "地区")
	private String regionPath;
	
	@ApiModelProperty(value = "积分")
	private BigDecimal money;

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	

}
