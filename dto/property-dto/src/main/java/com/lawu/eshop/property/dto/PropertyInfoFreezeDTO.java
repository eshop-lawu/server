package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;

import io.swagger.annotations.ApiModelProperty;

public class PropertyInfoFreezeDTO {

	/**
	 * 用户是否冻结(NO-否|YES-是)
	 */
	@ApiModelProperty(value = "用户是否冻结(NO-否|YES-是)", required = true)
	private PropertyinfoFreezeEnum status;
	
	@ApiModelProperty(value = "提示语")
	private String message;

	public PropertyinfoFreezeEnum getStatus() {
		return status;
	}

	public void setStatus(PropertyinfoFreezeEnum status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
