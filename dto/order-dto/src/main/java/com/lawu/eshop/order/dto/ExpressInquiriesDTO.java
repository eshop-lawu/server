package com.lawu.eshop.order.dto;

import java.util.List;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;
import com.lawu.eshop.order.dto.foreign.TraceDTO;

/**
 * 查询物流轨迹对api暴露数据
 * 
 * @author jiangxinjun
 * @date 2017年9月22日
 */
public class ExpressInquiriesDTO {
	
	/**
	 * 快递公司编码
	 */
	private String shipperCode;
	
	/**
	 * 物流状态
	 */
	private ExpressInquiriesDetailStateEnum state;

	/**
	 * 物流轨迹
	 */
	private List<TraceDTO> traces;

	public String getShipperCode() {
		return shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public ExpressInquiriesDetailStateEnum getState() {
		return state;
	}

	public void setState(ExpressInquiriesDetailStateEnum state) {
		this.state = state;
	}

	public List<TraceDTO> getTraces() {
		return traces;
	}

	public void setTraces(List<TraceDTO> traces) {
		this.traces = traces;
	}

}
