package com.lawu.eshop.order.param;

import java.math.BigDecimal;

import com.lawu.eshop.common.dto.FreightDTO;

/**
 * 购物车结算api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderSettlementItemParam {

    /**
     * 对应的购物车Id(在保存订单之后通过这个删除购物车里面的记录)
     */
    private Long shoppingCartId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品型号id
     */
    private Long productModelId;

    /**
     * 活动商品型号id
     */
    private Long activityProductModelId;

    /**
     * 商品型号名称
     */
    private String productModelName;

    /**
     * 商品特征图片
     */
    private String productFeatureImage;

    /**
     * 原价
     */
    private BigDecimal regularPrice;

    /**
     * 现价
     */
    private BigDecimal salesPrice;

    /**
     * 数量
     */
    private Integer quantity;
    
    /**
     * 小计
     */
    private BigDecimal subtotal;

    /**
     * 是否支持退换货(0-否1-是)
     */
    private Boolean isAllowRefund;

    /**
     * 抵扣积分
     */
    private BigDecimal point;

    /**
     * 积分抵扣金额
     */
    private BigDecimal deductionPointsAmount;
    
    /**
     * 运费计算规则
     */
    private FreightDTO freight;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

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

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }

    public Long getActivityProductModelId() {
        return activityProductModelId;
    }

    public void setActivityProductModelId(Long activityProductModelId) {
        this.activityProductModelId = activityProductModelId;
    }

    public String getProductModelName() {
        return productModelName;
    }

    public void setProductModelName(String productModelName) {
        this.productModelName = productModelName;
    }

    public String getProductFeatureImage() {
        return productFeatureImage;
    }

    public void setProductFeatureImage(String productFeatureImage) {
        this.productFeatureImage = productFeatureImage;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean isAllowRefund) {
        this.isAllowRefund = isAllowRefund;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getDeductionPointsAmount() {
        return deductionPointsAmount;
    }

    public void setDeductionPointsAmount(BigDecimal deductionPointsAmount) {
        this.deductionPointsAmount = deductionPointsAmount;
    }

    public FreightDTO getFreight() {
        return freight;
    }

    public void setFreight(FreightDTO freight) {
        this.freight = freight;
    }

}
