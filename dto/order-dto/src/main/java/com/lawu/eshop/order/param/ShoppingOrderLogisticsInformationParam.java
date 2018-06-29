package com.lawu.eshop.order.param;

/**
 * 商家填写物流信息
 * api传递给order-srv参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
public class ShoppingOrderLogisticsInformationParam {

	/**
	 * 物流编号
	 */
	private String waybillNum;

	/**
	 * 快递公司id
	 */
	private Integer expressCompanyId;

	/**
	 * 快递公司编码
	 */
	private String expressCompanyCode;

	/**
	 * 快递公司名称
	 */
	private String expressCompanyName;
	
	/**
	 * 是否需要物流
	 */
	private Boolean isNeedsLogistics;

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

	public Boolean getIsNeedsLogistics() {
		return isNeedsLogistics;
	}

	public void setIsNeedsLogistics(Boolean isNeedsLogistics) {
		this.isNeedsLogistics = isNeedsLogistics;
	}

}
