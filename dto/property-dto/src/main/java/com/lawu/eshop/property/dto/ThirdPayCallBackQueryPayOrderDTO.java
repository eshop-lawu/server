package com.lawu.eshop.property.dto;

public class ThirdPayCallBackQueryPayOrderDTO {
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
}
