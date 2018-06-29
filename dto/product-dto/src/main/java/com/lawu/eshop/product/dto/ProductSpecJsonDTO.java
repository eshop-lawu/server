package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 */
public class ProductSpecJsonDTO {
    @ApiModelProperty(value = "规格ID")
    private Long specId;

    @ApiModelProperty(value = "规格名称")
    private String specName;

    @ApiModelProperty(value = "规格选项列表")
    private List<ProductSpecOptionJsonDTO> options;

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

    public List<ProductSpecOptionJsonDTO> getOptions() {
        return options;
    }

    public void setOptions(List<ProductSpecOptionJsonDTO> options) {
        this.options = options;
    }
}
