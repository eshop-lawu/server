package com.lawu.eshop.user.dto;

/**
 * @author zhangyong
 * @date 2017/8/4.
 */
public class MerchantStoreProfileDTO {

    private MerchantStoreTypeEnum typeEnum;

    private Long merchantStoreId;

    public MerchantStoreTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantStoreTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }
}
