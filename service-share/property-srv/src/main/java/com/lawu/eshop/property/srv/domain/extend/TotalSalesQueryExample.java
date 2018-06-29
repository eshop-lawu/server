package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台总销量查询DO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class TotalSalesQueryExample implements Serializable {
   
	private static final long serialVersionUID = 1L;

	/**
	 * 开始时间
	 */
	private Date start;
	
	/**
	 * 结束时间
	 */
	private Date end;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}