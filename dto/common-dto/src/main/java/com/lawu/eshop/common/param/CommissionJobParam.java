package com.lawu.eshop.common.param;

import java.math.BigDecimal;

public class CommissionJobParam {
	
	private Long bizId;	//广告提成、消费/营业额提成	
	private BigDecimal actureMoneyIn;//实际所得余额
	private BigDecimal actureLoveIn; //爱心账户
	private String userNum;			 //用户编号
	private boolean isLast;			 //是否第3级，第三级进积分
	
	private Byte typeVal;//业务类型
	private String typeName;//业务名称
	
	private Byte loveTypeVal;//爱心账户类型
	private String loveTypeName;//爱心账户类型描述
	
	private Long tempBidId;

	private String transactionDesc;
	
	public BigDecimal getActureMoneyIn() {
		return actureMoneyIn;
	}
	public void setActureMoneyIn(BigDecimal actureMoneyIn) {
		this.actureMoneyIn = actureMoneyIn;
	}
	public BigDecimal getActureLoveIn() {
		return actureLoveIn;
	}
	public void setActureLoveIn(BigDecimal actureLoveIn) {
		this.actureLoveIn = actureLoveIn;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public Long getBizId() {
		return bizId;
	}
	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}
	public Byte getTypeVal() {
		return typeVal;
	}
	public void setTypeVal(Byte typeVal) {
		this.typeVal = typeVal;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Byte getLoveTypeVal() {
		return loveTypeVal;
	}
	public void setLoveTypeVal(Byte loveTypeVal) {
		this.loveTypeVal = loveTypeVal;
	}
	public String getLoveTypeName() {
		return loveTypeName;
	}
	public void setLoveTypeName(String loveTypeName) {
		this.loveTypeName = loveTypeName;
	}
	public Long getTempBidId() {
		return tempBidId;
	}
	public void setTempBidId(Long tempBidId) {
		this.tempBidId = tempBidId;
	}

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
}
