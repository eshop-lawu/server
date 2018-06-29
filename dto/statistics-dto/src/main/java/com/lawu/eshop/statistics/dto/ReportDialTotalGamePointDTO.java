package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class ReportDialTotalGamePointDTO {
	
	   @ApiModelProperty(value = "总收入")
	    private BigDecimal totalIncomePoint;

	    @ApiModelProperty(value = "总支出")
	    private BigDecimal totalExpendPoint;

	    public BigDecimal getTotalIncomePoint() {
	        return totalIncomePoint;
	    }

	    public void setTotalIncomePoint(BigDecimal totalIncomePoint) {
	        this.totalIncomePoint = totalIncomePoint;
	    }

	    public BigDecimal getTotalExpendPoint() {
	        return totalExpendPoint;
	    }

	    public void setTotalExpendPoint(BigDecimal totalExpendPoint) {
	        this.totalExpendPoint = totalExpendPoint;
	    }

}
