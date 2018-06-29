package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class CashFeeBO {

    private BigDecimal actualMoney;

    private BigDecimal chargeMoney;

    private BigDecimal serviceMoney;

    private String currentScale;

    public BigDecimal getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(BigDecimal actualMoney) {
        this.actualMoney = actualMoney;
    }

    public BigDecimal getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(BigDecimal chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public String getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(String currentScale) {
        this.currentScale = currentScale;
    }
}