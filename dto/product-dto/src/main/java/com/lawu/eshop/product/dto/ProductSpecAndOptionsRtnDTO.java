package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *  根据商品类目ID查询商品规格和规格选项
 */
public class ProductSpecAndOptionsRtnDTO {

    @ApiModelProperty(value = "商品规格和规格选项信息")
    private List<ProductSpecJsonDTO> productSpecRtnDTOList;

    public List<ProductSpecJsonDTO> getProductSpecRtnDTOList() {
        return productSpecRtnDTOList;
    }

    public void setProductSpecRtnDTOList(List<ProductSpecJsonDTO> productSpecRtnDTOList) {
        this.productSpecRtnDTOList = productSpecRtnDTOList;
    }
}
