package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动BO
 *
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 报名结束时间
     */
    private Date attentEndDate;

    /**
     * 会员等级
     */
    private MemberGradeEnum memberLevel;

    /**
     * 商家可提交审核的商品数
     */
    private Integer productValidCount;

    /**
     * 活动定价
     */
    private BigDecimal sellingPrice;

    /**
     * 宣传图片
     */
    private String picture;
    
    /**
    * 首页图片
    */
    private String homePicture;

    /**
     * 活动状态
     */
    private ActivityStatusEnum activityStatus;
    
    /**
     * 活动说明
     */
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

    public Integer getProductValidCount() {
        return productValidCount;
    }

    public void setProductValidCount(Integer productValidCount) {
        this.productValidCount = productValidCount;
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

    public String getHomePicture() {
        return homePicture;
    }

    public void setHomePicture(String homePicture) {
        this.homePicture = homePicture;
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