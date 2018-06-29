package com.lawu.eshop.ad.srv.domain.extend;

import java.util.Date;

public class AdDOView {

	private Byte type;

	private Byte topType;
	
	private Date beginAfterTime;

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}


	public Byte getTopType() {
		return topType;
	}

	public void setTopType(Byte topType) {
		this.topType = topType;
	}

	public Date getBeginAfterTime() {
		return beginAfterTime;
	}

	public void setBeginAfterTime(Date beginAfterTime) {
		this.beginAfterTime = beginAfterTime;
	}


	

	
}
