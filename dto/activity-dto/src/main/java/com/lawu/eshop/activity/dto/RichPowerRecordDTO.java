package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerRecordDTO {

	@ApiModelProperty(value = "标题")
	private String title;
	@ApiModelProperty(value = "来源")
	private PowerTaskTypeEnum typeEnum;
	@ApiModelProperty(value = "收入支出")
	private RichPowerRecordDirectionEnum directionEnum;
	@ApiModelProperty(value = "获取动力值")
	private int power;
	@ApiModelProperty(value = "领取时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date date;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PowerTaskTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(PowerTaskTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public RichPowerRecordDirectionEnum getDirectionEnum() {
		return directionEnum;
	}

	public void setDirectionEnum(RichPowerRecordDirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
