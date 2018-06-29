package com.lawu.eshop.order.param;

import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;

/**
 * 运营平台修改订单资料参数 api暴露给运营平台参数
 * 
 * @author Sunny
 * @date 2017/4/15
 */
public class ShoppingOrderUpdateInfomationParam {

	/**
	 * 订单状态
	 */
	private ShoppingOrderStatusEnum orderStatus;
	
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
	
	/**
	 * 运单编号
	 */
	private String waybillNum;

	/**
	 * 快递公司id
	 */
	private Integer expressCompanyId;
	
	/**
	 * 快递公司代码
	 */
	private String expressCompanyCode;
	
	/**
	 * 快递公司名称
	 */
	private String expressCompanyName;

	public ShoppingOrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(ShoppingOrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

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

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public Integer getExpressCompanyId() {
		return expressCompanyId;
	}

	public void setExpressCompanyId(Integer expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	public String getExpressCompanyCode() {
		return expressCompanyCode;
	}

	public void setExpressCompanyCode(String expressCompanyCode) {
		this.expressCompanyCode = expressCompanyCode;
	}

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}
}
