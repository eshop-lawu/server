package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
public class ReportAreaWithdrawDTO {

    @ApiModelProperty(value = "会员提现成功金额(含手续费)")
    private BigDecimal memberMoney;

    @ApiModelProperty(value = "商家提现成功金额(含手续费)")
    private BigDecimal merchantMoney;

    @ApiModelProperty(value = "总提现成功金额(含手续费)")
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
