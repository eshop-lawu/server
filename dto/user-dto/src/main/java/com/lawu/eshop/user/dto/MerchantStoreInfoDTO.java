package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/5/16.
 */
public class MerchantStoreInfoDTO {

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "店铺ID")
    private Long storeId;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
