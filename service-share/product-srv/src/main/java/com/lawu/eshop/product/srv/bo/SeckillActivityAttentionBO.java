package com.lawu.eshop.product.srv.bo;

public class SeckillActivityAttentionBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 抢购活动id
     */
    private Long activityId;
    
    /**
     * 抢购活动商品id
     */
    private Long activityProductId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long memberId;
    
    /**
     * 用户编号
     */
    private String memberNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }
}