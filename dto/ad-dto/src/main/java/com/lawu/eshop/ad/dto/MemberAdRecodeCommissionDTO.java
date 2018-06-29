package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class MemberAdRecodeCommissionDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "用户编号")
	private String memberNum;

	@ApiModelProperty(value = "广告id")
	private Long adId;

	@ApiModelProperty(value = "积分")
	private BigDecimal point;

	@ApiModelProperty(value = "状态")
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
