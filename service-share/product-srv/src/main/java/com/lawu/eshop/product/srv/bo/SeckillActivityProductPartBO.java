package com.lawu.eshop.product.srv.bo;

import java.util.List;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月23日
 * @updateDate 2018年5月23日
 */
public class SeckillActivityProductPartBO {

    /**
     * 当前活动倒计时
     */
    private Long countdown;

    /**
     * 当前活动状态
     */
    private ActivityStatusEnum activityStatus;

    /**
     * 当前活动抢购的商品
     */
    private List<SeckillActivityProductRecommendBO> products;

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

    public List<SeckillActivityProductRecommendBO> getProducts() {
        return products;
    }

    public void setProducts(List<SeckillActivityProductRecommendBO> products) {
        this.products = products;
    }
    
}
