package com.lawu.eshop.product.dto;

/**
 * 抢购商品型号信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class SeckillActivityProductModelInfoDTO extends ShoppingCartProductModelDTO {
    
    /**
     * 活动id
     */
    private Long activityId;
    
    /**
     * 活动商品id
     */
    private Long activityProductId;
    
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }
}
