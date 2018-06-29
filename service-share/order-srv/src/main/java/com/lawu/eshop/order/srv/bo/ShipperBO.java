package com.lawu.eshop.order.srv.bo;

/**
 * 单号识别-可能的快递公司BO
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ShipperBO {
	
	/**
	 * 快递公司编码
	 */
	private String shipperCode;
	
	/**
	 * 快递公司名称
	 */
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
