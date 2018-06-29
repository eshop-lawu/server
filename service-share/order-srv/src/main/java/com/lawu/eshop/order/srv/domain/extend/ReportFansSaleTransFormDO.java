package com.lawu.eshop.order.srv.domain.extend;

import java.io.Serializable;

/**
 * 
 * @author Sunny
 * @date 2017年5月4日
 */
public class ReportFansSaleTransFormDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String isFans;
    
    private Long count;

	public String getIsFans() {
		return isFans;
	}

	public void setIsFans(String isFans) {
		this.isFans = isFans;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
    
}
