package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author hongqm
 *
 */
public class MerchantInfoFromPublishAdDTO extends MerchantProfileDTO{

	@ApiModelProperty(value = "logo")
	private String logoUrl;

	@ApiModelProperty(value = "门店名称")
	private String storeName;

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
