package com.lawu.eshop.property.dto;

import com.lawu.eshop.property.constants.PayTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class BroadcastListDTO {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "收益")
    private BigDecimal income;

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "类型(BALANCE-余额|POINT-积分)")
    private PayTypeEnum payTypeEnum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public PayTypeEnum getPayTypeEnum() {
        return payTypeEnum;
    }

    public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
        this.payTypeEnum = payTypeEnum;
    }
}
