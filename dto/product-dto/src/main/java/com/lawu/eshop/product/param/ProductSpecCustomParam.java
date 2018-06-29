package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商家自定义规格
 */
public class ProductSpecCustomParam {

    @ApiModelProperty(value = "规格ID，从-1开始递减")
    private Long specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
