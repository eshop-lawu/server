package com.lawu.eshop.order.srv.utils.express.kdniao.bo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 快递鸟实时查询封装数据
 * 物流踪迹
 * 
 * @author Sunny
 * @date 2017/4/10
 */
public class Trace {
	
	/**
	 * 时间
	 */
	@JSONField(name = "AcceptTime")
	private String acceptTime;
	
	/**
	 * 描述
	 */
	@JSONField(name = "AcceptStation")
	private String acceptStation;
	
	/**
	 * 备注
	 */
	@JSONField(name = "Remark")
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
