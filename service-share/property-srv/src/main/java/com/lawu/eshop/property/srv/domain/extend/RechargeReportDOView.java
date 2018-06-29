package com.lawu.eshop.property.srv.domain.extend;

import java.io.Serializable;

public class RechargeReportDOView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String bdate;
	
	private String edate;
	
	private Byte rechargeType;
	
	private Byte status;
	

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

	public Byte getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(Byte rechargeType) {
		this.rechargeType = rechargeType;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	

}
