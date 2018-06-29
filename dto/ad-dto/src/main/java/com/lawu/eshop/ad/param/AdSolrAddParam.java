package com.lawu.eshop.ad.param;

import java.math.BigDecimal;
import java.util.List;

public class AdSolrAddParam extends AdParam{

	/**
	 * 商家经度
	 */
	private BigDecimal longitude;

	/**
	 * 商家纬度
	 */
	private BigDecimal latitude;

	/**
	 * 商家粉丝集合
	 */
	private List<Long> merchantIds;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
	
	/**
	 * 附件路径
	 */
	private String mediaUrl;
	
	/**
	 * 人数
	 */
	private Integer count;
	
	/**
	 * 商家编号
	 */
	private String userNum;

	

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	

	public List<Long> getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(List<Long> merchantIds) {
		this.merchantIds = merchantIds;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	
}
