package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;

import io.swagger.annotations.ApiModelProperty;

public class SeckillActivityDetailDTO {

	@ApiModelProperty(value = "活动id")
	private Long id;

	@ApiModelProperty(value = "活动名称")
	private String name;

	@ApiModelProperty(value = "活动图片")
	private String picture;

	@ApiModelProperty(value = "活动价格")
	private BigDecimal sellingPrice;

	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date startDate;
	
	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
	private Date endDate;

	@ApiModelProperty(value = "SILVER 白银会员 GOLD 黄金会员 PLATINUM 铂金会员 MASONRY 钻石会员 CROWN 皇冠会员")
	private MemberGradeEnum memberLevelEnum;

	@ApiModelProperty(value = "已参数商品数量")
	private int joinCount;

	@ApiModelProperty(value = "可参加数量")
	private int productValidCount;
	
	@ApiModelProperty(value = "报名倒计时")
	private Long countDown;
	
	@ApiModelProperty(value = "活动说明")
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public MemberGradeEnum getMemberLevelEnum() {
		return memberLevelEnum;
	}

	public void setMemberLevelEnum(MemberGradeEnum memberLevelEnum) {
		this.memberLevelEnum = memberLevelEnum;
	}

	public int getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}

	public int getProductValidCount() {
		return productValidCount;
	}

	public void setProductValidCount(int productValidCount) {
		this.productValidCount = productValidCount;
	}

	public Long getCountDown() {
		return countDown;
	}

	public void setCountDown(Long countDown) {
		this.countDown = countDown;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
