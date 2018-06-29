package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductBrandListDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "商品分类ID")
    private Integer productCategoryId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
