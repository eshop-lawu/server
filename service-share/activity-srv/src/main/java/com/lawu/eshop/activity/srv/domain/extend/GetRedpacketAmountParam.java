package com.lawu.eshop.activity.srv.domain.extend;

import java.util.List;

/**
 * 获取红包总金额参数
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public class GetRedpacketAmountParam {
    
    /**
     * 活动id
     */
    private Integer activityId;
    
    /**
     * 异常状态
     */
    private List<Byte> abnormalStatus;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<Byte> getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(List<Byte> abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }
    
}