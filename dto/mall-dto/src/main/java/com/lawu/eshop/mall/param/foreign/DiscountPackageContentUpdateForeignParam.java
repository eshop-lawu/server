package com.lawu.eshop.mall.param.foreign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DiscountPackageContentUpdateForeignParam extends DiscountPackageContentSaveForeignParam {
	
	/**
	 * 套餐内容id
	 */
	@ApiModelProperty(name = "id", value = "套餐内容id", required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
