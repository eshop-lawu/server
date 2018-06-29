package com.lawu.eshop.mall.param.foreign;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DiscountPackageContentSaveForeignParam {
	
	/**
	 * 内容名称
	 */
	@NotBlank(message = "内容名称不能为空")
	@ApiModelProperty(name = "name", value = "内容名称", required = true)
	private String name;
	
	/**
	 * 单价
	 */
	@Range(min = 0L, message = "单价不能小于0")
	@ApiModelProperty(name = "unitPrice", value = "单价", required = true)
	private BigDecimal unitPrice;

	/**
	 * 数量
	 */
	@Range(min = 0L, message = "数量不能小于0")
	@ApiModelProperty(name = "quantity", value = "数量", required = true)
	private Integer quantity;

	/**
	 * 单位
	 */
	@NotBlank(message = "单位不能为空")
	@ApiModelProperty(name = "unit", value = "单位", required = true)
	private String unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
