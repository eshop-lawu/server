package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.RichPowerTaskDetailBO;

/**
 * 瑞奇岛动力任务完成情况记录
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public interface RichPowerTaskRecordService {
	
	/**
	 * 更新动力任务完成情况
	 * 
	 * @param param
	 */
	void saveRichPowerTaskRecord(RichPowerTaskRecordParam param);
	
	

	/**
	 * 获取动力任务
	 * 
	 * @param memberNum
	 * @return
	 */
	List<RichPowerTaskDetailBO> getPowerTasks(String memberNum);

    int getPowerTaskRecordListCount();

	void resetTaskRecord(Integer currentPage, Integer pageSize);
}
