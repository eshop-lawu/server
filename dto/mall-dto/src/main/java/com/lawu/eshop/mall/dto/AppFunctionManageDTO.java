package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

public class AppFunctionManageDTO {
	
	@ApiModelProperty(value = "是否开启游戏功能 true 启用  false 禁用")
	private Boolean isEnableGame;

	@ApiModelProperty(value = "是否开启爱心账户功能 true 启用  false 禁用")
	private Boolean isEnableLove;

	@ApiModelProperty(value = "是否开启瑞奇岛  true 启用  false 禁用")
	private Boolean isEnableRich;
	
	@ApiModelProperty(value = "是否开启商家瑞奇岛  true 启用  false 禁用")
	private Boolean isEnableMerchantRich;

	public Boolean getIsEnableGame() {
		return isEnableGame;
	}


	public void setIsEnableGame(Boolean isEnableGame) {
		this.isEnableGame = isEnableGame;
	}


	public Boolean getIsEnableLove() {
		return isEnableLove;
	}


	public void setIsEnableLove(Boolean isEnableLove) {
		this.isEnableLove = isEnableLove;
	}


	public Boolean getIsEnableRich() {
		return isEnableRich;
	}


	public void setIsEnableRich(Boolean isEnableRich) {
		this.isEnableRich = isEnableRich;
	}


	public Boolean getIsEnableMerchantRich() {
		return isEnableMerchantRich;
	}


	public void setIsEnableMerchantRich(Boolean isEnableMerchantRich) {
		this.isEnableMerchantRich = isEnableMerchantRich;
	}
	 
	
	 
	 

}
