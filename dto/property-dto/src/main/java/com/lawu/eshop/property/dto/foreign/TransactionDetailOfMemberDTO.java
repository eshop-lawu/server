package com.lawu.eshop.property.dto.foreign;

import com.lawu.eshop.property.constants.MemberTransactionCategoryEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细DTO
 * Of Member
 * @author jiangxinjun
 * @date 2017年10月20日
 */
public class TransactionDetailOfMemberDTO extends TransactionDetailForeignDTO {
    
	/**
	 * 交易类型
	 */
	@ApiModelProperty(value = "交易类型")
	private MemberTransactionCategoryEnum transactionCategory;

    public MemberTransactionCategoryEnum getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(MemberTransactionCategoryEnum transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
	
}