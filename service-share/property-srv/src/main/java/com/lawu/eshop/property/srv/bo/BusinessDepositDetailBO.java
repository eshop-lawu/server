package com.lawu.eshop.property.srv.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import io.swagger.annotations.ApiModelProperty;

public class BusinessDepositDetailBO {

	private Long id;

	private String amount;

	private BusinessDepositStatusEnum businessDepositStatusEnum;

	private String bankName;
	
	private String accountName;
	
	private String cardNo;

	private String remark;

	private Date gmtPay;

	private Date gmtVerify;

	private Date gmtRefund;

	private Date gmtAccpet;

	private Date gmtResult;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public BusinessDepositStatusEnum getBusinessDepositStatusEnum() {
		return businessDepositStatusEnum;
	}

	public void setBusinessDepositStatusEnum(BusinessDepositStatusEnum businessDepositStatusEnum) {
		this.businessDepositStatusEnum = businessDepositStatusEnum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getGmtPay() {
		return gmtPay;
	}

	public void setGmtPay(Date gmtPay) {
		this.gmtPay = gmtPay;
	}

	public Date getGmtVerify() {
		return gmtVerify;
	}

	public void setGmtVerify(Date gmtVerify) {
		this.gmtVerify = gmtVerify;
	}

	public Date getGmtRefund() {
		return gmtRefund;
	}

	public void setGmtRefund(Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}

	public Date getGmtAccpet() {
		return gmtAccpet;
	}

	public void setGmtAccpet(Date gmtAccpet) {
		this.gmtAccpet = gmtAccpet;
	}

	public Date getGmtResult() {
		return gmtResult;
	}

	public void setGmtResult(Date gmtResult) {
		this.gmtResult = gmtResult;
	}
}
