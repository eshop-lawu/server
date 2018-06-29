package com.lawu.eshop.activity.srv.domain.extend;

import java.math.BigDecimal;
import java.util.List;

/**
 * 获取预期红包总金额参数
 * 
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
public class RedpacketAmountParam {
    
    /**
     * 活动id
     */
    private Integer activityId;
    
    /**
     * 排除在外的id
     */
    private List<Long> ids;

    /**
     * 红包的金额
     */
    private BigDecimal redpacketAmount;

    /**
     * 红包叠加倍数
     */
    private BigDecimal multiple;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public BigDecimal getRedpacketAmount() {
        return redpacketAmount;
    }

    public void setRedpacketAmount(BigDecimal redpacketAmount) {
        this.redpacketAmount = redpacketAmount;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }
    
}