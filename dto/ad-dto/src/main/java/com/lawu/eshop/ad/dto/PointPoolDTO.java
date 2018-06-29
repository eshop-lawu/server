package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class PointPoolDTO {
	
	@ApiModelProperty(value = "主键")
	private Long memberId;
	
	@ApiModelProperty(value = "积分")
	private BigDecimal point;

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
	
	

}
