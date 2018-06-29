package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerRecordBO {

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 来源
	 */
	private PowerTaskTypeEnum typeEnum;
	/**
	 * 收入支出
	 */
	private RichPowerRecordDirectionEnum directionEnum;
	/**
	 * 获取动力值
	 */
	private int power;
	
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
