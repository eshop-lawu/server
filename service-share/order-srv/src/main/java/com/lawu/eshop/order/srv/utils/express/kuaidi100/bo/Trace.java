package com.lawu.eshop.order.srv.utils.express.kuaidi100.bo;

/**
 * 快递100快递查询封装数据
 * 物流踪迹
 * 
 * @author jiangxinjun
 * @date 2017年9月21日
 */
public class Trace {
	
	/**
	 * 每条跟踪信息的时间
	 */
	private String time;
	
	/**
	 * 每条跟综信息的描述
	 */
	private String context;
	
	/**
	 * 格式化后时间
	 */
	private String ftime;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFtime() {
		return ftime;
	}

	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	
}
