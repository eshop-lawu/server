package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichConfigBO {
	
	/**
	 * 瑞奇岛E钻配置信息json
	 */
	private String diamondConfig;
	/**
	 * 瑞奇岛E钻配置生效时间，第二天凌晨更新
	 */
	private Date diamondEffectiveTime;
	/**
	 * 动力任务配置信息json
	 */
	private String power_task_config;
	/**
	 * 动力任务配置生效时间，第二天凌晨更新
	 */
	private Date power_effective_time;
	/**
	 * 公告
	 */
	private String notice;
	public String getDiamondConfig() {
		return diamondConfig;
	}
	public void setDiamondConfig(String diamondConfig) {
		this.diamondConfig = diamondConfig;
	}
	public Date getDiamondEffectiveTime() {
		return diamondEffectiveTime;
	}
	public void setDiamondEffectiveTime(Date diamondEffectiveTime) {
		this.diamondEffectiveTime = diamondEffectiveTime;
	}
	public String getPower_task_config() {
		return power_task_config;
	}
	public void setPower_task_config(String power_task_config) {
		this.power_task_config = power_task_config;
	}
	public Date getPower_effective_time() {
		return power_effective_time;
	}
	public void setPower_effective_time(Date power_effective_time) {
		this.power_effective_time = power_effective_time;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
}
