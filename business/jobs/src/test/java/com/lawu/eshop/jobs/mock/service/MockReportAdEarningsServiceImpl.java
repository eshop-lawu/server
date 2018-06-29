package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.ReportAdEarningsService;
import com.lawu.eshop.statistics.dto.ReportAdEarningsEndDTO;
import com.lawu.eshop.statistics.param.ReportAdEarningsParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockReportAdEarningsServiceImpl implements ReportAdEarningsService {


	@Override
	public Result saveReportAdEarnings(@RequestBody ReportAdEarningsParam reportAdEarningsParam) {
		return null;
	}

	@Override
	public Result<List<ReportAdEarningsEndDTO>> getReportAdEarningsIds() {
		return null;
	}
}
