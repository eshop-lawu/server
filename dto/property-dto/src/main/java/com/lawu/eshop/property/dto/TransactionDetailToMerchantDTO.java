package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细DTO
 * To Marchant
 * 
 * @author Sunny
 * @date 2017/3/29
 */
public class TransactionDetailToMerchantDTO extends TransactionDetailDTO {
	
	/**
	 * 交易类型
	 */
	@ApiModelProperty(value = "交易类型")
	private MerchantTransactionTypeEnum transactionType;

	public MerchantTransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(MerchantTransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	
}