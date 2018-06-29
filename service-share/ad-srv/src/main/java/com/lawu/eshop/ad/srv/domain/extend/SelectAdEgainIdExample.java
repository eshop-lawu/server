package com.lawu.eshop.ad.srv.domain.extend;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.utils.DistanceUtil;

/**
 * 查询所有可见广告条件
 * 
 * @author jiangxinjun
 * @date 2017年7月17日
 */
public class SelectAdEgainIdExample {
	
	/**
	 * 广告类型
	 */
	private Byte type;
	
	/**
	 * 经度
	 */
	private BigDecimal longitude;

	/**
	 * 纬度
	 */
	private BigDecimal latitude;
	
	/**
	 * 地球半径
	 */
	private static final Double radius = ((double) DistanceUtil.RADIUS / 1000);
	
	/**
	 * 用户所属的粉丝商家Id
	 */
	private List<Long> merchantIds;
	
	/**
	 * 用户所在区域(拆分为省市县区)
	 */
	private List<String> areas;

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
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

	public Double getRadius() {
		return radius;
	}

	public List<Long> getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(List<Long> merchantIds) {
		this.merchantIds = merchantIds;
	}

	public List<String> getAreas() {
		return areas;
	}

	public void setAreas(List<String> areas) {
		this.areas = areas;
	}
}
