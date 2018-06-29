package com.lawu.eshop.ad.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;


@ApiModel
public class AdPlatformFindEginParam {
	
	/**
	 * 经度
	 */
	@ApiParam (value = "经度")
	private Double longitude;
	
	/**
	 * 纬度
	 */
	@ApiParam (value = "纬度")
	private Double latitude;
	
	/**
	 * 定位区域
	 */
	@ApiParam(value="定位区域")
	private String transRegionPath;
	

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getTransRegionPath() {
		return transRegionPath;
	}

	public void setTransRegionPath(String transRegionPath) {
		this.transRegionPath = transRegionPath;
	}
	
	

}
