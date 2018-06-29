package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class PlatformRedPacketDTO {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "金额")
	private BigDecimal money;

	@ApiModelProperty(value = "发送红包个数")
	private Integer sendCount;

	@ApiModelProperty(value = "启用状态")
	private PlatformRedPacketStatusEnum statusEnum;

	@ApiModelProperty(value = "操作人id")
	private Long auditorId;

	@ApiModelProperty(value = "禁用时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date gmtModified;

	@ApiModelProperty(value = "启用时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date gmtCreate;
	
	@ApiModelProperty(value = "操作人名称")
	private String auditorName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getSendCount() {
		return sendCount;
	}

	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public PlatformRedPacketStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(PlatformRedPacketStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	
	

}
