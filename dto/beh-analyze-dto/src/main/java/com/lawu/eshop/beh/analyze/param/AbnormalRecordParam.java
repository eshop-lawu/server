package com.lawu.eshop.beh.analyze.param;

import com.lawu.eshop.beh.analyze.constants.AbnormalEnum;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public class AbnormalRecordParam {

    /**
     * 短高频
     */
    private Boolean shortHfFlag;
    /**
     * 长高频
     */
    private Boolean longHfFlag;
    /**
     * 多次短高频
     */
    private Boolean manyShortHfFlag;
    /**
     * 多次长高频
     */
    private Boolean manyLongHfFlag;
    /**
     * 一天高频下线
     */
    private Boolean oneDayFlag;

    private Boolean earlyFlag;

    private AbnormalEnum abnormal;

    public Boolean getShortHfFlag() {
        return shortHfFlag;
    }

    public void setShortHfFlag(Boolean shortHfFlag) {
        this.shortHfFlag = shortHfFlag;
    }

    public Boolean getLongHfFlag() {
        return longHfFlag;
    }

    public void setLongHfFlag(Boolean longHfFlag) {
        this.longHfFlag = longHfFlag;
    }

    public Boolean getManyShortHfFlag() {
        return manyShortHfFlag;
    }

    public void setManyShortHfFlag(Boolean manyShortHfFlag) {
        this.manyShortHfFlag = manyShortHfFlag;
    }

    public Boolean getManyLongHfFlag() {
        return manyLongHfFlag;
    }

    public void setManyLongHfFlag(Boolean manyLongHfFlag) {
        this.manyLongHfFlag = manyLongHfFlag;
    }

    public Boolean getOneDayFlag() {
        return oneDayFlag;
    }

    public void setOneDayFlag(Boolean oneDayFlag) {
        this.oneDayFlag = oneDayFlag;
    }

    public Boolean getEarlyFlag() {
        return earlyFlag;
    }

    public void setEarlyFlag(Boolean earlyFlag) {
        this.earlyFlag = earlyFlag;
    }

    public AbnormalEnum getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(AbnormalEnum abnormal) {
        this.abnormal = abnormal;
    }
}
