package com.lawu.eshop.order.srv.bo;

/**
 * 物流实时查询对外暴露数据
 * 物流踪迹
 * 
 * @author Sunny
 * @date 2017/4/10
 */
public class TraceBO {
	
	/**
	 * 时间
	 */
	private String acceptTime;
	
	/**
	 * 描述
	 */
	private String acceptStation;
	
	/**
	 * 备注
	 */
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
