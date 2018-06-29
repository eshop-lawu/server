package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;

/**
 * 平台总销量BO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class TotalSalesBO {
   
    /**
    * 金额
    */
    private BigDecimal amount;
    
    /**
    * 交易类型
    *
    */
    private MerchantTransactionTypeEnum transactionType;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MerchantTransactionTypeEnum getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(MerchantTransactionTypeEnum transactionType) {
		this.transactionType = transactionType;
	}
	
}