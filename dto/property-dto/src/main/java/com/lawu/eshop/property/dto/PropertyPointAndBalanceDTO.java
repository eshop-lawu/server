package com.lawu.eshop.property.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sunny
 * @date 2017/3/31
 */
public class PropertyPointAndBalanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 积分
     */
    @ApiModelProperty(name = "point", value = "积分", required = true)
    private BigDecimal point;

    @ApiModelProperty(name = "balance", value = "余额", required = true)
    private BigDecimal balance;

    @ApiModelProperty(name = "freeze", value = "冻结资金", required = true)
    private BigDecimal freeze;

    @ApiModelProperty(name = "exchangeRate", value = "积分兑换余额比例", required = true)
    private BigDecimal exchangeRate;

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
