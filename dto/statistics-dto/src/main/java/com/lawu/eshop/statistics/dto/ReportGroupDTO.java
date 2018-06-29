package com.lawu.eshop.statistics.dto;

/**
 * 报表分组DTO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class ReportGroupDTO<T> {
	
	/**
	 * x轴坐标数据
	 */
	private String x;
	
	/**
	 * y轴坐标数据
	 */
	private T y;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}
	
}