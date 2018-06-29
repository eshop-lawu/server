package com.lawu.eshop.order.param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车结算api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderSettlementParam {

    /**
     * 用户id
     */
    private Long memberId;

    /**
     * 用户编号
     */
    private String memberNum;

    /**
     * 用户昵称
     */
    private String memberNickname;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 商家门店id
     */
    private Long merchantStoreId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动商品id
     */
    private Long activityProductId;

    /**
     * 商家门店省市区
     */
    private String merchantStoreRegionPath;

    /**
     * 商家编号
     */
    private String merchantNum;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 买家留言
     */
    private String message;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddress;

    /**
     * 收货人手机号码
     */
    private String consigneeMobile;

    /**
     * 运费
     */
    private BigDecimal freightPrice;
    
    /**
     * 运费抵扣积分
     */
    private BigDecimal freightPriceDeductionPoints;

    /**
     * 商品总价
     */
    private BigDecimal commodityTotalPrice;

    /**
     * 订单总价
     */
    private BigDecimal orderTotalPrice;

    /**
     * 是否支持无理由退货,0否 1是
     */
    private Boolean isNoReasonReturn;

    /**
     * 用户是否是商家粉丝
     */
    private Boolean isFans;

    /**
     * 抵扣积分
     */
    private BigDecimal deductionPoints;

    /**
     * 积分抵扣金额
     */
    private BigDecimal deductionPointsAmount;
    
    /**
     * 金额兑积分比率
     */
    private BigDecimal exchangeRate;
    
    /**
     * 订单项
     */
    private List<ShoppingOrderSettlementItemParam> items;

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

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
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

    public String getMerchantStoreRegionPath() {
        return merchantStoreRegionPath;
    }

    public void setMerchantStoreRegionPath(String merchantStoreRegionPath) {
        this.merchantStoreRegionPath = merchantStoreRegionPath;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getFreightPriceDeductionPoints() {
        return freightPriceDeductionPoints;
    }

    public void setFreightPriceDeductionPoints(BigDecimal freightPriceDeductionPoints) {
        this.freightPriceDeductionPoints = freightPriceDeductionPoints;
    }

    public BigDecimal getCommodityTotalPrice() {
        return commodityTotalPrice;
    }

    public void setCommodityTotalPrice(BigDecimal commodityTotalPrice) {
        this.commodityTotalPrice = commodityTotalPrice;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Boolean getIsNoReasonReturn() {
        return isNoReasonReturn;
    }

    public void setIsNoReasonReturn(Boolean isNoReasonReturn) {
        this.isNoReasonReturn = isNoReasonReturn;
    }

    public Boolean getIsFans() {
        return isFans;
    }

    public void setIsFans(Boolean isFans) {
        this.isFans = isFans;
    }

    public BigDecimal getDeductionPoints() {
        return deductionPoints;
    }

    public void setDeductionPoints(BigDecimal deductionPoints) {
        this.deductionPoints = deductionPoints;
    }

    public BigDecimal getDeductionPointsAmount() {
        return deductionPointsAmount;
    }

    public void setDeductionPointsAmount(BigDecimal deductionPointsAmount) {
        this.deductionPointsAmount = deductionPointsAmount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<ShoppingOrderSettlementItemParam> getItems() {
        return items;
    }

    public void setItems(List<ShoppingOrderSettlementItemParam> items) {
        this.items = items;
    }

}
