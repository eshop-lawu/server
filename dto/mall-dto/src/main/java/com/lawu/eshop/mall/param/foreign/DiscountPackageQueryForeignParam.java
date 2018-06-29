package com.lawu.eshop.mall.param.foreign;

import javax.validation.constraints.NotNull;

import com.lawu.eshop.mall.constants.DiscountPackageStatusQueryEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DiscountPackageQueryForeignParam {

	/**
	 * 状态
	 */
	@NotNull(message = "状态不能为空")
	@ApiModelProperty(value = "状态", required = true)
	private DiscountPackageStatusQueryEnum status;

	public DiscountPackageStatusQueryEnum getStatus() {
		return status;
	}

	public void setStatus(DiscountPackageStatusQueryEnum status) {
		this.status = status;
	}
	
}
