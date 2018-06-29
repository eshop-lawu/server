package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lawu.eshop.property.constants.BusinessDepositOperEnum;

import io.swagger.annotations.ApiParam;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDepositOperParam {

	@ApiParam(name = "id", required = true, value = "保证金ID")
	@NotBlank(message="id不能为空")
	private String id;
	
	@ApiParam(name = "businessId", required = true, value = "商家ID")
	@NotNull(message="businessId不能为空")
	private Long businessId;

	@ApiParam(name = "businessDepositStatusEnum", required = true, value = "操作类型(VERIFYD-核实、ACCPET_REFUND-受理退款、REFUND_SUCCESS-退款成功、REFUND_FAILURE-退款失败)")
	@NotNull(message="操作不能为空")
	private BusinessDepositOperEnum businessDepositOperEnum;

	@ApiParam(name = "failReason", value = "失败原因")
	private String failReason;

	@ApiParam(name = "userNum", required = true, value = "商家编号")
	@NotBlank(message="userNum不能为空")
	private String userNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public BusinessDepositOperEnum getBusinessDepositOperEnum() {
		return businessDepositOperEnum;
	}

	public void setBusinessDepositOperEnum(BusinessDepositOperEnum businessDepositOperEnum) {
		this.businessDepositOperEnum = businessDepositOperEnum;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

}
