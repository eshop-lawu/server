package com.lawu.eshop.order.srv.utils.express.kdniao.bo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 快递鸟单号识别封装数据
 * 
 * @author jiangxinjun
 * @date 2017年9月5日
 */
public class ExpressRecognitionDetail {
	
	/**
	 * 电商用户ID
	 */
	@JSONField(name = "EBusinessID")
	private String eBusinessId;
	
	/**
	 * 物流单号
	 */
	@JSONField(name = "LogisticCode")
	private String logisticCode;
	
	/**
	 * 成功与否
	 */
	@JSONField(name = "Success")
	private Boolean success;
	
	/**
	 * 失败原因
	 */
	@JSONField(name = "Code")
	private Integer code;
	
	/**
	 * 可能得快递公司列表
	 * 一家或多家快递公司
	 * 排名靠前的命中率更高
	 */
	@JSONField(name = "Shippers")
	private List<Shipper> shippers;

	public String geteBusinessId() {
		return eBusinessId;
	}

	public void seteBusinessId(String eBusinessId) {
		this.eBusinessId = eBusinessId;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<Shipper> getShippers() {
		return shippers;
	}

	public void setShippers(List<Shipper> shippers) {
		this.shippers = shippers;
	}
}
