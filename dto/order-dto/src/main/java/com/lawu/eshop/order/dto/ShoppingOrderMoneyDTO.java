package com.lawu.eshop.order.dto;

import java.math.BigDecimal;

public class ShoppingOrderMoneyDTO {

    /**
     * 订单总价
     */
    private BigDecimal orderTotalPrice;

    private boolean isActivity;

    private String orderNum;

    /**
     * 是否抵扣方式支付
     */
    private boolean isDeductionPay;

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public boolean isActivity() {
        return isActivity;
    }

    public void setActivity(boolean activity) {
        isActivity = activity;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public boolean isDeductionPay() {
        return isDeductionPay;
    }

    public void setDeductionPay(boolean deductionPay) {
        isDeductionPay = deductionPay;
    }
}