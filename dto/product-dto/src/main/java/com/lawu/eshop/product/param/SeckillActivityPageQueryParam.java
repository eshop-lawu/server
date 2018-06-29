package com.lawu.eshop.product.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 运营平台分页查询参数
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityPageQueryParam extends AbstractPageParam {
    
    /**
     * 开始时间起始
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间起始")
    private Date startDateLeft;
    
    /**
     * 开始时间结尾
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间结尾")
    private Date startDateRight;
    
    /**
     * 会员等级
     */
    @ApiModelProperty(value = "会员等级")
    private MemberGradeEnum memberLevel;
    
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态")
    private ActivityStatusEnum activityStatus;

    public Date getStartDateLeft() {
        return startDateLeft;
    }

    public void setStartDateLeft(Date startDateLeft) {
        this.startDateLeft = startDateLeft;
    }

    public Date getStartDateRight() {
        return startDateRight;
    }

    public void setStartDateRight(Date startDateRight) {
        this.startDateRight = startDateRight;
    }

    public MemberGradeEnum getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberGradeEnum memberLevel) {
        this.memberLevel = memberLevel;
    }

    public ActivityStatusEnum getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatusEnum activityStatus) {
        this.activityStatus = activityStatus;
    }
    
}
