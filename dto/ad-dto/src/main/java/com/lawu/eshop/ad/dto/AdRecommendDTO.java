package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdRecommendDTO extends AdPlatformDTO{
	
	@ApiModelProperty(value = "价格")
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	

}
