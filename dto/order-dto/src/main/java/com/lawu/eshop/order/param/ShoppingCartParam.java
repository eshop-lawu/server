package com.lawu.eshop.order.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingCartParam {

    /**
     * 商品型号ID
     */
    @NotNull(message = "产品型号id不能为空")
    @ApiModelProperty(name = "productModelId", required = true, value = "商品型号ID")
    private Long productModelId;

    /**
     * 抢购活动商品型号id
     */
    @ApiModelProperty(name = "activityProductModelId", value = "抢购活动商品型号id")
    private Long activityProductModelId;

    /**
     * 数量
     */
    @Min(value = 1, message = "数量不能小于1")
    @ApiModelProperty(name = "quantity", required = true, value = "数量")
    private Integer quantity;

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }

    public Long getActivityProductModelId() {
        return activityProductModelId;
    }

    public void setActivityProductModelId(Long activityProductModelId) {
        this.activityProductModelId = activityProductModelId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}