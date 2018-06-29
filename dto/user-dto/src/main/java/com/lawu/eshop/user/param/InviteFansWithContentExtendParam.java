package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.InviteTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class InviteFansWithContentExtendParam extends InviteFansParam{

	@ApiModelProperty(value = "邀请内容的图片", required = true)
	private String url;
	
	@ApiModelProperty(value = "商家LOGO", required = true)
	private String logoUrl;
	
	@ApiModelProperty(value = "门店名称", required = true)
	private String merchantStoreName;
	
	@ApiModelProperty(value = "邀请内容", required = true)
	private String inviteContent;
	
	@ApiModelProperty(value = "门店简介", required = true)
	private String merchantStoreIntro;

	@ApiModelProperty(value = "类型:ALL--全选，CANCEL_SOME--取消了几个，CHOOSE_SOME--只选了几个", required = true)
	private InviteTypeEnum inviteTypeEnum;
	
	@ApiModelProperty(value = "邀请人数", required = false)
	private Integer inviteCount;
	
	@ApiModelProperty(value = "地区", required = false)
	private String regionPath;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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


	public InviteTypeEnum getInviteTypeEnum() {
		return inviteTypeEnum;
	}

	public void setInviteTypeEnum(InviteTypeEnum inviteTypeEnum) {
		this.inviteTypeEnum = inviteTypeEnum;
	}

	public Integer getInviteCount() {
		return inviteCount;
	}

	public void setInviteCount(Integer inviteCount) {
		this.inviteCount = inviteCount;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}
	
	
}
