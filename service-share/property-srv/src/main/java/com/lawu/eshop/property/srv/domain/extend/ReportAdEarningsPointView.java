package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;
import java.util.List;

public class ReportAdEarningsPointView {
	
	private List<Long> bizIds;
	
	private byte pointType;
	
	private byte loveType;
	
	private BigDecimal userTotalPoint;

	private BigDecimal loveTotalPoint;
	

	public List<Long> getBizIds() {
		return bizIds;
	}

	public void setBizIds(List<Long> bizIds) {
		this.bizIds = bizIds;
	}

	public byte getPointType() {
		return pointType;
	}

	public void setPointType(byte pointType) {
		this.pointType = pointType;
	}

	public BigDecimal getUserTotalPoint() {
		return userTotalPoint;
	}

	public void setUserTotalPoint(BigDecimal userTotalPoint) {
		this.userTotalPoint = userTotalPoint;
	}

	public BigDecimal getLoveTotalPoint() {
		return loveTotalPoint;
	}

	public void setLoveTotalPoint(BigDecimal loveTotalPoint) {
		this.loveTotalPoint = loveTotalPoint;
	}

	public byte getLoveType() {
		return loveType;
	}

	public void setLoveType(byte loveType) {
		this.loveType = loveType;
	}
	
	
	
}
