package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/4/18.
 */
public class ProductCategoryListDTO {

    @ApiModelProperty(value = "分类ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
