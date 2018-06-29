package com.lawu.eshop.property.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class BusinessDepositDetailDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "金额")
	private String amount;

	@ApiModelProperty(value = "状态['PAYING-待支付', 'VERIFY-已支付未核实', 'VERIFYD-已核实', 'APPLY_REFUND-退款申请', 'ACCPET_REFUND-退款已受理', 'REFUND_SUCCESS-退款成功', 'REFUND_FAILURE-退款失败']")
	private BusinessDepositStatusEnum businessDepositStatusEnum;

	@ApiModelProperty(value = "银行名称")
	private String bankName;
	
	@ApiModelProperty(value = "账户名称")
	private String accountName;
	
	@ApiModelProperty(value = "卡号后四位")
	private String cardNo;

	@ApiModelProperty(value = "失败原因")
	private String remark;

	@ApiModelProperty(value = "支付时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtPay;

	@ApiModelProperty(value = "核实时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtVerify;

	@ApiModelProperty(value = "退款时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtRefund;

	@ApiModelProperty(value = "受理时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtAccpet;

	@ApiModelProperty(value = "退款申请完成时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
