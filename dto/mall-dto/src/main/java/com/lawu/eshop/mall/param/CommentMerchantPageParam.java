package com.lawu.eshop.mall.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentMerchantPageParam extends AbstractPageParam {

    /**
     * 商家ID
     */
    private Long merchantId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
