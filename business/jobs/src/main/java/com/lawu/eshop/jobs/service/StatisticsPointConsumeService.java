package com.lawu.eshop.jobs.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.PointConsumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "statistics-srv")
public interface StatisticsPointConsumeService {

	//-----------------------------积分消费
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "pointConsume/saveDaily")
	Result saveDaily(@RequestBody ReportKCommonParam reportWithdraw);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "pointConsume/saveMonth")
	Result saveMonth(@RequestBody ReportKCommonParam reportWithdraw);
	
	@RequestMapping(method = RequestMethod.GET, value = "pointConsume/getDailyList")
	Result<List<PointConsumeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "pointConsume/deleteDailyByReportDate")
	Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "pointConsume/deleteMonthByReportDate")
	Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate);	
	
	@RequestMapping(value = "pointConsume/getReportDatePointConsumeDaily", method = RequestMethod.GET)
	Result<ReportNewDateDTO> getReportDateUserRegDaily();
	
	@RequestMapping(value = "pointConsume/getReportDatePointConsumeMonth", method = RequestMethod.GET)
	Result<ReportNewDateDTO> getReportDateUserRegMonth();
	
	@RequestMapping(method = RequestMethod.GET, value = "pointConsume/getDaily")
	Date getDaily();
	
	@RequestMapping(method = RequestMethod.GET, value = "pointConsume/getMonth")
	Date getMonth();
}