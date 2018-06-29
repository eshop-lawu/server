package com.lawu.eshop.property.param;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 查询平台总销量参数
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class TotalSalesQueryParam {
	
	/**
	 * 查询时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
