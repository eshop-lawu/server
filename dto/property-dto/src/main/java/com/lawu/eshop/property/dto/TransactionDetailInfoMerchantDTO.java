package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.MerchantTransactionCategoryEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class TransactionDetailInfoMerchantDTO extends TransactionDetailBaseInfoDTO {

    @ApiModelProperty(value = "交易分类")
    private MerchantTransactionCategoryEnum transactionCategory;

    @ApiModelProperty(value = "交易类型")
    private MerchantTransactionTypeEnum merchantTransactionTypeEnum;

    public MerchantTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MerchantTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public MerchantTransactionTypeEnum getMerchantTransactionTypeEnum() {
        return merchantTransactionTypeEnum;
    }

    public void setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum merchantTransactionTypeEnum) {
        this.merchantTransactionTypeEnum = merchantTransactionTypeEnum;
    }
}
