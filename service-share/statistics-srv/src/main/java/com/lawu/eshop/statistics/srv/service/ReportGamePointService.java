package com.lawu.eshop.statistics.srv.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.statistics.constants.GameTypeEnum;
import com.lawu.eshop.statistics.param.GamePointReportParam;
import com.lawu.eshop.statistics.param.ReportGamePointQuery;
import com.lawu.eshop.statistics.srv.bo.ReportGamePointBO;
import com.lawu.eshop.statistics.srv.bo.ReportTotalGamePointBO;
import com.lawu.eshop.statistics.srv.domain.extend.ReportGamePointDailyDOView;

/**
 * 游戏积分统计
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
public interface ReportGamePointService {
	
	/**
	 * 获取最后一条统计日期
	 * @return
	 */
	Date getDaily();

	/**
	 * 保存日统计
	 * 
	 * @param list
	 */
	void saveReportGamePointDaily(List<GamePointReportParam> list);
	
	/**
	 * 报表查询
	 * @param query
	 * @return
	 */
	ReportGamePointBO selectReport(ReportGamePointQuery query);

	ReportGamePointDailyDOView getTotalGamePoint(GameTypeEnum param);
	
	/**
	 * 总统计
	 * @param typeEnum
	 * @return
	 */
	ReportTotalGamePointBO selectTotalReport(GameTypeEnum typeEnum);
	
}