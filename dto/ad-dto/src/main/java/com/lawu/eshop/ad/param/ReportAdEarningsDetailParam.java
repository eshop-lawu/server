package com.lawu.eshop.ad.param;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class ReportAdEarningsDetailParam extends AbstractPageParam{
	
	@ApiParam (name="adId",required = true, value = "广告id")
	private Long adId;
	
	@ApiParam (name="adTypeEnum",required = true, value = "广告类型")
	private AdTypeEnum adTypeEnum;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public AdTypeEnum getAdTypeEnum() {
		return adTypeEnum;
	}

	public void setAdTypeEnum(AdTypeEnum adTypeEnum) {
		this.adTypeEnum = adTypeEnum;
	}

	
	
	
	
	
}
