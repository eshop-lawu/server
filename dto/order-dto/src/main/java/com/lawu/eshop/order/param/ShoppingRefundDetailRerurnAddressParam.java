package com.lawu.eshop.order.param;

import io.swagger.annotations.ApiModel;

/**
 * 商家填写物流信息
 * api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/4/12
 */
@ApiModel
public class ShoppingRefundDetailRerurnAddressParam {

	/**
	 * 收货人姓名
	 */
	private String consigneeName;

	/**
	 * 收货人地址
	 */
	private String consigneeAddress;

	/**
	 * 收货人手机号码
	 */
	private String consigneeMobile;

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
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
	
}
