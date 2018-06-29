package com.lawu.eshop.jobs.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.statistics.dto.RechargeBalanceDailyDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;

@FeignClient(value= "statistics-srv")
public interface StatisticsRechargeBalanceService {

	//-----------------------------充值余额
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "rechargeBalance/saveDaily")
	Result saveDaily(@RequestBody ReportKCommonParam reportWithdraw);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "rechargeBalance/saveMonth")
	Result saveMonth(@RequestBody ReportKCommonParam reportWithdraw);
	
	@RequestMapping(method = RequestMethod.GET, value = "rechargeBalance/getDailyList")
	Result<List<RechargeBalanceDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "rechargeBalance/deleteDailyByReportDate")
	Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "rechargeBalance/deleteMonthByReportDate")
	Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate);	
	
	@RequestMapping(value = "rechargeBalance/getLastRechargeDay", method = RequestMethod.GET)
	public Date getLastRechargeDay();
	
	@RequestMapping(value = "rechargeBalance/getLastRechargeMonth", method = RequestMethod.GET)
	public Date getLastRechargeMonth();
	
}
