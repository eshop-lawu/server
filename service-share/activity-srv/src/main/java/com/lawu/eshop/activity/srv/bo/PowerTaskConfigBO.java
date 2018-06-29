package com.lawu.eshop.activity.srv.bo;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.activity.param.MerchantPowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class PowerTaskConfigBO {

	/**
	 * 用户任务生效时间
	 */
	private Date powerEffectiveTime;

	/**
	 * 用户任务
	 */
	private List<PowerTaskBaseConfigParam> tasks;

	/**
	 * 商家任务生效时间
	 */
	private Date merchantPowerEffectiveTime;

	/**
	 * 商家任务
	 */
	private List<MerchantPowerTaskBaseConfigParam> merchantTasks;

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

	public Date getMerchantPowerEffectiveTime() {
		return merchantPowerEffectiveTime;
	}

	public void setMerchantPowerEffectiveTime(Date merchantPowerEffectiveTime) {
		this.merchantPowerEffectiveTime = merchantPowerEffectiveTime;
	}

	public List<MerchantPowerTaskBaseConfigParam> getMerchantTasks() {
		return merchantTasks;
	}

	public void setMerchantTasks(List<MerchantPowerTaskBaseConfigParam> merchantTasks) {
		this.merchantTasks = merchantTasks;
	}
}
