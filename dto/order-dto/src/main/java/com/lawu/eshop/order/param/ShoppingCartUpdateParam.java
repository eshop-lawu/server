package com.lawu.eshop.order.param;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShoppingCartUpdateParam {
	
    /**
     * 商品型号ID
     */
    @ApiModelProperty(required = false, notes = "商品型号ID")
    private Long productModelId;

    /**
     * 数量
     */
    @Min(value = 1, message = "数量不能小于1")
    @ApiModelProperty(required = true, notes = "数量")
    private Integer quantity;

	public Long getProductModelId() {
		return productModelId;
	}

	public void setProductModelId(Long productModelId) {
		this.productModelId = productModelId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}