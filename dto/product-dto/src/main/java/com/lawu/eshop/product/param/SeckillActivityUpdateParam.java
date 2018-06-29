package com.lawu.eshop.product.param;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 抢购活动更新参数
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月29日
 */
@ApiModel
public class SeckillActivityUpdateParam {

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空")
    @ApiModelProperty(value = "活动名称", required = true)
    private String name;

    /**
     * 开始时间(yyyy-MM-dd HH:mm)
     */
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "开始时间(yyyy-MM-dd HH:mm)", required = true)
    private Date startDate;

    /**
     * 会员等级
     */
    @NotNull(message = "会员等级不能为空")
    @ApiModelProperty(value = "会员等级", required = true)
    private MemberGradeEnum memberLevel;

    /**
     * 报名结束时间(yyyy-MM-dd HH:mm)
     */
    @NotNull(message = "报名结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "报名结束时间(yyyy-MM-dd HH:mm)", required = true)
    private Date attentEndDate;

    /**
     * 宣传图片
     */
    @NotBlank(message = "宣传图片不能为空")
    @ApiModelProperty(value = "宣传图片", required = true)
    private String picture;
    
    /**
    * 首页图片
    */
    @NotBlank(message = "首页图片不能为空")
    @ApiModelProperty(value = "首页图片", required = true)
    private String homePicture;

    /**
     * 结束时间(yyyy-MM-dd HH:mm)
     */
    @NotNull(message = "结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm)", required = true)
    private Date endDate;

    /**
     * 商家可提交审核的商品数
     */
    @NotNull(message = "商家可提交审核的商品数不能为空")
    @Min(value = 1, message = "商家可提交审核的商品数必须大于0")
    @ApiModelProperty(value = "商家可提交审核的商品数", required = true)
    private Integer productValidCount;
    
    /**
     * 活动说明
     */
    @NotBlank(message = "活动说明不能为空")
    @Length(max = 500, message = "活动说明限制500字")
    @ApiModelProperty(value = "活动说明", required = true)
    private String remark;

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

    public MemberGradeEnum getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(MemberGradeEnum memberLevel) {
        this.memberLevel = memberLevel;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getProductValidCount() {
        return productValidCount;
    }

    public void setProductValidCount(Integer productValidCount) {
        this.productValidCount = productValidCount;
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