package com.lawu.eshop.ad.dto;

import com.lawu.eshop.ad.constants.PositionEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/6/16.
 */
public class AdPlatformProductDTO {

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "广告附件地址")
    private String mediaUrl;
    
    @ApiModelProperty(value = "链接")
    private String linkUrl;
    
    private PositionEnum postion;

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

	public PositionEnum getPostion() {
		return postion;
	}

	public void setPostion(PositionEnum postion) {
		this.postion = postion;
	}
    
	
}
