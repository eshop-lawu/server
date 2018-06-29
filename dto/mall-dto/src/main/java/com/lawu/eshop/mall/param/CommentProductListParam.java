package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
public class CommentProductListParam extends AbstractPageParam {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
