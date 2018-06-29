package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

public class PointGetDetailBO {

	private Long memberId;
	
	private BigDecimal point;
	
	private Date gmtCreate;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
	
}
