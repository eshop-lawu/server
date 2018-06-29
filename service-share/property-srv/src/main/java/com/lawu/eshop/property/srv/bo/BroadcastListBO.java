package com.lawu.eshop.property.srv.bo;

import com.lawu.eshop.property.constants.PayTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BroadcastListBO {

    private BigDecimal income;

    private String date;

    private PayTypeEnum payTypeEnum;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }
}
