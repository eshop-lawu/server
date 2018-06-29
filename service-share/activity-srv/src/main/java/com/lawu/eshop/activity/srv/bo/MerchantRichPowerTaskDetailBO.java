package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;

/**
 * @author zhangyong
 * @date 2018/6/8.
 */
public class MerchantRichPowerTaskDetailBO {

    private MerchantPowerTaskTypeEnum type;

    /**
     * 任务数量
     */
    private Integer taskCount;

    /**
     * 动力值
     */
    private Integer powerCount;


    /**
     * 是否完成
     */
    private Boolean isFinish;


    /**
     * 有效时间开始
     */
    private Date beginTime;

    /**
     * 有效时间结束
     */
    private Date endTime;

    private PowerTaskStatusEnum status;


    /**
     * 剩余次数
     */
    private int residueCount;

    public MerchantPowerTaskTypeEnum getType() {
        return type;
    }

    public void setType(MerchantPowerTaskTypeEnum type) {
        this.type = type;
    }

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

    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean finish) {
        isFinish = finish;
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

    public PowerTaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PowerTaskStatusEnum status) {
        this.status = status;
    }

    public int getResidueCount() {
        return residueCount;
    }

    public void setResidueCount(int residueCount) {
        this.residueCount = residueCount;
    }
}
