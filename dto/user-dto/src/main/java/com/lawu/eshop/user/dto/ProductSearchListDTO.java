package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/6/23.
 */
public class ProductSearchListDTO {
    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "特征图片")
    private String featureImage;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "详细描述")
    private String content;

    @ApiModelProperty(value = "原价")
    private Double originalPrice;

    @ApiModelProperty(value = "现价")
    private Double price;

    @ApiModelProperty(value = "销量")
    private Integer salesVolume;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
}
