package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

public class FreezeBO {

	private BigDecimal money;

	private String orderNum;

	private Date gmtCreate;

	private Long bizId;

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
}
