package com.lawu.eshop.property.param;

import java.math.BigDecimal;

public class CountIncomeBroadcastParam {

    private String gmtReport;
    private String userNum;
    private Byte incomeType;
    private BigDecimal money;

    public String getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(String gmtReport) {
        this.gmtReport = gmtReport;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Byte getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(Byte incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
