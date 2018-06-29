package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentMerchantListParam extends AbstractPageParam {

    @ApiModelProperty(value = "商家ID", required = true)
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
