package com.lawu.eshop.order.srv.utils.express.kuaidi100.bo;

/**
 * 快递100快递查询封装数据
 * 
 * @author jiangxinjun
 * @date 2017年9月21日
 */
public class RouteInfo {
	
	/**
	 * 出发城市
	 */
	private  RouteInfoCity from;
	
	/**
	 * 出发城市
	 */
	private  RouteInfoCity cur;
	
	/**
	 * 出发城市
	 */
	private  RouteInfoCity to;

	public RouteInfoCity getFrom() {
		return from;
	}

	public void setFrom(RouteInfoCity from) {
		this.from = from;
	}

	public RouteInfoCity getCur() {
		return cur;
	}

	public void setCur(RouteInfoCity cur) {
		this.cur = cur;
	}

	public RouteInfoCity getTo() {
		return to;
	}

	public void setTo(RouteInfoCity to) {
		this.to = to;
	}
	
}
