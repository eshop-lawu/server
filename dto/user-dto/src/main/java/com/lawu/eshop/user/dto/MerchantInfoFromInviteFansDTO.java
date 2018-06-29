package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

public class MerchantInfoFromInviteFansDTO {

	@ApiModelProperty(value = "门店ID")
	private Long merchantStoreId;
	
	@ApiModelProperty(value = "门店照")
	private String merchantStoreUrl;
	
	@ApiModelProperty(value = "门店logo")
	private String merchantStoreLogo;
	
	@ApiModelProperty(value = "门店简介")
	private String merchantStoreIntro;
	
	@ApiModelProperty(value = "门店名称")
	private String merchantStoreName;


	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantStoreUrl() {
		return merchantStoreUrl;
	}

	public void setMerchantStoreUrl(String merchantStoreUrl) {
		this.merchantStoreUrl = merchantStoreUrl;
	}

	public String getMerchantStoreLogo() {
		return merchantStoreLogo;
	}

	public void setMerchantStoreLogo(String merchantStoreLogo) {
		this.merchantStoreLogo = merchantStoreLogo;
	}


	public String getMerchantStoreIntro() {
		return merchantStoreIntro;
	}

	public void setMerchantStoreIntro(String merchantStoreIntro) {
		this.merchantStoreIntro = merchantStoreIntro;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}
	
}
