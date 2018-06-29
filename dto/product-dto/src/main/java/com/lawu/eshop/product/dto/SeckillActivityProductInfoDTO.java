package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.product.constant.ActivityProductStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动商品分页列表DTO
 * 用于运营平台
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
@ApiModel(description = "抢购活动商品分页列表DTO")
public class SeckillActivityProductInfoDTO {
    
    /**
     * 抢购活动商品id
     */
    @ApiModelProperty(value = "抢购活动商品id", required = true)
    private Long activityProductId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id", required = true)
    private Long productId;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片", required = true)
    private String productPicture;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true)
    private String productName;

    /**
     * 商品原价
     */
    @ApiModelProperty(value = "商品原价", required = true)
    private BigDecimal originalPrice;

    /**
     * 商品型号总数量
     */
    @ApiModelProperty(value = "商品型号总数量", required = true)
    private Integer productModelCount;

    /**
     * 剩余数量
     */
    @ApiModelProperty(value = "剩余数量", required = true)
    private Integer leftCount;
    
    /**
     * 成交额
     */
    @ApiModelProperty(value = "成交额", required = true)
    private BigDecimal turnover;
    
    /**
     * 排序优先级
     */
    @ApiModelProperty(value = "排序优先级", required = true)
    private Integer priority;

    /**
     * 反馈原因
     */
    @ApiModelProperty(value = "反馈原因", required = true)
    private String reasons;

    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数", required = true)
    private Integer attentionCount;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private ActivityProductStatusEnum status;
    
    /**
     * 商家id
     */
    @ApiModelProperty(value = "商家id", required = true)
    private Long merchantId;
    
    /**
     * 商品售价
     */
    @ApiModelProperty(value = "商品售价", required = true)
    private BigDecimal saleMoney;
    
    /**
     * 审核人员
     */
    @ApiModelProperty(value = "审核人员")
    private String auditorAccount;

    /**
     * 审核时间
     */
    @JsonFormat
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;
    
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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