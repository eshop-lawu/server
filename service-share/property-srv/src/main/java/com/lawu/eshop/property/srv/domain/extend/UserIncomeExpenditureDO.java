package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户支出收入DO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class UserIncomeExpenditureDO implements Serializable {
   
	private static final long serialVersionUID = 1L;

    /**
    * 金额
    */
    private BigDecimal amount;
    
    /**
    * 用户编号
    */
    private String userNum;
    
    /**
     * 消费类型
     */
    private Byte direction;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getDirection() {
		return direction;
	}

	public void setDirection(Byte direction) {
		this.direction = direction;
	}
	
}