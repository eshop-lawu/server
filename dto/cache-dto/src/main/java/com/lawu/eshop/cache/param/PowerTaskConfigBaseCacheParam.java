package com.lawu.eshop.cache.param;

import java.io.Serializable;
import java.util.Date;

import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class PowerTaskConfigBaseCacheParam implements Serializable{

	private static final long serialVersionUID = 7499903913022826888L;
	/**
	 * 任务类型
	 */
	private PowerTaskTypeEnum type;
	
	/**
	 * 任务数量
	 */
	private Integer taskCount;
	
	/**
	 * 动力值
	 */
	private Integer powerCount;
	
	/**
	 * 任务状态
	 */
	private PowerTaskStatusEnum status;
	
	/**
	 * 有效时间开始
	 */
	private Date beginTime;
	
	/**
	 * 有效时间结束
	 */
	private Date endTime;

	public PowerTaskTypeEnum getType() {
		return type;
	}

	public void setType(PowerTaskTypeEnum type) {
		this.type = type;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getPowerCount() {
		return powerCount;
	}

	public void setPowerCount(Integer powerCount) {
		this.powerCount = powerCount;
	}

	public PowerTaskStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PowerTaskStatusEnum status) {
		this.status = status;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
