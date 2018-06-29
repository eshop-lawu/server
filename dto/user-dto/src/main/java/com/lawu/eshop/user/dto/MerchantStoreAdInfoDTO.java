package com.lawu.eshop.user.dto;

import java.math.BigDecimal;

import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;

public class MerchantStoreAdInfoDTO {
	
	private Long merchantStoreId;
	
	private String name;
	
	private String logoUrl;
	
	private MerchantStoreTypeEnum manageType;
	
	private BigDecimal longitude;

	private BigDecimal latitude;
	
	private String regionPath;
	

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


	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public MerchantStoreTypeEnum getManageType() {
		return manageType;
	}

	public void setManageType(MerchantStoreTypeEnum manageType) {
		this.manageType = manageType;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}
	
	
	

}
