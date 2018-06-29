package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdPlatformDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "商品id")
	private Long productId;

	@ApiModelProperty(value = "广告标题")
	private String title;

	@ApiModelProperty(value = "广告附件地址")
	private String mediaUrl;

	@ApiModelProperty(value = "链接地址")
	private String linkUrl;
	
	@ApiModelProperty(value = "广告内容")
	private String content;

	@ApiModelProperty(value = "门店ID")
	private Long merchantStoreId;
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	
	
	
}
