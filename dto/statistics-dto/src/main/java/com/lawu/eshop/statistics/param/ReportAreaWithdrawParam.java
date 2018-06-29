package com.lawu.eshop.statistics.param;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
public class ReportAreaWithdrawParam {

    private String beginDate;

    private String endDate;

    private Integer cityId;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
