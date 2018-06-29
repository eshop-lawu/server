package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.OrderTypeEnum;

import io.swagger.annotations.ApiParam;

/**
 * 排行版查询参数
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public class AdPointForeignParam {

	/**
	 * 经度
	 */
	@ApiParam(value = "经度")
	private Double longitude;

	/**
	 * 纬度
	 */
	@ApiParam(value = "纬度")
	private Double latitude;

	/**
	 * 排序类型
	 */
	@ApiParam(value = "排序类型(AD_TORLEPOINT_DESC-总积分倒序 |AD_POINT_DESC-单个积分倒序)", required = true)
	private OrderTypeEnum orderTypeEnum;

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
