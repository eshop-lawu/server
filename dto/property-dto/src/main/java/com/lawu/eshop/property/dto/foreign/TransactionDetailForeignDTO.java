package com.lawu.eshop.property.dto.foreign;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细DTO
 * @author jiangxinjun
 * @date 2017年10月20日
 */
public class TransactionDetailForeignDTO {

    @ApiModelProperty(value = "列表ID", required = true)
    private Long id;
    
    /**
     * 交易标题
     */
    @ApiModelProperty(value = "交易标题", required = true)
    private String title;

    /**
     * 金额
     */
    @JsonSerialize(using = KeepDecimalJsonSerializer.class)
    @ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    /**
     * 交易时间
     */
    @ApiModelProperty(value = "交易时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date transactionDate;
    
    /**
     * 资金流向
     */
    @ApiModelProperty(value = "资金流向(EXPENDITURE-支出|INCOME-收入)", required = true)
    private ConsumptionTypeEnum direction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public ConsumptionTypeEnum getDirection() {
        return direction;
    }

    public void setDirection(ConsumptionTypeEnum direction) {
        this.direction = direction;
    }

}