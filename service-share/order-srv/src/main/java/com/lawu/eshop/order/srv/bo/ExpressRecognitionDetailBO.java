package com.lawu.eshop.order.srv.bo;

import java.util.List;

/**
 * 单号识别BO
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ExpressRecognitionDetailBO {
	
	/**
	 * 可能得快递公司列表
	 * 一家或多家快递公司
	 * 排名靠前的命中率更高
	 */
	private List<ShipperBO> shippers;
	
	public List<ShipperBO> getShippers() {
		return shippers;
	}

	public void setShippers(List<ShipperBO> shippers) {
		this.shippers = shippers;
	}
}
