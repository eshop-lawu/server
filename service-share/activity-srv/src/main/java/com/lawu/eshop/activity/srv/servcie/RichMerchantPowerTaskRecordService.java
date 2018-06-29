package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.MerchantRichPowerTaskDetailBO;

/**
 * 商家动力任务完成情况记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月7日
 */
public interface RichMerchantPowerTaskRecordService {
	
	/**
	 * 更新动力任务完成情况
	 * 
	 * @param param
	 */
	void saveRichPowerTaskRecord(RichMerchantPowerTaskRecordParam param);


    Integer getMerchantPowerTaskRecordListCount();

	void resetMerchantTaskRecord(Integer currentPage, Integer pageSize);

    List<MerchantRichPowerTaskDetailBO> getPowerTasks(String memberNum);
}
