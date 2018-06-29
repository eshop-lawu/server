package com.lawu.eshop.product.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
public class ListShoppingProductParam extends AbstractPageParam {

    @ApiModelProperty(value = "商家ID", required = true)
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
