package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/17.
 */
public class SeckillActivityProductRecommendDTO {

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品图片")
    private String productPicture;

    @ApiModelProperty(value = "原价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "抢购价")
    private BigDecimal saleMoney;

    @ApiModelProperty(value = "活动状态：NOT_STARTED--即将开始，PROCESSING--进行中，END--已结束")
    private ActivityStatusEnum statusEnum;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public ActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(ActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
