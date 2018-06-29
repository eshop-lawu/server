package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;

public class WithdrawCashDOView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Byte userType;
	private Byte status;

	private Integer successNums;
	private BigDecimal successMoney;

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Integer getSuccessNums() {
		return successNums;
	}

	public void setSuccessNums(Integer successNums) {
		this.successNums = successNums;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
