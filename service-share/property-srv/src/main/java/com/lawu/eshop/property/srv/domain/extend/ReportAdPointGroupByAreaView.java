package com.lawu.eshop.property.srv.domain.extend;

import java.math.BigDecimal;

public class ReportAdPointGroupByAreaView {

	
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
