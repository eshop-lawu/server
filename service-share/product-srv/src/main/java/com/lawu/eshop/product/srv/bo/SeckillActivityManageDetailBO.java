package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.product.constant.ActivityStatusEnum;

public class SeckillActivityManageDetailBO {

	private Long id;

	private String name;

	private String picture;

	private BigDecimal sellingPrice;
	
	private Date startDate;

	private Date endDate;

	private Long countDown;
	
	private ActivityStatusEnum activityStatusEnum;
	
	private List<SeckillActivityProductManageBO> list ;
	

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


	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Long getCountDown() {
		return countDown;
	}

	public void setCountDown(Long countDown) {
		this.countDown = countDown;
	}


	public List<SeckillActivityProductManageBO> getList() {
		return list;
	}

	public void setList(List<SeckillActivityProductManageBO> list) {
		this.list = list;
	}

	public ActivityStatusEnum getActivityStatusEnum() {
		return activityStatusEnum;
	}

	public void setActivityStatusEnum(ActivityStatusEnum activityStatusEnum) {
		this.activityStatusEnum = activityStatusEnum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	
}
