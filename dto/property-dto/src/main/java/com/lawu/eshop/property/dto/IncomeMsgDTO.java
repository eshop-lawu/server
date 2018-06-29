package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.PayTypeEnum;
import io.swagger.annotations.ApiModelProperty;

public class IncomeMsgDTO {
	
	private String userNum;

	private BigDecimal money;

	private Byte type;//类型

	private int msgType;//1-余额|2-积分

	private PayTypeEnum payTypeEnum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public PayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}
}
