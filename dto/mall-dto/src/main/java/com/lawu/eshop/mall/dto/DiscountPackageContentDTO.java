package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠套餐内容DTO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
@ApiModel
public class DiscountPackageContentDTO {
	
    /**
     * 主键
     */
	@ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 内容名称
     */
	@ApiModelProperty(value = "内容名称", required = true)
    private String name;

    /**
     * 单价
     */
	@ApiModelProperty(value = "单价", required = true)
    private BigDecimal unitPrice;

    /**
     * 数量
     */
	@ApiModelProperty(value = "数量", required = true)
    private Integer quantity;

    /**
     * 单位
     */
	@ApiModelProperty(value = "单位", required = true)
    private String unit;

    /**
     * 小计
     */
	@ApiModelProperty(value = "小计", required = true)
    private BigDecimal subtotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

}