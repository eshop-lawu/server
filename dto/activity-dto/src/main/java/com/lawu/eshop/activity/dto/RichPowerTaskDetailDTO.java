package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

import io.swagger.annotations.ApiModelProperty;

public class RichPowerTaskDetailDTO {
	
	/**
	 * 任务类型
	 */
	@ApiModelProperty(value = "任务类型")
	private PowerTaskTypeEnum type;
	
	/**
	 * 任务数量
	 */
	@ApiModelProperty(value = "任务数量")
	private Integer taskCount;
	
	/**
	 * 动力值
	 */
	@ApiModelProperty(value = "动力值")
	private Integer powerCount;
	
	
	/**
	 * 是否完成
	 */
	@ApiModelProperty(value = "是否完成")
	private Boolean isFinish;
	
	
	/**
	 * 有效时间开始
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date beginTime;
	
	/**
	 * 有效时间结束
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endTime;
	
	@ApiModelProperty(value = "ENABLED 启用 DISABLED 禁用")
	private PowerTaskStatusEnum status;
	
	
	/**
	 * 剩余次数
	 */
	@ApiModelProperty(value = "剩余次数")
	private int residueCount;


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


	public Boolean getIsFinish() {
		return isFinish;
	}


	public void setIsFinish(Boolean isFinish) {
		this.isFinish = isFinish;
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


	public PowerTaskStatusEnum getStatus() {
		return status;
	}


	public void setStatus(PowerTaskStatusEnum status) {
		this.status = status;
	}


	public int getResidueCount() {
		return residueCount;
	}


	public void setResidueCount(int residueCount) {
		this.residueCount = residueCount;
	}
	
	

}
