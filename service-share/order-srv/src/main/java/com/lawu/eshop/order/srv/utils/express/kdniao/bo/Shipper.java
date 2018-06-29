package com.lawu.eshop.order.srv.utils.express.kdniao.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 快递鸟实单号识别封装数据
 * 可能的快递公司
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class Shipper {
	
	/**
	 * 快递公司编码
	 */
	@JSONField(name = "ShipperCode")
	private String shipperCode;
	
	/**
	 * 快递公司名称
	 */
	@JSONField(name = "ShipperName")
	private String shipperName;

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	
}
