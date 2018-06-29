package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.ReportSalesService;
import com.lawu.eshop.statistics.param.PlatformTotalSalesSaveParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockReportSalesServiceImpl implements ReportSalesService {


	@Override
	public Result save(@RequestBody PlatformTotalSalesSaveParam param) {
		return null;
	}
}
