package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class SeckillActivityJoinDTO {
	
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
