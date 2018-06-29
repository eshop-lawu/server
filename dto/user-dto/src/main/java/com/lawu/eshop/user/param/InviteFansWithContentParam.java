package com.lawu.eshop.user.param;

import io.swagger.annotations.ApiModelProperty;

public class InviteFansWithContentParam extends InviteFansParam{

	@ApiModelProperty(value = "邀请内容的图片", required = true)
	private String Url;
	
	@ApiModelProperty(value = "商家LOGO", required = true)
	private String logoUrl;
	
	@ApiModelProperty(value = "门店名称", required = true)
	private String merchantStoreName;
	
	@ApiModelProperty(value = "邀请内容", required = true)
	private String inviteContent;
	
	@ApiModelProperty(value = "门店简介", required = true)
	private String merchantStoreIntro;


	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}

	public String getInviteContent() {
		return inviteContent;
	}

	public void setInviteContent(String inviteContent) {
		this.inviteContent = inviteContent;
	}

	public String getMerchantStoreIntro() {
		return merchantStoreIntro;
	}

	public void setMerchantStoreIntro(String merchantStoreIntro) {
		this.merchantStoreIntro = merchantStoreIntro;
	}
	
	
	
}
