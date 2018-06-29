package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import com.lawu.eshop.ad.constants.RedPacketStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class RedPacketDTO {
	
	@ApiModelProperty(value = "积分(金额)")
	private BigDecimal point;
	
	@ApiModelProperty(value = "红包领取情况 RED_PACKET_SUCCESS 成功  RED_PACKET_FAIL 红包已被领完")
	private RedPacketStatusEnum statusEnum; 

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public RedPacketStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(RedPacketStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	
	

}
