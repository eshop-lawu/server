package com.lawu.eshop.mall.dto;

/**
 * @author zhangyong
 * @date 2017/4/17.
 */
public class CommentProductIdDTO {

    private Long productId;

    private Long productModelId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }
}
