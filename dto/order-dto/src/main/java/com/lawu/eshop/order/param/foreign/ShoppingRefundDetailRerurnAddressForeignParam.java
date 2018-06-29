package com.lawu.eshop.order.param.foreign;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商家填写物流信息
 * api接收参数
 * 
 * @author Sunny
 * @date 2017/4/12
 */
@ApiModel
public class ShoppingRefundDetailRerurnAddressForeignParam {

	/**
	 * 地址id
	 */
	@NotNull(message = "地址id不能为空")
	@ApiModelProperty(value = "地址id", required = false)
	private Long addressId;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
}
