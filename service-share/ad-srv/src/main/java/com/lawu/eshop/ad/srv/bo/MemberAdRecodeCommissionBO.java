package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;

public class MemberAdRecodeCommissionBO {

	private Long id;

	private String memberNum;

	private Long adId;

	private BigDecimal point;

	private Byte status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
