package com.lawu.eshop.order.srv.utils.express.kdniao.bo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 快递鸟实时查询封装数据
 * 
 * @author Sunny
 * @date 2017/4/10
 */
public class ExpressInquiriesDetail {
	
	/**
	 * 电商用户ID
	 */
	@JSONField(name = "EBusinessID")
	private String eBusinessId;
	
	/**
	 * 订单编号
	 */
	@JSONField(name = "OrderCode")
	private String orderCode;
	
	/**
	 * 快递公司编码
	 */
	@JSONField(name = "ShipperCode")
	private String shipperCode;
	
	/**
	 * 物流运单号
	 */
	@JSONField(name = "LogisticCode")
	private String logisticCode;
	
	/**
	 * 用户标识
	 */
	@JSONField(name = "CallBack")
	private String callBack;
	
	/**
	 * 成功与否
	 */
	@JSONField(name = "Success")
	private Boolean success;
	
	/**
	 * 失败原因
	 */
	@JSONField(name = "Reason")
	private String reason;
	
	/**
	 * 物流状态
	 */
	@JSONField(name = "State")
	private String state;
	
	/**
	 * 物流轨迹
	 */
	@JSONField(name = "Traces")
	private List<Trace> traces;

	public String geteBusinessId() {
		return eBusinessId;
	}

	public void seteBusinessId(String eBusinessId) {
		this.eBusinessId = eBusinessId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getLogisticCode() {
		return logisticCode;
	}

	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}

	public String getCallBack() {
		return callBack;
	}

	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Trace> getTraces() {
		return traces;
	}

	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}
}
