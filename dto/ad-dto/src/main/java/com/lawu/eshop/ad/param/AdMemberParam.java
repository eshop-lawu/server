package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class AdMemberParam extends AbstractPageParam{
	
	@ApiParam (name="typeEnum", value = "AD_TYPE_FLAT 平面 AD_TYPE_VIDEO 视频 AD_TYPE_PRAISE E赞 AD_TYPE_PACKET 红包")
	private AdTypeEnum typeEnum;

	@ApiParam (name="longitude", value = "经度")
	private Double longitude;
	
	@ApiParam (name="latitude", value = "纬度")
	private Double latitude;

	@ApiParam (name="orderTypeEnum", value = "AD_TORLEPOINT_DESC 总积分倒序  AD_POINT_DESC 单个积分倒序")
	private OrderTypeEnum orderTypeEnum;
	
	/**
	 * 定位区域
	 */
	@ApiParam(value="定位区域")
	private String transRegionPath;

	public String getTransRegionPath() {
		return transRegionPath;
	}


	public void setTransRegionPath(String transRegionPath) {
		this.transRegionPath = transRegionPath;
	}


	public AdTypeEnum getTypeEnum() {
		return typeEnum;
	}


	public void setTypeEnum(AdTypeEnum typeEnum) {
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


	public OrderTypeEnum getOrderTypeEnum() {
		return orderTypeEnum;
	}


	public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
		this.orderTypeEnum = orderTypeEnum;
	}
    
    
    

}
