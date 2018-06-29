package com.lawu.eshop.property.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import io.swagger.annotations.ApiModelProperty;

public class FreezeDTO {

	@ApiModelProperty(value = "冻结金额")
	private BigDecimal money;

	@ApiModelProperty(value = "订单号")
	private String orderNum;

	@ApiModelProperty(value = "冻结时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date gmtCreate;

	private Long bizId;

	@ApiModelProperty(value = "标题")
	private String title;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
