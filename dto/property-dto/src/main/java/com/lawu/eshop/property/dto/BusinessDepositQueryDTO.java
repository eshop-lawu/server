package com.lawu.eshop.property.dto;

import java.math.BigDecimal;

import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class BusinessDepositQueryDTO {

	@ApiModelProperty(value = "主键")
	private Long id;
	
	@ApiModelProperty(value = "商家ID")
	private Long businessId;

	@ApiModelProperty(value = "支付时间")
	private String gmtPay;

	@ApiModelProperty(value = "提现单号")
	private String depositNumber;

	@ApiModelProperty(value = "账户")
	private String BusinessAccount;

	@ApiModelProperty(value = "第三方订单号")
	private String thirdNumber;

	@ApiModelProperty(value = "负责人姓名")
	private String BusinessName;
	
	@ApiModelProperty(value = "商家编号")
	private String userNum;

	@ApiModelProperty(value = "金额")
	private BigDecimal amount;

	@ApiModelProperty(value = "支付方式")
	private String payMethod;

	@ApiModelProperty(value = "状态枚举")
	private BusinessDepositStatusEnum businessDepositStatusEnum;
	
	@ApiModelProperty(value = "状态")
	private String status;
	
	
	/////////////////////////////////////////////////////////////////////////////
	

	@ApiModelProperty(value = "银行账户")
	private String businessBankAccount;

	@ApiModelProperty(value = "银行卡号")
	private String bankNo;

	@ApiModelProperty(value = "银行名称")
	private String bankName;

	@ApiModelProperty(value = "支行")
	private String bankBranchName;

	@ApiModelProperty(value = "处理人姓名")
	private String auditUserName;

	@ApiModelProperty(value = "失败原因")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getGmtPay() {
		return gmtPay;
	}

	public void setGmtPay(String gmtPay) {
		this.gmtPay = gmtPay;
	}

	public String getDepositNumber() {
		return depositNumber;
	}

	public void setDepositNumber(String depositNumber) {
		this.depositNumber = depositNumber;
	}

	public String getBusinessAccount() {
		return BusinessAccount;
	}

	public void setBusinessAccount(String businessAccount) {
		BusinessAccount = businessAccount;
	}

	public String getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(String thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public BusinessDepositStatusEnum getBusinessDepositStatusEnum() {
		return businessDepositStatusEnum;
	}

	public void setBusinessDepositStatusEnum(BusinessDepositStatusEnum businessDepositStatusEnum) {
		this.businessDepositStatusEnum = businessDepositStatusEnum;
	}

	public String getBusinessBankAccount() {
		return businessBankAccount;
	}

	public void setBusinessBankAccount(String businessBankAccount) {
		this.businessBankAccount = businessBankAccount;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
