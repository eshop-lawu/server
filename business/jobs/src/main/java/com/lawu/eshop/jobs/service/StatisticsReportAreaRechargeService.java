package com.lawu.eshop.jobs.service;

import java.util.List;

import com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 区域充值统计
 */
@FeignClient(value= "statistics-srv")
public interface StatisticsReportAreaRechargeService {

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "reportAreaRecharge/saveDaily")
	Result saveDaily(@RequestBody List<AgentReportRechargeSaveParam> saveParams);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "reportAreaRecharge/saveMonth")
	Result saveMonth(@RequestBody List<AgentReportRechargeSaveParam> saveParams);
	
	@RequestMapping(method = RequestMethod.GET, value = "reportAreaRecharge/getDailyList")
	Result<List<ReportAreaRechargeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "reportAreaRecharge/deleteDailyByReportDate")
	Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "reportAreaRecharge/deleteMonthByReportDate")
	Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate);	
	
}
