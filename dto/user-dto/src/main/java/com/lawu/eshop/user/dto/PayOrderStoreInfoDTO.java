package com.lawu.eshop.user.dto;

/**
 * @author zhangyong
 * @date 2017/6/14.
 */
public class PayOrderStoreInfoDTO {

    private String name;

    private String storeUrl;

    private Long merchantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreUrl() {
        return storeUrl;
    }

    public void setStoreUrl(String storeUrl) {
        this.storeUrl = storeUrl;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
