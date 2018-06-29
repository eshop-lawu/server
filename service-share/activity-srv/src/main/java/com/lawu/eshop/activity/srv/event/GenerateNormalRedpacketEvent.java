package com.lawu.eshop.activity.srv.event;

import java.math.BigDecimal;

import com.lawu.framework.core.event.AsyncEvent;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年1月4日
 * @updateDate 2018年1月4日
 */
public class GenerateNormalRedpacketEvent extends AsyncEvent {

    private static final long serialVersionUID = -2803285901780541750L;
    
    /**
     * 自动分配红包总额
     */
    private BigDecimal totalAutoAmount;
    
    /**
     * 活动id
     */
    private Integer activityId;
    
    /**
     * 获取当前已经分配的红包金额
     */
    private BigDecimal redpacketAmount;
    
    public GenerateNormalRedpacketEvent(Object source) {
        super(source);
    }

    public BigDecimal getTotalAutoAmount() {
        return totalAutoAmount;
    }

    public void setTotalAutoAmount(BigDecimal totalAutoAmount) {
        this.totalAutoAmount = totalAutoAmount;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getRedpacketAmount() {
        return redpacketAmount;
    }

    public void setRedpacketAmount(BigDecimal redpacketAmount) {
        this.redpacketAmount = redpacketAmount;
    }
    
}
