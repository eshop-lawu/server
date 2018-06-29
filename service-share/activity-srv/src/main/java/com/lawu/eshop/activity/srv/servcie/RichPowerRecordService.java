package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;

import com.lawu.eshop.activity.param.RichPowerRecordParam;

/**
 * 瑞奇岛挖矿动力收支记录
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public interface RichPowerRecordService {
	
	/**
	 * 动力收支记录
	 * @param param
	 */
	void saveRichPowerRecord(RichPowerRecordParam param);

	List<RichPowerRecordBO> getRichPowerInfoRecord(String userNum);

}
