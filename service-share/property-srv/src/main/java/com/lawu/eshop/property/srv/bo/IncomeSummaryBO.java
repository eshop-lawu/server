package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class IncomeSummaryBO {

    private BigDecimal money;

    private Byte incomeType;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Byte getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Byte incomeType) {
        this.incomeType = incomeType;
    }
}
