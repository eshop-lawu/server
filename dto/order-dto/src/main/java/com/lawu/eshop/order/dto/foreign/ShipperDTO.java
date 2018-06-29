package com.lawu.eshop.order.dto.foreign;

/**
 * 单号识别-可能的快递公司DTO
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ShipperDTO {
	
	/**
	 * 快递公司编码
	 */
	private String shipperCode;
	
	
	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

}
