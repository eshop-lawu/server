package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdPlatformVideoDTO {
	
	@ApiModelProperty(value = "主键")
	private Long id ;
	
	@ApiModelProperty(value = "广告id")
	private Long adId;
	
	@ApiModelProperty(value = "广告标题")
	private String title;
	
	@ApiModelProperty(value = "广告内容")
	private String content;
	
	@ApiModelProperty(value = "商家名称")
	private String name ;
	
	@ApiModelProperty(value = "视频封面图片")
	private String videoImgUrl ;
	
	@ApiModelProperty(value = "商家logo")
	private String logoUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	

}
