package com.lawu.eshop.order.srv.bo;

import java.util.List;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;

/**
 * 物流实时查询对外暴露数据
 * 
 * @author Sunny
 * @date 2017/4/10
 */
public class ExpressInquiriesDetailBO {
	
	/**
	 * 快递公司编码
	 */
	private String shipperCode;
	
	/**
	 * 物流运单号
	 */
	private String logisticCode;
	
	/**
	 * 物流状态
	 */
	private ExpressInquiriesDetailStateEnum state;
	
	/**
	 * 物流轨迹
	 */
	private List<TraceBO> traces;

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

	public ExpressInquiriesDetailStateEnum getState() {
		return state;
	}

	public void setState(ExpressInquiriesDetailStateEnum state) {
		this.state = state;
	}

	public List<TraceBO> getTraces() {
		return traces;
	}

	public void setTraces(List<TraceBO> traces) {
		this.traces = traces;
	}
	
	
}
