package com.lawu.eshop.statistics.dto;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class UserActiveDTO {

    private int cityId;

    private String cityName;

    private Date visitDate;

    private int userCount;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}
