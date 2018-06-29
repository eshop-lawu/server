package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.StatisticsSrvService;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointDailyDTO;
import com.lawu.eshop.statistics.dto.ReportAreaAdPointMonthDTO;
import com.lawu.eshop.statistics.dto.ReportAreaPointConsumeDailyInMonthDTO;
import com.lawu.eshop.statistics.dto.ReportAreaVolumnDailyInMonthDTO;
import com.lawu.eshop.statistics.param.ReportAreaAdPointDailyParams;
import com.lawu.eshop.statistics.param.ReportAreaAdPointMonthParams;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeDailyParam;
import com.lawu.eshop.statistics.param.ReportAreaPointConsumeMonthParam;
import com.lawu.eshop.statistics.param.ReportAreaVolumnDailyParam;
import com.lawu.eshop.statistics.param.ReportAreaVolumnMonthParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockStatisticsSrvServiceImpl implements StatisticsSrvService {


	@Override
	public Result<List<ReportAreaAdPointDailyDTO>> selectReportAreaAdPointDaily(@PathVariable("areaId") Integer areaId, @RequestParam("date") String date) {
		return null;
	}

	@Override
	public Result insertReportAreaAdPointDaily(@RequestBody ReportAreaAdPointDailyParams param) {
		return null;
	}

	@Override
	public Result deleteReportAreaAdPointDaily(@RequestParam(value = "id") Long id) {
		return null;
	}

	@Override
	public Result<List<ReportAreaAdPointMonthDTO>> selectReportAreaAdPointDailyInMonth(@RequestParam(value = "bdate") String bdate, @RequestParam(value = "edate") String edate) {
		return null;
	}

	@Override
	public Result insertReportAreaAdPointMonth(@RequestBody ReportAreaAdPointMonthParams param) {
		return null;
	}

	@Override
	public Result insertReportAreaVolumnDaily(@RequestBody ReportAreaVolumnDailyParam param) {
		return null;
	}

	@Override
	public Result<List<ReportAreaVolumnDailyInMonthDTO>> selectReportAreaVolumeDailyInMonth(@RequestParam("bdate") String bdate, @RequestParam("edate") String edate) {
		return null;
	}

	@Override
	public Result insertReportAreaVolumnMonth(@RequestBody ReportAreaVolumnMonthParam param) {
		return null;
	}

	@Override
	public Result insertReportAreaPointConsumeDaily(@RequestBody ReportAreaPointConsumeDailyParam param) {
		return null;
	}

	@Override
	public Result<List<ReportAreaPointConsumeDailyInMonthDTO>> selectReportAreaPointConsumeDailyInMonth(@RequestParam("bdate") String bdate, @RequestParam("edate") String edate) {
		return null;
	}

	@Override
	public Result saveReportAreaPointConsumeMonth(@RequestBody ReportAreaPointConsumeMonthParam param) {
		return null;
	}

	@Override
	public Result executeCollectReportAreaPointConsumeMonth(@RequestParam("pageSize") Integer pageSize) {
		return null;
	}
}
