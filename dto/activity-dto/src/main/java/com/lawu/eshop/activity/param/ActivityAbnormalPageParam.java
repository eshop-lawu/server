package com.lawu.eshop.activity.param;

import java.util.Date;

import com.lawu.framework.core.page.AbstractPageParam;


/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class ActivityAbnormalPageParam extends AbstractPageParam {

    private Date gmtCreate;

    private Integer minTime;

    private Integer maxTime;

    private Integer abnormalCount;

    private Byte abnormalStatus;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Integer getAbnormalCount() {
        return abnormalCount;
    }

    public void setAbnormalCount(Integer abnormalCount) {
        this.abnormalCount = abnormalCount;
    }

    public Byte getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(Byte abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }
}
