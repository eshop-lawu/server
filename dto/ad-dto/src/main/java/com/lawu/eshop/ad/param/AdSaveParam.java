package com.lawu.eshop.ad.param;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.ClientTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;

public class AdSaveParam {

	private AdParam adParam;

	/**
	 * 商家经度
	 */
	private BigDecimal longitude;

	/**
	 * 商家纬度
	 */
	private BigDecimal latitude;

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

	private String videoImgUrl;

	/**
	 * 商家门店id
	 */
	private Long merchantStoreId;

	/**
	 * 店铺名称
	 */
	private String merchantStoreName;

	/**
	 * 经营类型
	 */
	private ManageTypeEnum manageType;

	/**
	 * 门店logo
	 */
	private String logoUrl;
	
	/**
	 * 商家地区
	 */
	private String merchantRegionPath;
	
	private  ClientTypeEnum clentType;
	
	

	public ClientTypeEnum getClentType() {
		return clentType;
	}

	public void setClentType(ClientTypeEnum clentType) {
		this.clentType = clentType;
	}

	public AdParam getAdParam() {
		return adParam;
	}

	public void setAdParam(AdParam adParam) {
		this.adParam = adParam;
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

	public String getVideoImgUrl() {
		return videoImgUrl;
	}

	public void setVideoImgUrl(String videoImgUrl) {
		this.videoImgUrl = videoImgUrl;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getMerchantStoreName() {
		return merchantStoreName;
	}

	public void setMerchantStoreName(String merchantStoreName) {
		this.merchantStoreName = merchantStoreName;
	}

	public ManageTypeEnum getManageType() {
		return manageType;
	}

	public void setManageType(ManageTypeEnum manageType) {
		this.manageType = manageType;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getMerchantRegionPath() {
		return merchantRegionPath;
	}

	public void setMerchantRegionPath(String merchantRegionPath) {
		this.merchantRegionPath = merchantRegionPath;
	}
	
	

}
