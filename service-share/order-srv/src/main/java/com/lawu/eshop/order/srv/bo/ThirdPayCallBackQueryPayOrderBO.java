package com.lawu.eshop.order.srv.bo;

import com.lawu.eshop.order.constants.PayOrderStatusEnum;

public class ThirdPayCallBackQueryPayOrderBO {
	private PayOrderStatusEnum payOrderStatusEnum;
	private double actualMoney;
	private String businessUserNum;
	private String orderNum;
	public double getActualMoney() {
		return actualMoney;
	}
	public void setActualMoney(double actualMoney) {
		this.actualMoney = actualMoney;
	}
	public String getBusinessUserNum() {
		return businessUserNum;
	}
	public void setBusinessUserNum(String businessUserNum) {
		this.businessUserNum = businessUserNum;
	}
	public PayOrderStatusEnum getPayOrderStatusEnum() {
		return payOrderStatusEnum;
	}
	public void setPayOrderStatusEnum(PayOrderStatusEnum payOrderStatusEnum) {
		this.payOrderStatusEnum = payOrderStatusEnum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
