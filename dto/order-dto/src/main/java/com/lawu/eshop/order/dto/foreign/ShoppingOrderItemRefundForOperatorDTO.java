package com.lawu.eshop.order.dto.foreign;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ShoppingOrderItemRefundForOperatorDTO extends ShoppingOrderItemRefundForMerchantDTO {
	
	/**
	 * 订单编号
	 */
	@ApiModelProperty(value = "订单编号", required = true)
	private String orderNum;
	
	/**
	 * 收货人地址
	 */
	@ApiModelProperty(value = "收货人地址", required = true)
	private String consigneeAddress;

	/**
	 * 收货人手机号码
	 */
	@ApiModelProperty(value = "收货人手机号码", required = true)
	private String consigneeMobile;
	
    /**
    * 申请平台介入时间
    */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "申请平台介入时间", required = true)
    private Date gmtIntervention;

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public Date getGmtIntervention() {
		return gmtIntervention;
	}

	public void setGmtIntervention(Date gmtIntervention) {
		this.gmtIntervention = gmtIntervention;
	}
}