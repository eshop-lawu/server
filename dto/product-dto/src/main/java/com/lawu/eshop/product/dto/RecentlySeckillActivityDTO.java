package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 最近一场抢购活动DTO
 * @author jiangxinjun
 * @createDate 2018年2月28日
 * @updateDate 2018年2月28日
 */
@ApiModel
public class RecentlySeckillActivityDTO {

    /**
    * 首页图片
    */
    @ApiModelProperty(value = "首页图片", required = true)
    private String homePicture;

    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(UNPUBLISHED-未发布|PUBLISHED-发布中|IN_REVIEW-审核中|NOT_STARTED-未开始|PROCESSING-进行中|END-已结束)", required = true)
    private ActivityStatusEnum activityStatus;
    
    /**
     * 倒计时
     */
    @ApiModelProperty(value = "倒计时")
    private Long countdown;

    public String getHomePicture() {
        return homePicture;
    }

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