package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;

public class PointReportDOView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String bdate;
	
	private String edate;
	
	private Byte direction;
	
	private Byte pointType;
	

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

	public Byte getDirection() {
		return direction;
	}

	public void setDirection(Byte direction) {
		this.direction = direction;
	}

	public Byte getPointType() {
		return pointType;
	}

	public void setPointType(Byte pointType) {
		this.pointType = pointType;
	}

	
	
	

}
