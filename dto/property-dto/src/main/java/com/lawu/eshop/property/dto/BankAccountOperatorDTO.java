package com.lawu.eshop.property.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class BankAccountOperatorDTO {
	
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String accountName;
	
	@ApiModelProperty(value = "卡号", required = true)
	private String accountNumber;
	
	@ApiModelProperty(value = "支行", required = true)
	private String subBranchName;
	
	@ApiModelProperty(value = "银行名称", required = true)
	private String bankName;

	@ApiModelProperty(value = "所属银行主键", required = true)
	private Integer bankId;
	
	@ApiModelProperty(value = "操作备注")
	private String remark;
	
	@ApiModelProperty(value = "操作人ID")
	private Integer auditorId;
	
	@ApiModelProperty(value = "操作人时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date auditorTime;
	
	@ApiModelProperty(value = "操作人名称")
	private String auditorName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public Date getAuditorTime() {
		return auditorTime;
	}

	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	

}
