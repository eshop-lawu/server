package com.lawu.eshop.user.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class FavoriteMerchantDTO {
	
	@ApiModelProperty(value = "商家id")
	private Long merchantId;

	@ApiModelProperty(value = "门店名称")
	private String name;

	@ApiModelProperty(value = "主营业名称")
	private String industryName;

	@ApiModelProperty(value = "好评率")
	private BigDecimal feedbackRate;

	@ApiModelProperty(value = "门店图路径")
	private String path;
	
	@ApiModelProperty(value = "粉丝总数量")
	private Integer fansCount;
	
	@ApiModelProperty(value = "在售商品总数量")
	private Integer saleCount;
	
	@ApiModelProperty(value = "距离")
	private int distance;
	
	@ApiModelProperty(value = "门店id")
	private Long merchantStoreId;
	

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}
	
	

}
