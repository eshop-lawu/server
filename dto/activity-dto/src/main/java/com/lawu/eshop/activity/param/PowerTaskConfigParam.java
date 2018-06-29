package com.lawu.eshop.activity.param;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class PowerTaskConfigParam {
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date powerEffectiveTime;
	
	List<PowerTaskBaseConfigParam> tasks;
	

	public Date getPowerEffectiveTime() {
		return powerEffectiveTime;
	}

	public void setPowerEffectiveTime(Date powerEffectiveTime) {
		this.powerEffectiveTime = powerEffectiveTime;
	}

	public List<PowerTaskBaseConfigParam> getTasks() {
		return tasks;
	}

	public void setTasks(List<PowerTaskBaseConfigParam> tasks) {
		this.tasks = tasks;
	}


}
