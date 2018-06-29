package com.lawu.eshop.activity.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 挖矿动力
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerInfoRecordDTO {

	@ApiModelProperty(value = "挖矿总动力")
	private int totalPower;

	@ApiModelProperty(value = "动力收支TOP10")
	private List<RichPowerRecordDTO> listPowerRecord;

	public int getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(int totalPower) {
		this.totalPower = totalPower;
	}

	public List<RichPowerRecordDTO> getListPowerRecord() {
		return listPowerRecord;
	}

	public void setListPowerRecord(List<RichPowerRecordDTO> listPowerRecord) {
		this.listPowerRecord = listPowerRecord;
	}

}
