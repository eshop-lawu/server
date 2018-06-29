package com.lawu.eshop.statistics.srv.service;

import java.util.List;

import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegDailyBO;
import com.lawu.eshop.statistics.srv.bo.ReportAreaUserRegMonthBO;

/**
 * @author zhangyong
 * @date 2017/8/11.
 */
public interface ReportAreaUserRegService {

    List<ReportAreaUserRegDailyBO> getUserRegListDaily(AgentReportParam param);

    List<ReportAreaUserRegMonthBO> getUserRegListMonth(AgentReportParam param);
}
