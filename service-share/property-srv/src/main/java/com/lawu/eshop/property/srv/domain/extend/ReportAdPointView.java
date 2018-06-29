package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class ReportAdPointView {
	
	private Long bizId;
	
	private byte pointType;
	
	private byte loveType;
	
	private BigDecimal userTotalPoint;

	private BigDecimal loveTotalPoint;
	

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
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
