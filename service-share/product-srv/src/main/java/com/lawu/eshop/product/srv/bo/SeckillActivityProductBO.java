package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.product.constant.ActivityProductStatusEnum;

/**
 * 抢购活动商品BO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityProductBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 抢购活动id
     */
    private Long activityId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品图片
     */
    private String productPicture;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品原价
     */
    private BigDecimal originalPrice;

    /**
     * 商品型号总数量
     */
    private Integer productModelCount;

    /**
     * 剩余数量
     */
    private Integer leftCount;

    /**
     * 成交额
     */
    private BigDecimal turnover;

    /**
     * 排序优先级
     */
    private Integer priority;

    /**
     * 反馈原因
     */
    private String reasons;

    /**
     * 关注人数
     */
    private Integer attentionCount;

    /**
     * 状态
     */
    private ActivityProductStatusEnum status;

    /**
     * 商品抢购价格
     */
    private BigDecimal saleMoney;

    /**
     * 审核人员
     */
    private String auditorAccount;

    /**
     * 审核时间
     */
    private Date auditTime;

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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getProductModelCount() {
        return productModelCount;
    }

    public void setProductModelCount(Integer productModelCount) {
        this.productModelCount = productModelCount;
    }

    public Integer getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(Integer leftCount) {
        this.leftCount = leftCount;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public ActivityProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ActivityProductStatusEnum status) {
        this.status = status;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public String getAuditorAccount() {
        return auditorAccount;
    }

    public void setAuditorAccount(String auditorAccount) {
        this.auditorAccount = auditorAccount;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

}