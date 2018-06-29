package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppMerchantFunctionManageDTO {
	
	
	@ApiModelProperty(value = "是否开启商家瑞奇岛  true 启用  false 禁用")
	private Boolean isEnableMerchantRich;

	public Boolean getIsEnableMerchantRich() {
		return isEnableMerchantRich;
	}


	public void setIsEnableMerchantRich(Boolean isEnableMerchantRich) {
		this.isEnableMerchantRich = isEnableMerchantRich;
	}
	 
	
	 
	 

}
