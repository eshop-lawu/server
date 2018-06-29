package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdPlatformFlatDTO {

	@ApiModelProperty(value = "广告id")
	private Long adId;
	
	@ApiModelProperty(value = "图片路径")
	private String mediaUrl;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	
	
}
