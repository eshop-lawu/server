package com.lawu.eshop.activity.srv.bo;

import java.util.List;

/**
 * 挖矿动力
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichPowerInfoRecordBO {

	private int totalPower;
	private List<RichPowerRecordBO> listRichPowerRecord;

	public int getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(int totalPower) {
		this.totalPower = totalPower;
	}

	public List<RichPowerRecordBO> getListRichPowerRecord() {
		return listRichPowerRecord;
	}

	public void setListRichPowerRecord(List<RichPowerRecordBO> listRichPowerRecord) {
		this.listRichPowerRecord = listRichPowerRecord;
	}

}
