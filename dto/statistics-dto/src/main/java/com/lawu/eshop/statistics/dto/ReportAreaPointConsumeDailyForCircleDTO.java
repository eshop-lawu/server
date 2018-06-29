package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class ReportAreaPointConsumeDailyForCircleDTO {

	@ApiModelProperty(value = "会员消费总积分")
	private BigDecimal memberPoint;
	
	@ApiModelProperty(value = "商家消费总积分")
	private BigDecimal merchantPoint;
	
	@ApiModelProperty(value = "会员充值总积分")
	private BigDecimal memberRechargePoint;
	
	@ApiModelProperty(value = "商家充值总积分")
	private BigDecimal merchantRechargePoint;
	
	@ApiModelProperty(value = "总积分")
	private BigDecimal totalPoint;
	
	@ApiModelProperty(value = "充值总积分")
	private BigDecimal totalRechargePoint;

	public BigDecimal getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(BigDecimal memberPoint) {
		this.memberPoint = memberPoint;
	}

	public BigDecimal getMerchantPoint() {
		return merchantPoint;
	}

	public void setMerchantPoint(BigDecimal merchantPoint) {
		this.merchantPoint = merchantPoint;
	}

	public BigDecimal getMemberRechargePoint() {
		return memberRechargePoint;
	}

	public void setMemberRechargePoint(BigDecimal memberRechargePoint) {
		this.memberRechargePoint = memberRechargePoint;
	}

	public BigDecimal getMerchantRechargePoint() {
		return merchantRechargePoint;
	}

	public void setMerchantRechargePoint(BigDecimal merchantRechargePoint) {
		this.merchantRechargePoint = merchantRechargePoint;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

	public BigDecimal getTotalRechargePoint() {
		return totalRechargePoint;
	}

	public void setTotalRechargePoint(BigDecimal totalRechargePoint) {
		this.totalRechargePoint = totalRechargePoint;
	}
}
