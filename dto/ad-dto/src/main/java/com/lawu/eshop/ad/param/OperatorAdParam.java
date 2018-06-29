package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class OperatorAdParam extends AbstractPageParam{
	
	@ApiParam(name = "title", value = "广告名称")
    private String title;
	
	private AdEgainTypeEnum adEgainType ;

	public AdEgainTypeEnum getAdEgainType() {
		return adEgainType;
	}

	public void setAdEgainType(AdEgainTypeEnum adEgainType) {
		this.adEgainType = adEgainType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
