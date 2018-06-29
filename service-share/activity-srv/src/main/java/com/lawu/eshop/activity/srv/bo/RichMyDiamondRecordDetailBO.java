package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;

/**
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichMyDiamondRecordDetailBO {

	private String title;
	private RichDiamondRecordTypeEnum typeEnum;
	private RichDiamondRecordSourceEnum sourceEnum;
	private RichPowerRecordDirectionEnum directionEnum;
	private BigDecimal amount;
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
