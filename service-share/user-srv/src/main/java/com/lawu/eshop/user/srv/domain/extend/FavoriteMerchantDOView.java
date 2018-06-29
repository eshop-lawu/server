package com.lawu.eshop.user.srv.domain.extend;

import java.math.BigDecimal;
import java.util.Date;

public class FavoriteMerchantDOView {

	private Long memberId;

	private Long merchantId;

	private byte type;

	private String name;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private String industryName;

	private BigDecimal feedbackRate;

	private String path;
	
	private Long merchantStoreId;
	
	private Integer countFs; 
	
	private Date gmtCreate;
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public Integer getCountFs() {
		return countFs;
	}

	public void setCountFs(Integer countFs) {
		this.countFs = countFs;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
