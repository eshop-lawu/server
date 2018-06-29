package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class ClickAdPointDTO {
	
	@JsonSerialize(using=KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "今日累计积分")
	private BigDecimal addPoint;
	
	@JsonSerialize(using=KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "广告积分")
	private BigDecimal point;

	public BigDecimal getAddPoint() {
		return addPoint;
	}

	public void setAddPoint(BigDecimal addPoint) {
		this.addPoint = addPoint;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	

	
	

}
