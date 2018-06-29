package com.lawu.eshop.order.dto.foreign;

import com.lawu.eshop.order.constants.ExpressInquiriesDetailStateEnum;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingOrderExtendDetailDTO extends ShoppingOrderDetailDTO {
    
	/**
	 * 物流状态
	 */
	@ApiModelProperty(value = "物流状态(NO_INFO-此单无物流信息|ON_THE_WAY-在途中|SIGN_IN-签收|PROBLEM_PIECES-问题件)", required = true)
	private ExpressInquiriesDetailStateEnum state;
	
	/**
	 * 最新更新的物流记录
	 */
	@ApiModelProperty(value = "最新的物流记录", required = false)
	private TraceDTO trace;
	
	public ExpressInquiriesDetailStateEnum getState() {
		return state;
	}

	public void setState(ExpressInquiriesDetailStateEnum state) {
		this.state = state;
	}

	public TraceDTO getTrace() {
		return trace;
	}

	public void setTrace(TraceDTO trace) {
		this.trace = trace;
	}

}