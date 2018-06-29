package com.lawu.eshop.order.param;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询物流轨迹srv暴露给api参数
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ExpressQueryParam {

	/**
	 * 快递单号
	 */
	@NotBlank(message = "快递单号不能为空")
	private String expNo;
	
	/**
	 * 快递鸟快递公司编号
	 */
	private String expCode;
	
	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}

	public String getExpCode() {
		return expCode;
	}

	public void setExpCode(String expCode) {
		this.expCode = expCode;
	}

}
