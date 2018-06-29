package com.lawu.eshop.ad.param;

import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class PointGetDetailParam extends AbstractPageParam {

	@ApiParam (name="id",required=true, value = "广告id")
	private Long id;
	
	@ApiParam (name="typeEnum",required=true, value = "广告类型")
	private AdTypeEnum typeEnum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AdTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(AdTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	
}
