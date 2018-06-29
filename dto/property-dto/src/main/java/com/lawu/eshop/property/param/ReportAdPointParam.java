package com.lawu.eshop.property.param;

import com.lawu.framework.core.page.AbstractPageParam;

public class ReportAdPointParam extends AbstractPageParam{
	
	private String bdate;
	
	private String edate;
	
	private int offset;

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
