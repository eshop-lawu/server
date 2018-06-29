package com.lawu.eshop.jobs.mock.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.jobs.service.StatisticsPointConsumeService;
import com.lawu.eshop.statistics.dto.PointConsumeDailyDTO;
import com.lawu.eshop.statistics.dto.ReportNewDateDTO;
import com.lawu.eshop.statistics.param.ReportKCommonParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockStatisticsPointConsumeServiceImpl implements StatisticsPointConsumeService {


	@Override
	public Result saveDaily(@RequestBody ReportKCommonParam reportWithdraw) {
		return null;
	}

	@Override
	public Result saveMonth(@RequestBody ReportKCommonParam reportWithdraw) {
		return null;
	}

	@Override
	public Result<List<PointConsumeDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
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
	public Result<ReportNewDateDTO> getReportDateUserRegDaily() {
		return null;
	}

	@Override
	public Result<ReportNewDateDTO> getReportDateUserRegMonth() {
		return null;
	}

	@Override
	public Date getDaily() {
		return null;
	}

	@Override
	public Date getMonth() {
		return null;
	}
}
