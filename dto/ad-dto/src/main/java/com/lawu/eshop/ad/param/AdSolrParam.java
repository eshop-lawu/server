package com.lawu.eshop.ad.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * @author zhangrc
 * @date 2017/4/13.
 */
public class AdSolrParam extends AbstractPageParam {


    @ApiModelProperty(value = "广告标题")
    private String title;
    
    @ApiParam (name="longitude", value = "经度")
	private Double longitude;
	
	@ApiParam (name="latitude", value = "纬度")
	private Double latitude;

	@ApiParam(value="定位区域")
	private String transRegionPath;
	
	
	public String getTransRegionPath() {
		return transRegionPath;
	}

	public void setTransRegionPath(String transRegionPath) {
		this.transRegionPath = transRegionPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	
    
}
