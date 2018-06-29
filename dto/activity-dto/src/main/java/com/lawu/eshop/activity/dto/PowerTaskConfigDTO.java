package com.lawu.eshop.activity.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.param.MerchantPowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class PowerTaskConfigDTO {
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date powerEffectiveTime;
	
	List<PowerTaskBaseConfigParam> tasks;
	
	private Boolean isOpenInviteTime;

	/**
	 * 商家任务生效时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date merchantPowerEffectiveTime;
	/**
	 * 商家任务
	 */
	private List<MerchantPowerTaskBaseConfigParam> merchantTasks;

	private Boolean isMerchantOpenInviteTime;

	private Boolean isActiveTime;

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

	public Boolean getIsOpenInviteTime() {
		return isOpenInviteTime;
	}

	public void setIsOpenInviteTime(Boolean isOpenInviteTime) {
		this.isOpenInviteTime = isOpenInviteTime;
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

	public Boolean getIsMerchantOpenInviteTime() {
		return isMerchantOpenInviteTime;
	}

	public void setIsMerchantOpenInviteTime(Boolean merchantOpenInviteTime) {
		isMerchantOpenInviteTime = merchantOpenInviteTime;
	}

	public Boolean getIsActiveTime() {
		return isActiveTime;
	}

	public void setIsActiveTime(Boolean activeTime) {
		isActiveTime = activeTime;
	}
}
