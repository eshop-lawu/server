package com.lawu.eshop.activity.param;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
public class MerchantPowerTaskConfigParam {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date powerEffectiveTime;

    List<MerchantPowerTaskBaseConfigParam> tasks;

    public Date getPowerEffectiveTime() {
        return powerEffectiveTime;
    }

    public void setPowerEffectiveTime(Date powerEffectiveTime) {
        this.powerEffectiveTime = powerEffectiveTime;
    }

    public List<MerchantPowerTaskBaseConfigParam> getTasks() {
        return tasks;
    }

    public void setTasks(List<MerchantPowerTaskBaseConfigParam> tasks) {
        this.tasks = tasks;
    }
}
