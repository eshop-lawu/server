package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

/**
 * 月结账单封装BO
 * 
 * @author jiangxinjun
 * @date 2017年10月20日
 */
public class MonthlyBillBO {
   
    /**
    * 收入总额
    */
    private BigDecimal totalIncome;
    
    /**
    * 支出总额
    */
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