package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 月结账单封装DTO
 * 
 * @author jiangxinjun
 * @date 2017年10月20日
 */
@ApiModel
public class MonthlyBillDTO {
   
    /**
    * 收入总额
    */
    @JsonSerialize(using = KeepDecimalJsonSerializer.class)
    @ApiModelProperty(value = "收入总额", required = true)
    private BigDecimal totalIncome;
    
    /**
    * 支出总额
    */
    @JsonSerialize(using = KeepDecimalJsonSerializer.class)
    @ApiModelProperty(value = "支出总额", required = true)
    private BigDecimal totalExpenditure;

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpenditure() {
        return totalExpenditure;
    }

    public void setTotalExpenditure(BigDecimal totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }
    
}