package com.lawu.eshop.product.srv.bo;

/**
 * @author meishuquan
 * @date 2018/5/17.
 */
public class ShareSeckillActivityProductBO {

    private Long productId;

    private Integer leftCount;

    private Byte activityStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public Byte getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Byte activityStatus) {
        this.activityStatus = activityStatus;
    }
}
