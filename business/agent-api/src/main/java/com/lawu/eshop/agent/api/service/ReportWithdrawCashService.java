package com.lawu.eshop.agent.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.statistics.dto.ReportAreaWithdrawDTO;
import com.lawu.eshop.statistics.param.AgentReportParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/8/15.
 */
@FeignClient(value = "statistics-srv")
public interface ReportWithdrawCashService {

    @RequestMapping(value = "withdrawCash/selectAreaWithdrawDailyReport",method = RequestMethod.POST)
    Result<ReportAreaWithdrawDTO> selectAreaWithdrawDailyReport(@RequestBody AgentReportParam param);

    @RequestMapping(value = "withdrawCash/selectAreaWithdrawMonthReport",method = RequestMethod.POST)
    Result<ReportAreaWithdrawDTO> selectAreaWithdrawMonthReport(@RequestBody AgentReportParam param);
}
