package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.StatisticsReportAreaRechargeService;
import com.lawu.eshop.statistics.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.statistics.param.AgentReportRechargeSaveParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockStatisticsReportAreaRechargeServiceImpl implements StatisticsReportAreaRechargeService {


	@Override
	public Result saveDaily(@RequestBody List<AgentReportRechargeSaveParam> saveParams) {
		return null;
	}

	@Override
	public Result saveMonth(@RequestBody List<AgentReportRechargeSaveParam> saveParams) {
		return null;
	}

	@Override
	public Result<List<ReportAreaRechargeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
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
}
