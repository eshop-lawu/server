package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 当天抢购活动列表DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityThatDayDTO {

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
     * 日期(yyyy-MM-dd)
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "日期(yyyy-MM-dd)", required = true)
    private Date date;
    
    /**
     * 开始时间(HH:mm)
     */
    @JsonFormat(pattern = "HH:mm")
    @ApiModelProperty(value = "开始时间(HH:mm)", required = true)
    private Date startDate;
    
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
}