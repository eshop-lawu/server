package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class BroadcastDTO {

    @ApiModelProperty(value = "余额收益，当日无收益时为null")
    private BigDecimal balanceIncome;

    @ApiModelProperty(value = "积分收益，当日无收益时为null")
    private BigDecimal pointIncome;

    public BigDecimal getBalanceIncome() {
        return balanceIncome;
    }

    public void setBalanceIncome(BigDecimal balanceIncome) {
        this.balanceIncome = balanceIncome;
    }

    public BigDecimal getPointIncome() {
        return pointIncome;
    }

    public void setPointIncome(BigDecimal pointIncome) {
        this.pointIncome = pointIncome;
    }
}
