package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.ReportUserIncomeExpenditureService;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveParam;
import com.lawu.eshop.statistics.param.ReportUserIncomeExpenditureSaveWrapperParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockReportUserIncomeExpenditureServiceImpl implements ReportUserIncomeExpenditureService {


	@Override
	public Result save(@RequestBody ReportUserIncomeExpenditureSaveParam param) {
		return null;
	}

	@Override
	public Result batchSave(@RequestBody ReportUserIncomeExpenditureSaveWrapperParam param) {
		return null;
	}
}
