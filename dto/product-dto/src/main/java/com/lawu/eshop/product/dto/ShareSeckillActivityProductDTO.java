package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/5/17.
 */
public class ShareSeckillActivityProductDTO {

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "剩余数量")
    private Integer leftCount;

    @ApiModelProperty(value = "活动状态：NOT_STARTED--未开始，PROCESSING--进行中，END--已结束")
    private ActivityStatusEnum statusEnum;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public ActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(ActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

}
