package com.lawu.eshop.product.dto;

import java.util.List;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/17.
 */
public class SeckillActivityProductPartDTO {

    @ApiModelProperty(value = "倒计时")
    private Long countdown;

    @ApiModelProperty(value = "活动状态(NOT_STARTED-未开始|PROCESSING-进行中|END-已结束)", required = true)
    private ActivityStatusEnum activityStatus;

    @ApiModelProperty(value = "趣乐购首页展示的抢购商品")
    List<SeckillActivityProductRecommendDTO> recommendDTOS;

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }

    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }

    public List<SeckillActivityProductRecommendDTO> getRecommendDTOS() {
        return recommendDTOS;
    }

    public void setRecommendDTOS(List<SeckillActivityProductRecommendDTO> recommendDTOS) {
        this.recommendDTOS = recommendDTOS;
    }
}
