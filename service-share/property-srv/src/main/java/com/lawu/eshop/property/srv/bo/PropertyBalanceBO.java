package com.lawu.eshop.property.srv.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Sunny
 * @date 2017/3/31
 */
public class PropertyBalanceBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 余额
     */
    private BigDecimal balance;

    private BigDecimal freeze;

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
}
