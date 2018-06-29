package com.lawu.eshop.ad.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * 查询精选推荐广告
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public class AdChoicenessParam extends AbstractPageParam {
	
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
