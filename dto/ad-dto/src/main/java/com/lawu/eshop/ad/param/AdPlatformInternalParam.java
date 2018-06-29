package com.lawu.eshop.ad.param;

import java.util.List;

import com.lawu.eshop.ad.constants.AdPlatformFlatTypeEnum;

/**
 * 查询广告位内部调用参数
 * 
 * @author zhangrc
 * @date 2017年8月7日
 */
public class AdPlatformInternalParam extends AdPlatformFindEginParam {
	
	/**
	 * 用户所属的粉丝商家Id
	 */
	private List<Long> merchantIds;
	
	/**
	 * 用户所在区域(拆分为省市县区)
	 */
	private List<String> areas;
	
	/**
	 * 广告位置
	 */
	private AdPlatformFlatTypeEnum adPlatformFlatTypeEnum;

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

	public AdPlatformFlatTypeEnum getAdPlatformFlatTypeEnum() {
		return adPlatformFlatTypeEnum;
	}

	public void setAdPlatformFlatTypeEnum(AdPlatformFlatTypeEnum adPlatformFlatTypeEnum) {
		this.adPlatformFlatTypeEnum = adPlatformFlatTypeEnum;
	}
	
	
	
}
