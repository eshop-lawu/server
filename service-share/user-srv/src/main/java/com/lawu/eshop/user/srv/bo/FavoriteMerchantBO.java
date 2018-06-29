package com.lawu.eshop.user.srv.bo;

import java.math.BigDecimal;

/**
 * 我收藏的商家实体
 * 
 * @author zhangrc
 * @date 2017/03/27
 */
public class FavoriteMerchantBO {

	private Long merchantId;

	private String name;

	private String industryName;

	private BigDecimal feedbackRate;

	private String path;
	
	private Integer fansCount;
	
	private int distance;
	
	private Long merchantStoreId;
	

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public BigDecimal getFeedbackRate() {
		return feedbackRate;
	}

	public void setFeedbackRate(BigDecimal feedbackRate) {
		this.feedbackRate = feedbackRate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	
	
	
	
}
