package com.lawu.eshop.order.dto.foreign;

import java.util.List;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物流即时查询api对app暴露数据
 * 
 * @author Sunny
 * @date 2017/4/10
 */
@ApiModel
public class ExpressInquiriesDetailForeignDTO {
	
	/**
	 * 快递公司编码
	 */
	@ApiModelProperty("快递公司编码")
	private String shipperCode;
	
	/**
	 * 快递公司名称
	 */
	@ApiModelProperty("快递公司名称")
	private String shipperName;
	
	/**
	 * 物流状态
	 */
	@ApiModelProperty(value = "物流状态(NO_INFO-此单无物流信息|ON_THE_WAY-在途中|SIGN_IN-签收|PROBLEM_PIECES-问题件)", required = true)
	private ExpressInquiriesDetailStateEnum state;

	/**
	 * 物流轨迹
	 */
	@ApiModelProperty(value = "物流轨迹", required = true)
	private List<TraceDTO> traces;

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
