package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;

/**
 * 生成大额红包BO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
public class ExpectedRedpacketAmountBO {

    /**
     * 预期最小红包总金额
     */
    private BigDecimal expectedMinRedpacketAmount;

    /**
     * 预期最大红包总金额
     */
    private BigDecimal expectedMaxRedpacketAmount;
    
    public BigDecimal getExpectedMinRedpacketAmount() {
        return expectedMinRedpacketAmount;
    }

    public void setExpectedMinRedpacketAmount(BigDecimal expectedMinRedpacketAmount) {
        this.expectedMinRedpacketAmount = expectedMinRedpacketAmount;
    }

    public BigDecimal getExpectedMaxRedpacketAmount() {
        return expectedMaxRedpacketAmount;
    }

    public void setExpectedMaxRedpacketAmount(BigDecimal expectedMaxRedpacketAmount) {
        this.expectedMaxRedpacketAmount = expectedMaxRedpacketAmount;
    }

}
