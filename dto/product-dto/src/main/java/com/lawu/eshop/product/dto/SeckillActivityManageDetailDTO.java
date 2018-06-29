package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.product.constant.ActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class SeckillActivityManageDetailDTO {

	@ApiModelProperty(value = "活动id")
	private Long id;

	@ApiModelProperty(value = "活动名称")
	private String name;

	@ApiModelProperty(value = "活动图片")
	private String picture;

	@ApiModelProperty(value = "活动价格")
	private BigDecimal sellingPrice;

	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date endDate;
	
	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date startDate;
	
	@ApiModelProperty(value = "倒计时")
	private Long countDown;
	
	@ApiModelProperty(value = "UNPUBLISHED 未发布  NOT_STARTED 未开始 PROCESSING 进行中  END 已结束")
	private ActivityStatusEnum activityStatusEnum;
	
	@ApiModelProperty(value = "报名商品集合")
	private List<SeckillActivityProductManageDTO> list ;

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

	public ActivityStatusEnum getActivityStatusEnum() {
		return activityStatusEnum;
	}

	public void setActivityStatusEnum(ActivityStatusEnum activityStatusEnum) {
		this.activityStatusEnum = activityStatusEnum;
	}

	public List<SeckillActivityProductManageDTO> getList() {
		return list;
	}

	public void setList(List<SeckillActivityProductManageDTO> list) {
		this.list = list;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	
	
	

}
