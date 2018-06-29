package com.lawu.eshop.jobs.mock.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.jobs.service.ReportEarningService;
import com.lawu.eshop.statistics.dto.ReportEarningsDailyDTO;
import com.lawu.eshop.statistics.param.ReportEarningParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockReportEarningServiceImpl implements ReportEarningService {


	@Override
	public Result saveDaily(@RequestBody ReportEarningParam param) {
		return null;
	}

	@Override
	public Result saveMonth(@RequestBody ReportEarningParam param) {
		return null;
	}

	@Override
	public Result<List<ReportEarningsDailyDTO>> getDailyList(@RequestParam("reportDate") String reportDate) {
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
