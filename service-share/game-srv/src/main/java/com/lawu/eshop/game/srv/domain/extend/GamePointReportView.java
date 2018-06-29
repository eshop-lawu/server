package com.lawu.eshop.game.srv.domain.extend;

import java.math.BigDecimal;

public class GamePointReportView {

	private Byte attendType;
	
	private BigDecimal attendSumPoint;
	
	private BigDecimal rewardSumPoint;

	public Byte getAttendType() {
		return attendType;
	}

	public void setAttendType(Byte attendType) {
		this.attendType = attendType;
	}

	public BigDecimal getAttendSumPoint() {
		return attendSumPoint;
	}

	public void setAttendSumPoint(BigDecimal attendSumPoint) {
		this.attendSumPoint = attendSumPoint;
	}

	public BigDecimal getRewardSumPoint() {
		return rewardSumPoint;
	}

	public void setRewardSumPoint(BigDecimal rewardSumPoint) {
		this.rewardSumPoint = rewardSumPoint;
	}
	
	
}
