package com.lawu.eshop.product.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动商品分页列表DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月27日
 */
@ApiModel
public class SeckillActivityProductBuyPageDTO {
    
    /**
     * 抢购活动id
     */
    @ApiModelProperty(value = "抢购活动id", required = true)
    private Long activityId;
    
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
     * 是否有效
     */
    @ApiModelProperty(value = "是否有效", required = true)
    private Boolean valid;
    
    /**
     * 商品售价
     */
    @ApiModelProperty(value = "商品售价")
    private BigDecimal saleMoney;
    
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

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

	public BigDecimal getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
    
    
}