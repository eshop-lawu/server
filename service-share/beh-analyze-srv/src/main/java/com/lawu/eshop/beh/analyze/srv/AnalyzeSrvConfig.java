package com.lawu.eshop.beh.analyze.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@Component
public class AnalyzeSrvConfig {

    @Value(value = "${beh.analyze.short.high.frequency.count}")
    private Integer shortFrequencyCount;

    @Value(value = "${beh.analyze.many.short.high.frequency.count}")
    private Integer shortManyFrequencyCount;

    @Value(value = "${beh.analyze.long.high.frequency.count}")
    private Integer longFrequencyCount;

    @Value(value = "${beh.analyze.many.long.high.frequency.count}")
    private Integer longManyFrequencyCount;

    @Value(value = "${beh.analyze.oneday.high.frequency.count}")
    private Integer oneDayFrequencyCount;

    @Value(value = "${beh.analyze.short.high.frequency.time}")
    private Integer shortFrequencyTime;

    @Value(value = "${beh.analyze.many.short.high.frequency.time}")
    private Integer shortManyFrequencyTime;

    @Value(value = "${beh.analyze.long.high.frequency.time}")
    private Integer longFrequencyTime;

    @Value(value = "${beh.analyze.many.long.high.frequency.time}")
    private Integer longManyFrequencyTime;

    @Value(value = "${beh.analyze.oneday.high.frequency.time}")
    private Integer oneDayFrequencyTime;

    @Value(value = "${beh.analyze.early.high.frequency.count}")
    private Integer earlyFrequencyCount;

    @Value(value = "${beh.analyze.early.high.frequency.startTime}")
    private String earlyStartTime;

    @Value(value = "${beh.analyze.early.high.frequency.endTime}")
    private String earlyEndTime;

    public Integer getShortFrequencyCount() {
        return shortFrequencyCount;
    }

    public Integer getShortManyFrequencyCount() {
        return shortManyFrequencyCount;
    }

    public Integer getLongFrequencyCount() {
        return longFrequencyCount;
    }

    public Integer getLongManyFrequencyCount() {
        return longManyFrequencyCount;
    }

    public Integer getOneDayFrequencyCount() {
        return oneDayFrequencyCount;
    }

    public Integer getShortFrequencyTime() {
        return shortFrequencyTime;
    }

    public Integer getShortManyFrequencyTime() {
        return shortManyFrequencyTime;
    }

    public Integer getLongFrequencyTime() {
        return longFrequencyTime;
    }

    public Integer getLongManyFrequencyTime() {
        return longManyFrequencyTime;
    }

    public Integer getOneDayFrequencyTime() {
        return oneDayFrequencyTime;
    }

    public Integer getEarlyFrequencyCount() {
        return earlyFrequencyCount;
    }

    public String getEarlyStartTime() {
        return earlyStartTime;
    }

    public String getEarlyEndTime() {
        return earlyEndTime;
    }
}
