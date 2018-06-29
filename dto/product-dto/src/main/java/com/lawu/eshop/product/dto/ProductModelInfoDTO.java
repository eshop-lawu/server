package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class ProductModelInfoDTO {

    @ApiModelProperty(value = "型号ID")
    private Long id;

    @ApiModelProperty(value = "型号组合key")
    private String modelKey;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
