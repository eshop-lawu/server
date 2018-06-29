package com.lawu.eshop.order.dto;

import java.util.List;

import com.lawu.eshop.order.dto.foreign.ShipperDTO;

/**
 * 单号识别DTO
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ExpressRecognitionDetailDTO {
	
	/**
	 * 可能的快递公司列表
	 */
	private List<ShipperDTO> shippers;
	
	public List<ShipperDTO> getShippers() {
		return shippers;
	}

	public void setShippers(List<ShipperDTO> shippers) {
		this.shippers = shippers;
	}
}
