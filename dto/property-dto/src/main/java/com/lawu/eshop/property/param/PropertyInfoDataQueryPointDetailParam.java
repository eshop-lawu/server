package com.lawu.eshop.property.param;

import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

/**
 *
 */
public class PropertyInfoDataQueryPointDetailParam {

    private String userNum;

    private MerchantTransactionTypeEnum merchantTransactionTypeEnum;

    private String bizId;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public MerchantTransactionTypeEnum getMerchantTransactionTypeEnum() {
        return merchantTransactionTypeEnum;
    }

    public void setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum merchantTransactionTypeEnum) {
        this.merchantTransactionTypeEnum = merchantTransactionTypeEnum;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}