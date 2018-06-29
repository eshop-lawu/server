package com.lawu.eshop.ad.param;

import com.lawu.eshop.ad.constants.FavoriteAdTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

public class FavoriteAdParam extends AbstractPageParam{
	
	@ApiParam (name="typeEnum", required=true, value = "AD_TYPE_EGAIN 广告 AD_TYPE_EPRAISE E赞")
	private FavoriteAdTypeEnum typeEnum;

	public FavoriteAdTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(FavoriteAdTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	

}
