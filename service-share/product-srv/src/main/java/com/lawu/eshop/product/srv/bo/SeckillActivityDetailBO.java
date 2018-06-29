package com.lawu.eshop.product.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.MemberGradeEnum;

public class SeckillActivityDetailBO {

	private Long id;

	private String name;

	private String picture;

	private BigDecimal sellingPrice;

	private Date startDate;
	
	private Date endDate;

	private Date attentEndDate;

	private MemberGradeEnum memberLevelEnum;

	private int joinCount;

	private int productValidCount;
	
	private String countDown;
	
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

	public String getCountDown() {
		return countDown;
	}

	public void setCountDown(String countDown) {
		this.countDown = countDown;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
