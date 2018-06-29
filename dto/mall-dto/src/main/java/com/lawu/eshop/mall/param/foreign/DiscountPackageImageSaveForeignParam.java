package com.lawu.eshop.mall.param.foreign;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class DiscountPackageImageSaveForeignParam {
	
	/**
	 * 图片文字描述
	 */
	@NotBlank(message = "图片文字描述不能为空")
	@ApiModelProperty(value = "图片文字描述", required = true)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
