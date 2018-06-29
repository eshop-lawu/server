package com.lawu.eshop.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class MerchantInviterDTO {
	
	@ApiModelProperty(value = "商家账号", required = true)
	private String account;
	
	@ApiModelProperty(value = "店铺名称", required = true)
	private String name;
	
	@ApiModelProperty(value = "责任人姓名", required = true)
	private String principalName;
	 
	@ApiModelProperty(value = "责任人电话", required = true)
	private String principalMobile;
	
	@ApiModelProperty(value = "区域", required = true)
	private String regionName;
	
	@ApiModelProperty(value = "详细地址", required = true)
	private String address;
	
	@ApiModelProperty(value = "创建时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date gmtCreate;
	
	@ApiModelProperty(value = "图片", required = true)
	private String path;
	
    @ApiModelProperty(name = "statusEnum", value = "门店状态:MERCHANT_STATUS_UNCHECK:未审核,MERCHANT_STATUS_CHECKED:审核通过,MERCHANT_STATUS_CHECK_FAILED:审核不通过,MERCHANT_STATUS_NOT_MONEY:未提交保证金,MERCHANT_STATUS_GIVE_MONEY_CHECK:已提交保证金待财务核实,MERCHANT_STATUS_GIVE_MONEY_CHECK_FAILED:财务审核不通过")
	private MerchantStatusEnum statusEnum;
	
	@ApiModelProperty(value = "邀请人数", required = true)
	private Integer inviterCount;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getPrincipalMobile() {
		return principalMobile;
	}

	public void setPrincipalMobile(String principalMobile) {
		this.principalMobile = principalMobile;
	}
	

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public MerchantStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(MerchantStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getInviterCount() {
		return inviterCount;
	}

	public void setInviterCount(Integer inviterCount) {
		this.inviterCount = inviterCount;
	}

	
	

}
