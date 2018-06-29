package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动分页列表DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
@ApiModel
public class SeckillActivityInfoDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称", required = true)
    private String name;

    /**
     * 开始时间(yyyy-MM-dd HH:mm)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "开始时间(yyyy-MM-dd HH:mm)", required = true)
    private Date startDate;
    
    /**
     * 结束时间(yyyy-MM-dd HH:mm)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm)", required = true)
    private Date endDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "报名结束时间(yyyy-MM-dd HH:mm)", required = true)
    private Date attentEndDate;

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
     * 宣传图片
     */
    @ApiModelProperty(value = "宣传图片", required = true)
    private String picture;

    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(UNPUBLISHED-未发布|PUBLISHED-发布中|IN_REVIEW-审核中|NOT_STARTED-未开始|PROCESSING-进行中|END-已结束)", required = true)
    private ActivityStatusEnum activityStatus;
    
    /**
     * 活动说明
     */
    @ApiModelProperty(value = "活动说明", required = true)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Date getAttentEndDate() {
        return attentEndDate;
    }

    public void setAttentEndDate(Date attentEndDate) {
        this.attentEndDate = attentEndDate;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}