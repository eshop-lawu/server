package com.lawu.eshop.statistics.param;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class AgentSelectAreaAdPointParam {

	@ApiParam (value = "市编号")
	private Integer cityId;
	
	@ApiParam (value = "开始时间")
	private String bdate;
	
	@ApiParam (value = "结束时间")
	private String edate;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

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
}
