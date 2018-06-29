package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;

public class ReportAdPointGroupByAreaBO {

	
	private BigDecimal totalPoint;
	
	private Integer areaId;

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}
