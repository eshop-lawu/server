package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;
import java.util.List;

/**
 * 更新最低红包金额
 * 
 * @author jiangxinjun
 * @createDate 2017年1月2日
 * @updateDate 2018年1月2日
 */
public class UpdateMinRedpacketAmountParam {
    
    /**
     * 活动id
     */
    private Integer activityId;
    
    /**
     * 红包的金额
     */
    private BigDecimal minRedpacketAmount;

    /**
     * 红包叠加倍数
     */
    private BigDecimal multiple;
    
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

    public BigDecimal getMinRedpacketAmount() {
        return minRedpacketAmount;
    }

    public void setMinRedpacketAmount(BigDecimal minRedpacketAmount) {
        this.minRedpacketAmount = minRedpacketAmount;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public List<Byte> getAbnormalStatus() {
        return abnormalStatus;
    }

    public void setAbnormalStatus(List<Byte> abnormalStatus) {
        this.abnormalStatus = abnormalStatus;
    }
    
}