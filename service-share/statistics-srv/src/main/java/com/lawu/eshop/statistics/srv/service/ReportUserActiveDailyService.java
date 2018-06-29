package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveAreaMonthBO;
import com.lawu.eshop.statistics.srv.bo.ReportUserActiveBO;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
public interface ReportUserActiveDailyService {

    void saveUserActiveDaily(Integer memberCount, Integer merchantCount, String reportDate);


    List<ReportUserActiveBO> getUserActiveListDaily(String beginTime, String endTime);

    List<ReportUserActiveBO> getUserActiveListMonth(String beginTime, String endTime);

    List<ReportUserActiveAreaDailyBO> getReportUserActiveAreaDailyList(String reportDate);

    List<ReportUserActiveAreaDailyBO> getAgentUserActiveListDaily(AgentReportParam param);

    List<ReportUserActiveAreaMonthBO> getAgentUserActiveListMonth(AgentReportParam param);
}
