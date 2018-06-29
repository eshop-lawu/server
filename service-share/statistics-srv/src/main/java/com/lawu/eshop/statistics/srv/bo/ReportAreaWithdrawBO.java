package com.lawu.eshop.statistics.srv.bo;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
public class ReportAreaWithdrawBO {
    private BigDecimal memberMoney;

    private BigDecimal merchantMoney;

    private BigDecimal totalMoney;

    public BigDecimal getMemberMoney() {
        return memberMoney;
    }

    public void setMemberMoney(BigDecimal memberMoney) {
        this.memberMoney = memberMoney;
    }

    public BigDecimal getMerchantMoney() {
        return merchantMoney;
    }

    public void setMerchantMoney(BigDecimal merchantMoney) {
        this.merchantMoney = merchantMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
}
