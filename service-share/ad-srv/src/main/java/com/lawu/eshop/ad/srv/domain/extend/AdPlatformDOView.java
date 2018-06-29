package com.lawu.eshop.ad.srv.domain.extend;

/**
 * @author meishuquan
 * @date 2017/6/16.
 */
public class AdPlatformDOView {

    private Long productId;

    private String title;

    private String mediaUrl;
    
    private String linkUrl ;
    
    public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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
}
