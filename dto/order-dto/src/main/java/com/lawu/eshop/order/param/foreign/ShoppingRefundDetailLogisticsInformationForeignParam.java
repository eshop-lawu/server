package com.lawu.eshop.order.param.foreign;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商家填写物流信息
 * api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingRefundDetailLogisticsInformationForeignParam {

	/**
	 * 物流编号
	 */
	@NotBlank(message="物流编号不能为空")
	@ApiModelProperty(required = true, value = "物流编号")
	private String waybillNum;

	/**
	 * 快递公司id
	 */
	@NotNull(message="快递公司id不能为空")
	@ApiModelProperty(required = true, value = "快递公司id")
	private Integer expressCompanyId;

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public Integer getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Integer expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}
}
