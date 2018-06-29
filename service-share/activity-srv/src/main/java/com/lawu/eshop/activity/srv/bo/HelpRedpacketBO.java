package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;

public class HelpRedpacketBO {
	
    private Integer activityId;
    
	private BigDecimal originalMoney;
	
	private BigDecimal money;
	
	private Double multiple;

	public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Double getMultiple() {
		return multiple;
	}

	public void setMultiple(Double multiple) {
		this.multiple = multiple;
	}

	public BigDecimal getOriginalMoney() {
		return originalMoney;
	}

	public void setOriginalMoney(BigDecimal originalMoney) {
		this.originalMoney = originalMoney;
	}
	
	

}
