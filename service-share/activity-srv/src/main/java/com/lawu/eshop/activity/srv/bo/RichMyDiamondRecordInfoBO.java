package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichMyDiamondRecordInfoBO {

	private BigDecimal totalDiamond;

	private List<RichMyDiamondRecordDetailBO> recordList;

	public BigDecimal getTotalDiamond() {
		return totalDiamond;
	}

	public void setTotalDiamond(BigDecimal totalDiamond) {
		this.totalDiamond = totalDiamond;
	}

	public List<RichMyDiamondRecordDetailBO> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<RichMyDiamondRecordDetailBO> recordList) {
		this.recordList = recordList;
	}

}
