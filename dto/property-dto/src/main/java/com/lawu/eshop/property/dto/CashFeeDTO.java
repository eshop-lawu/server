package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class CashFeeDTO {

    @ApiModelProperty(value = "实际到账", required = true)
    private BigDecimal actualMoney;

    @ApiModelProperty(value = "手续费", required = true)
    private BigDecimal chargeMoney;

    @ApiModelProperty(value = "服务费", required = true)
    private BigDecimal serviceMoney;

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
}