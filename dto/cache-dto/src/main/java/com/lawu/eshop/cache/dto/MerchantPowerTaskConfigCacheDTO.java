package com.lawu.eshop.cache.dto;

import java.io.Serializable;
import java.util.Date;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;

/**
 * @author zhangyong
 * @date 2018/6/7.
 */
public class MerchantPowerTaskConfigCacheDTO implements Serializable{
    private static final long serialVersionUID = -3834917205668607377L;
    /**
     * 任务数量
     */
    private Integer taskCount;

    /**
     * 动力值
     */
    private Integer powerCount;

    /**
     * 任务状态
     */
    private PowerTaskStatusEnum status;


    private MerchantPowerTaskTypeEnum type;


    /**
     * 有效时间开始
     */
    private Date beginTime;

    /**
     * 有效时间结束
     */
    private Date endTime;

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getPowerCount() {
        return powerCount;
    }

    public void setPowerCount(Integer powerCount) {
        this.powerCount = powerCount;
    }

    public PowerTaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PowerTaskStatusEnum status) {
        this.status = status;
    }

    public MerchantPowerTaskTypeEnum getType() {
        return type;
    }

    public void setType(MerchantPowerTaskTypeEnum type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
