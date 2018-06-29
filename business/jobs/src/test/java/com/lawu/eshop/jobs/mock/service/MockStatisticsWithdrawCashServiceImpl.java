package com.lawu.eshop.jobs.mock.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.jobs.service.StatisticsWithdrawCashService;
import com.lawu.eshop.statistics.dto.ReportWithdrawDailyDTO;
import com.lawu.eshop.statistics.param.AgentWithdrawCashParam;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockStatisticsWithdrawCashServiceImpl implements StatisticsWithdrawCashService {


	@Override
	public Result saveDaily(@RequestBody ReportKCommonParam reportWithdraw) {
		return null;
	}

	@Override
	public Result saveMonth(@RequestBody ReportKCommonParam reportWithdraw) {
		return null;
	}

	@Override
	public Result<List<ReportWithdrawDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result deleteDailyByReportDate(@RequestParam("reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result deleteMonthByReportDate(@RequestParam("reportDate") String reportDate) {
		return null;
	}

	@Override
	public Result saveAgentDaily(@RequestBody AgentWithdrawCashParam param) {
		return null;
	}

	@Override
	public Result<List<ReportWithdrawDailyDTO>> selectReportAreaWithdrawCashList(@RequestParam("month") String month, @RequestParam("cityId") Integer cityId) {
		return null;
	}

	@Override
	public Result saveAgentMonth(@RequestBody AgentWithdrawCashParam param) {
		return null;
	}

	@Override
	public Result<Date> getLastReportWithdraw() {
		return null;
	}

	@Override
	public Result<Date> getLastReportWithdrawMonth() {
		return null;
	}
}
