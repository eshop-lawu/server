package com.lawu.eshop.statistics.param;

import com.lawu.eshop.statistics.constants.GameTypeEnum;

public class ReportGamePointQuery {
	
	private String bdate;
	private String edate;
	private GameTypeEnum typeEnum;
	
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
	public GameTypeEnum getTypeEnum() {
		return typeEnum;
	}
	public void setTypeEnum(GameTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}
	
	

}
