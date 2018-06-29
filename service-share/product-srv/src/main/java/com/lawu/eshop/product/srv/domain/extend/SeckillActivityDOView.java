package com.lawu.eshop.product.srv.domain.extend;

import java.math.BigDecimal;
import java.util.Date;

public class SeckillActivityDOView {
	
	private Long id;
	
	private String name;
	
	private String picture;
	
	private BigDecimal sellingPrice;
	
	private Date startDate;
	
	private Date endDate;
	
	private Byte activityStatus;

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

	public Byte getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Byte activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
