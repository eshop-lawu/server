package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PointDetailReportParam {
	
	@NotBlank(message = "date不能为空")
	private String date;
	
	@NotNull(message = "direction不能为空")
	private Byte direction;
	
	private Byte pointType;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Byte getDirection() {
		return direction;
	}

	public void setDirection(Byte direction) {
		this.direction = direction;
	}

	public Byte getPointType() {
		return pointType;
	}

	public void setPointType(Byte pointType) {
		this.pointType = pointType;
	}
	
}
