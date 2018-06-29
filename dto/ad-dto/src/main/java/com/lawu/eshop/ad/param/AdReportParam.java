package com.lawu.eshop.ad.param;

import com.lawu.framework.core.page.AbstractPageParam;

public class AdReportParam extends AbstractPageParam {
	
	private String today;
	
	private int offset;

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
	
}
