package com.lawu.eshop.beh.analyze.param;


import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public class AbnormalAddParam {

    private Long id;

    private String userNum;

    private String account;

    private UserTypeEnum type;

    private Boolean isShortHf;

    /**
     * 是否长高频
     */
    private Boolean isLongHf;

    /**
     * 是否多次短高频
     */
    private Boolean isManyShortHf;

    /**
     * 是否多次短高频
     */
    private Boolean isManyLongHf;

    /**
     * 是否一天注册下线高频
     */
    private Boolean isOneDayHf;

    /**
     * 是否凌晨注册高频
     */
    private Boolean isEarlyHf;

    /**
     * 是否相同ip高频
     */
    private Boolean isIpHf;

    /**
     * 是否异常
     */
    private Boolean isAbnormal;


    /**
     * 是否已通知
     */
    private Boolean isNoticed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public Boolean getShortHf() {
        return isShortHf;
    }

    public void setShortHf(Boolean shortHf) {
        isShortHf = shortHf;
    }

    public Boolean getLongHf() {
        return isLongHf;
    }

    public void setLongHf(Boolean longHf) {
        isLongHf = longHf;
    }

    public Boolean getManyShortHf() {
        return isManyShortHf;
    }

    public void setManyShortHf(Boolean manyShortHf) {
        isManyShortHf = manyShortHf;
    }

    public Boolean getManyLongHf() {
        return isManyLongHf;
    }

    public void setManyLongHf(Boolean manyLongHf) {
        isManyLongHf = manyLongHf;
    }

    public Boolean getOneDayHf() {
        return isOneDayHf;
    }

    public void setOneDayHf(Boolean oneDayHf) {
        isOneDayHf = oneDayHf;
    }

    public Boolean getEarlyHf() {
        return isEarlyHf;
    }

    public void setEarlyHf(Boolean earlyHf) {
        isEarlyHf = earlyHf;
    }

    public Boolean getIpHf() {
        return isIpHf;
    }

    public void setIpHf(Boolean ipHf) {
        isIpHf = ipHf;
    }

    public Boolean getAbnormal() {
        return isAbnormal;
    }

    public void setAbnormal(Boolean abnormal) {
        isAbnormal = abnormal;
    }

    public Boolean getNoticed() {
        return isNoticed;
    }

    public void setNoticed(Boolean noticed) {
        isNoticed = noticed;
    }


    public boolean isAbnormal() {
        return toHfInt(getShortHf()) + toHfInt(getLongHf()) + toHfInt(getManyShortHf()) + toHfInt(getManyLongHf()) + toHfInt(getOneDayHf()) + toHfInt(getEarlyHf())+ toHfInt(getIpHf()) >= 2;
    }

    private int toHfInt(Boolean b) {
        return b ? 1 : 0;
    }
}
