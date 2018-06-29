package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
public class AgentWithdrawCashParam {

    private Date gmtReport;

    private BigDecimal memberMoney;

    private BigDecimal merchantMoney;

    private BigDecimal totalMoney;

    private Integer cityId;

    private String cityName;

    public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
