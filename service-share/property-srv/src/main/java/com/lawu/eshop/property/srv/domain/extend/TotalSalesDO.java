package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 平台总销量封装DO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class TotalSalesDO implements Serializable {
   
	private static final long serialVersionUID = 1L;

    /**
    * 金额
    */
    private BigDecimal amount;
    
    /**
    * 交易类型
    *
    */
    private Byte transactionType;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Byte getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Byte transactionType) {
		this.transactionType = transactionType;
	}
    
}