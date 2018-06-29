package com.lawu.eshop.order.dto.foreign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物流实时查询对外暴露数据
 * 物流踪迹
 * 
 * @author Sunny
 * @date 2017/4/10
 */
@ApiModel
public class TraceDTO {
	
	/**
	 * 时间
	 */
	@ApiModelProperty(value = "时间", required = true)
	private String acceptTime;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", required = true)
	private String acceptStation;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getAcceptStation() {
		return acceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		this.acceptStation = acceptStation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
