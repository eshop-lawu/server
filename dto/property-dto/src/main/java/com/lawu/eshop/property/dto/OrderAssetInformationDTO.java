package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

/**
 * 订单所需要的资产信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月10日
 * @updateDate 2018年3月10日
 */
public class OrderAssetInformationDTO {

    /**
     * 积分
     */
    private BigDecimal point;

    /**
     * 余额
     */
    private BigDecimal balance;
    
    /**
     * 积分兑换比例
     */
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

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
