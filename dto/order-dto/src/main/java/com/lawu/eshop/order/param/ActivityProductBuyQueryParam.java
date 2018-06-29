package com.lawu.eshop.order.param;

/**
 * 查看用户是否购买过抢购活动的商品
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class ActivityProductBuyQueryParam {
    
    /**
     * 抢购活动商品id
     */
    private Long activityProductId;
    
    /**
     * 会员id
     */
    private Long memberId;

    public Long getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    
}
