package com.lawu.eshop.cache.dto;

/**
 * @author zhangyong
 * @date 2018/1/18.
 */
public class AbnormalRedisCountDTO {
    private Integer shortHfCount;
    private Integer longHfCount;
    private Integer manyShortHfCount;
    private Integer manyLongHfCount;
    private Integer oneDayCount;
    private Integer earlyCount;

    public Integer getShortHfCount() {
        return shortHfCount;
    }

    public void setShortHfCount(Integer shortHfCount) {
        this.shortHfCount = shortHfCount;
    }

    public Integer getLongHfCount() {
        return longHfCount;
    }

    public void setLongHfCount(Integer longHfCount) {
        this.longHfCount = longHfCount;
    }

    public Integer getManyShortHfCount() {
        return manyShortHfCount;
    }

    public void setManyShortHfCount(Integer manyShortHfCount) {
        this.manyShortHfCount = manyShortHfCount;
    }

    public Integer getManyLongHfCount() {
        return manyLongHfCount;
    }

    public void setManyLongHfCount(Integer manyLongHfCount) {
        this.manyLongHfCount = manyLongHfCount;
    }

    public Integer getOneDayCount() {
        return oneDayCount;
    }

    public void setOneDayCount(Integer oneDayCount) {
        this.oneDayCount = oneDayCount;
    }

    public Integer getEarlyCount() {
        return earlyCount;
    }

    public void setEarlyCount(Integer earlyCount) {
        this.earlyCount = earlyCount;
    }
}
