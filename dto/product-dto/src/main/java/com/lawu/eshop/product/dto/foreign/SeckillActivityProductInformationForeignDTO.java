package com.lawu.eshop.product.dto.foreign;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInformationDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 当天抢购活动商品详情DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
@ApiModel
public class SeckillActivityProductInformationForeignDTO {

    /**
     * 活动商品id
     */
    @ApiModelProperty(value = "活动商品id", required = true)
    private Long activityProductId;
    
    /**
     * 会员等级
     */
    @ApiModelProperty(value = "会员等级", required = true)
    private MemberGradeEnum memberLevel;

    /**
     * 活动定价
     */
    @ApiModelProperty(value = "活动定价", required = true)
    private BigDecimal sellingPrice;
    
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(UNPUBLISHED-未发布|NOT_STARTED-未开始|PROCESSING-进行中|END-已结束)", required = true)
    private ActivityStatusEnum activityStatus;
    
    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数", required = true)
    private Integer attentionCount;
    
    /**
     * 是否关注
     */
    @ApiModelProperty(value = "是否关注", required = true)
    private Boolean attention;
    
    /**
     * 是否已经超过设置提醒的时间
     */
    @ApiModelProperty(value = "是否已经超过设置提醒的时间", required = true)
    private Boolean exceededAttentionTime;
    
    /**
     * 已售数量
     */
    @ApiModelProperty(value = "已售数量", required = true)
    private Integer soldQuantity;
    
    /**
     * 倒计时
     */
    @ApiModelProperty(value = "倒计时", required = true)
    private Long countdown;
    
    /**
     * 结束倒计时
     */
    @ApiModelProperty(value = "结束倒计时", required = true)
    private Long endCountdown;
    
    /**
     * 是否购买过
     */
    @ApiModelProperty(value = "是否购买过", required = true)
    private Boolean buy;
    
    /**
     * 抢购商品型号库存信息
     */
    @ApiModelProperty(value = "抢购商品型号库存信息", required = true)
    List<SeckillActivityProductModelInformationDTO> productModelList;
    
    /**
     * 抢购商品售价
     */
    @ApiModelProperty(value = "抢购商品售价")
    private BigDecimal saleMoney;

    public Long getActivityProductId() {
        return activityProductId;
    }

    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }

    public MemberGradeEnum getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberGradeEnum memberLevel) {
        this.memberLevel = memberLevel;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Boolean getAttention() {
        return attention;
    }

    public void setAttention(Boolean attention) {
        this.attention = attention;
    }

    public Boolean getExceededAttentionTime() {
        return exceededAttentionTime;
    }

    public void setExceededAttentionTime(Boolean exceededAttentionTime) {
        this.exceededAttentionTime = exceededAttentionTime;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }
    
    public Long getEndCountdown() {
        return endCountdown;
    }

    public void setEndCountdown(Long endCountdown) {
        this.endCountdown = endCountdown;
    }

    public Boolean getBuy() {
        return buy;
    }

    public void setBuy(Boolean buy) {
        this.buy = buy;
    }
    
    public List<SeckillActivityProductModelInformationDTO> getProductModelList() {
        return productModelList;
    }

    public void setProductModelList(List<SeckillActivityProductModelInformationDTO> productModelList) {
        this.productModelList = productModelList;
    }

	public BigDecimal getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
    
}