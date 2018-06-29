package com.lawu.eshop.ad.srv.domain.extend;

/**
 * 我收藏的广告实体扩展
 * @author zhangrc
 * @date 2017/4/8
 *
 */
public class FavoriteAdDOView {
	
	/**
	 * 广告id
	 */
	private Long id;
	
	/**
	 * 广告id
	 */
	private Long AdId;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
	
	/**
	 * 广告标题
	 */
	private String title;
	
	/**
	 * 广告附件图
	 */
	private String mediaUrl;
	
	/**
	 * 广告内容
	 */
	private String content;
	
	
	/**
	 * 广告类型
	 */
	private byte type;
	
	
	private Long memberId;
	
	private byte status;
	
	private String videoImgUrl;
	
	private int count;
	
	private byte praiseType;
	

	public byte getPraiseType() {
		return praiseType;
	}

	public void setPraiseType(byte praiseType) {
		this.praiseType = praiseType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return AdId;
	}

	public void setAdId(Long adId) {
		AdId = adId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}
	
	
	

}
