package com.lawu.eshop.product.srv.bo;

/**
 * 抢购活动商品型号BO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class SeckillActivityProductModelBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 抢购活动审核商品id
     */
    private Long activityProductId;

    /**
     * 商品型号id
     */
    private Long productModelId;

    /**
     * 商品型号数量
     */
    private Integer count;

    /**
     * 剩余数量
     */
    private Integer leftCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }
    
}