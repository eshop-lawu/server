package com.lawu.eshop.ad.srv.bo;

import java.math.BigDecimal;

public class ClickPointBO {
	
	private BigDecimal point;
	
	private boolean isOverClick = false;
	
	private Boolean isClick = false;

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public boolean isOverClick() {
		return isOverClick;
	}

	public void setOverClick(boolean isOverClick) {
		this.isOverClick = isOverClick;
	}

	public Boolean getIsClick() {
		return isClick;
	}

	public void setIsClick(Boolean isClick) {
		this.isClick = isClick;
	}
	

}
