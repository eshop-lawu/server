package com.lawu.eshop.product.srv.bo;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

/**
 * 最近一场抢购活动BO
 * @author jiangxinjun
 * @createDate 2018年2月28日
 * @updateDate 2018年2月28日
 */
public class RecentlySeckillActivityBO {
    
    /**
     * 主键
     */
    private Long id;
    
    /**
    * 首页图片
    */
    @Deprecated
    private String homePicture;

    /**
     * 活动状态
     */
    private ActivityStatusEnum activityStatus;
    
    /**
     * 倒计时
     */
    private Long countdown;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Deprecated
    public String getHomePicture() {
        return homePicture;
    }

    @Deprecated
    public void setHomePicture(String homePicture) {
        this.homePicture = homePicture;
    }

    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }
    
}