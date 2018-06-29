package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

/**
 * 月结账单封装DO
 * 
 * @author jiangxinjun
 * @date 2017年10月20日
 */
public class MonthlyBillDO {
   
    /**
    * 金额
    */
    private BigDecimal amount;
    
    /**
    * 交易类型
    */
    private Byte direction;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    public Byte getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction;
    }
	
}