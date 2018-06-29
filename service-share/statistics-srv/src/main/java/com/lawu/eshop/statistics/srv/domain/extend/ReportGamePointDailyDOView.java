package com.lawu.eshop.statistics.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2018/3/26.
 */
public class ReportGamePointDailyDOView implements Serializable {
    private static final long serialVersionUID = 6586703279111228131L;

   private BigDecimal totalIncomePoint;

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
