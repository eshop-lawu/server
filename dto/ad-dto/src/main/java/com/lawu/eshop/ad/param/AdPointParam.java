package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class AdPointParam extends AbstractPageParam{

	@ApiParam (name="longitude", value = "经度")
	private Double longitude;
	
	@ApiParam (name="latitude", value = "纬度")
	private Double latitude;

	@ApiParam (name="orderTypeEnum",required = true, value = "AD_TORLEPOINT_DESC 总积分倒序  AD_POINT_DESC 单个积分倒序")
	private OrderTypeEnum orderTypeEnum;

	@ApiParam(value="定位区域")
	private String transRegionPath;

	
	public String getTransRegionPath() {
		return transRegionPath;
	}

	public void setTransRegionPath(String transRegionPath) {
		this.transRegionPath = transRegionPath;
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


	public OrderTypeEnum getOrderTypeEnum() {
		return orderTypeEnum;
	}


	public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
		this.orderTypeEnum = orderTypeEnum;
	}
    
    
    

}
