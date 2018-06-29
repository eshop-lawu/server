package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentProductPageParam extends AbstractPageParam {

    @ApiModelProperty(value = "商品ID",required = true)
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
