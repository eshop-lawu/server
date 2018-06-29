package com.lawu.eshop.property.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class TransactionDetailBaseInfoDTO {

    @ApiModelProperty(value = "交易标题", required = true)
    private String title;

    @JsonSerialize(using = KeepDecimalJsonSerializer.class)
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    @ApiModelProperty(value = "交易说明", required = true)
    private String transactionDesc;

    @ApiModelProperty(value = "其他说明(支付方式、退款方式、提现账户)", required = true)
    private String otherDesc;

    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtDate;

    @ApiModelProperty(value = "订单编号", required = true)
    private String orderNum;

    @ApiModelProperty(value = "资金流向(EXPENDITURE-支出|INCOME-收入)", required = true)
    private ConsumptionTypeEnum direction;

    public ConsumptionTypeEnum getDirection() {
        return direction;
    }

    public void setDirection(ConsumptionTypeEnum direction) {
        this.direction = direction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public Date getGmtDate() {
        return gmtDate;
    }

    public void setGmtDate(Date gmtDate) {
        this.gmtDate = gmtDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }


}
