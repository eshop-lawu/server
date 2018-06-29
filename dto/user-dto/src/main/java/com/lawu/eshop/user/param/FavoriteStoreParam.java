package com.lawu.eshop.user.param;

import com.lawu.eshop.common.constants.ManageTypeEnum;

import io.swagger.annotations.ApiParam;

public class FavoriteStoreParam {
	
	@ApiParam (name="manageTypeEnum",required = true, value = "ENTITY 实体 COMMON 普通")
	private ManageTypeEnum manageTypeEnum;
	
	@ApiParam (name="merchantId",required = true, value = "商家id")
	private Long merchantId;

	public ManageTypeEnum getManageTypeEnum() {
		return manageTypeEnum;
	}

	public void setManageTypeEnum(ManageTypeEnum manageTypeEnum) {
		this.manageTypeEnum = manageTypeEnum;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	
	

}
