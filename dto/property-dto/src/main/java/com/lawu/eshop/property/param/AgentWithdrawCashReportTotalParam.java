package com.lawu.eshop.property.param;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
public class AgentWithdrawCashReportTotalParam {

    private String begin;

    private String end;

    private Byte status;

    private Integer cityId;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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
