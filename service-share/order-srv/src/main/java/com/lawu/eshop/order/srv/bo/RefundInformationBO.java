package com.lawu.eshop.order.srv.bo;

import java.math.BigDecimal;

/**
 * 退款所需要展示的信息
 * @author jiangxinjun
 * @createDate 2018年3月22日
 * @updateDate 2018年3月22日
 */
public class RefundInformationBO {
    
    /**
     * 退款金额
     */
    private BigDecimal amount;
    
    /**
     * 退还积分
     */
    private BigDecimal point;

    /**
     * 实际退款金额
     */
    private BigDecimal actualAmount;
    
    /**
     * 运费
     */
    private BigDecimal freightPrice;
    
    /**
     * 运费抵扣积分
     */
    private BigDecimal freightPriceDeductionPoints;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getFreightPriceDeductionPoints() {
        return freightPriceDeductionPoints;
    }

    public void setFreightPriceDeductionPoints(BigDecimal freightPriceDeductionPoints) {
        this.freightPriceDeductionPoints = freightPriceDeductionPoints;
    }
    
}
