package com.lawu.eshop.user.param;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
public class UserVisitInfoParam implements Serializable {

    private static final long serialVersionUID = 2368488381031859894L;

    private Byte userType;

    private String userNum;

    private String account;

    private Integer visitCount;

    private Integer cityId;

    private String cityName;

    private Date visitDate;

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
}
