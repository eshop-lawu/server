package com.lawu.eshop.activity.srv.mapper.extend;

import com.lawu.eshop.activity.srv.domain.extend.RichMerchantPowerTaskRecordDOExtend;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public interface RichMerchantPowerTaskRecordDOMapperExtend {

	/**
	 * 修改动力任务完成记录
	 * 
	 * @param extend
	 */
	void updatePowerTaskRecord(RichMerchantPowerTaskRecordDOExtend extend);
}
