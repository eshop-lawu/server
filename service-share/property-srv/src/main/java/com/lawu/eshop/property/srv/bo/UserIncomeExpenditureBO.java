package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.ConsumptionTypeEnum;

/**
 * 用户支出收入BO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class UserIncomeExpenditureBO {
   
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
    private ConsumptionTypeEnum direction;

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

	public ConsumptionTypeEnum getDirection() {
		return direction;
	}

	public void setDirection(ConsumptionTypeEnum direction) {
		this.direction = direction;
	}
	
}