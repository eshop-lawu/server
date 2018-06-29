package com.lawu.eshop.activity.srv.mapper.extend;

import com.lawu.eshop.activity.srv.domain.extend.RichPowerTaskRecordDOExtend;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public interface RichPowerTaskRecordDOMapperExtend {

	/**
	 * 修改动力任务完成记录
	 * 
	 * @param extend
	 */
	void updatePowerTaskRecord(RichPowerTaskRecordDOExtend extend);
}
