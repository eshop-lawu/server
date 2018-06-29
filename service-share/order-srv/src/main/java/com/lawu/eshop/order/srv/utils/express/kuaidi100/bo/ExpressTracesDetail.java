package com.lawu.eshop.order.srv.utils.express.kuaidi100.bo;

import java.util.List;

/**
 * 快递100快递查询封装数据
 * 
 * @author jiangxinjun
 * @date 2017年9月21日
 */
public class ExpressTracesDetail extends BaseResult {
	
	/**
	 * 物流公司编号
	 */
	private String com;
	
	/**
	 * 物流单号
	 */
	private String nu;
	
	/**
	 * 快递当前状态
	 */
	private String state;
	
	/**
	 * 当前查询结果状态
	 */
	private String status;
	
	
	/**
	 * 无意义，请忽略
	 */
	private String condition;
	
	/**
	 * 无意义，请忽略
	 */
	private String ischeck;
	
	private String num;
	
	/**
	 * 出发、目的及当前城市
	 */
	private RouteInfo routeInfo;
	
	/**
	 * 物流轨迹
	 */
	private List<Trace> data;
	
	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public RouteInfo getRouteInfo() {
		return routeInfo;
	}

	public void setRouteInfo(RouteInfo routeInfo) {
		this.routeInfo = routeInfo;
	}

	public List<Trace> getData() {
		return data;
	}

	public void setData(List<Trace> data) {
		this.data = data;
	}
	
}
