package com.lawu.eshop.ad.param;

import java.math.BigDecimal;
import java.util.Date;

public class UserPacketRefundParam {

	private String userNum;
	private Long redId;
	private BigDecimal refundMoney;
	private String thirdNo;
	private Byte payType;
	private Date gmtModified;
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public Long getRedId() {
		return redId;
	}
	public void setRedId(Long redId) {
		this.redId = redId;
	}
	public BigDecimal getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}
	public String getThirdNo() {
		return thirdNo;
	}
	public void setThirdNo(String thirdNo) {
		this.thirdNo = thirdNo;
	}
	public Byte getPayType() {
		return payType;
	}
	public void setPayType(Byte payType) {
		this.payType = payType;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
