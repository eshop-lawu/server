package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class PraisePointDTO {
	
	@JsonSerialize(using=KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "积分")
	private BigDecimal point;
	
	@ApiModelProperty(value = "是否抢到E咻 true 抢到 false 没有抢到")
	private Boolean isGetPoint = false;

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public Boolean getIsGetPoint() {
		return isGetPoint;
	}

	public void setIsGetPoint(Boolean isGetPoint) {
		this.isGetPoint = isGetPoint;
	}
	
	

}
