package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/17.
 */
public class CommentProductInfoDTO {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品图片")
    private String imgUrl;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
