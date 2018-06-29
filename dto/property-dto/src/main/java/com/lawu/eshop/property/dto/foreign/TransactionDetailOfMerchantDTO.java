package com.lawu.eshop.property.dto.foreign;

import com.lawu.eshop.property.constants.MerchantTransactionCategoryEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细DTO
 * Of Merchant
 * @author jiangxinjun
 * @date 2017年10月20日
 */
public class TransactionDetailOfMerchantDTO extends TransactionDetailForeignDTO {
    
	/**
	 * 交易类型
	 */
	@ApiModelProperty(value = "交易类型")
	private MerchantTransactionCategoryEnum transactionCategory;

    public MerchantTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MerchantTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
	
}