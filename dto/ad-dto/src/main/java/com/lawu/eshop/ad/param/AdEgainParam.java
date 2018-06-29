package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * 查询E赚参数
 */
@ApiModel
public class AdEgainParam extends AbstractPageParam {
	
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
	
	
	/**
	 * 广告类型
	 */
	@ApiParam (value = "广告类型(AD_TYPE_FLAT-平面|AD_TYPE_VIDEO-视频)", required = true)
	private AdEgainTypeEnum typeEnum;

	public AdEgainTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdEgainTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

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
