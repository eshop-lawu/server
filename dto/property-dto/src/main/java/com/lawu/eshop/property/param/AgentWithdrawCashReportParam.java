package com.lawu.eshop.property.param;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
public class AgentWithdrawCashReportParam {

    private String date;

    private Byte status;

    private Integer cityId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
