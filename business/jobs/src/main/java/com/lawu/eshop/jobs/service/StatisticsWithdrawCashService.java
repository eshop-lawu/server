package com.lawu.eshop.jobs.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.ReportWithdrawDailyDTO;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "statistics-srv")
public interface StatisticsWithdrawCashService {

	//-----------------------------提现统计
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "withdrawCash/saveDaily")
	Result saveDaily(@RequestBody ReportKCommonParam reportWithdraw);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "withdrawCash/saveMonth")
	Result saveMonth(@RequestBody ReportKCommonParam reportWithdraw);
	
	@RequestMapping(method = RequestMethod.GET, value = "withdrawCash/getDailyList")
	Result<List<ReportWithdrawDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "withdrawCash/deleteDailyByReportDate")
	Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "withdrawCash/deleteMonthByReportDate")
	Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate);

	@RequestMapping(method = RequestMethod.POST, value = "withdrawCash/saveAgentDaily")
	Result saveAgentDaily(@RequestBody AgentWithdrawCashParam param);

	@RequestMapping(method = RequestMethod.GET, value = "withdrawCash/selectReportAreaWithdrawCashList")
	Result<List<ReportWithdrawDailyDTO>> selectReportAreaWithdrawCashList(@RequestParam("month") String month, @RequestParam("cityId") Integer cityId);

	@RequestMapping(method = RequestMethod.POST, value = "withdrawCash/saveAgentMonth")
	Result saveAgentMonth(@RequestBody AgentWithdrawCashParam param);
	
	@RequestMapping(value = "withdrawCash/getLastReportWithdraw", method = RequestMethod.GET)
	Result<Date> getLastReportWithdraw();
	
	@RequestMapping(value = "withdrawCash/getLastReportWithdrawMonth", method = RequestMethod.GET)
	Result<Date> getLastReportWithdrawMonth();
}
