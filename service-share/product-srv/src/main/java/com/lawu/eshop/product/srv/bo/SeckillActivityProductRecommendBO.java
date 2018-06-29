package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

/**
 * @author meishuquan
 * @date 2018/3/17.
 */
public class SeckillActivityProductRecommendBO {

    private Long productId;

    private String productName;

    private String productPicture;

    private BigDecimal originalPrice;

    private BigDecimal saleMoney;

    @Deprecated
    private ActivityStatusEnum activityStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    @Deprecated
    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    @Deprecated
    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }

}
