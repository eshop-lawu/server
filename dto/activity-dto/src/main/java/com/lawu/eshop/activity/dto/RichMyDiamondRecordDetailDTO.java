package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;

import io.swagger.annotations.ApiModelProperty;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichMyDiamondRecordDetailDTO {
	
	@ApiModelProperty(value="标题")
	private String title;
	
	@ApiModelProperty(value="类型E钻、幸运钻")
	private RichDiamondRecordTypeEnum typeEnum;
	
	@ApiModelProperty(value="来源")
	private RichDiamondRecordSourceEnum sourceEnum;
	
	@ApiModelProperty(value="收入、支出")
	private RichPowerRecordDirectionEnum directionEnum;
	
	@ApiModelProperty(value="数量")
	private BigDecimal amount;
	
	@ApiModelProperty(value="领取时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date takeTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RichDiamondRecordTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(RichDiamondRecordTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public RichDiamondRecordSourceEnum getSourceEnum() {
		return sourceEnum;
	}

	public void setSourceEnum(RichDiamondRecordSourceEnum sourceEnum) {
		this.sourceEnum = sourceEnum;
	}

	public RichPowerRecordDirectionEnum getDirectionEnum() {
		return directionEnum;
	}

	public void setDirectionEnum(RichPowerRecordDirectionEnum directionEnum) {
		this.directionEnum = directionEnum;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
	
	
	
}
