package com.lawu.eshop.ad.param;

import java.util.List;

/**
 * 查询精品推荐广告内部调用参数
 * 
 * @author jiangxinjun
 * @date 2017年7月18日
 */
public class AdChoicenessInternalParam extends AdChoicenessParam {
	
	/**
	 * 用户所属的粉丝商家Id
	 */
	private List<Long> merchantIds;
	
	/**
	 * 用户所在区域(拆分为省市县区)
	 */
	private List<String> areas;

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
