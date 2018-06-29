package com.lawu.eshop.jobs.mock.service;

import com.lawu.eshop.jobs.service.TransactionDetailService;
import com.lawu.eshop.property.dto.TotalSalesDTO;
import com.lawu.eshop.property.dto.UserIncomeExpenditureDatailDTO;
import com.lawu.eshop.property.param.TotalSalesQueryParam;
import com.lawu.eshop.property.param.UserIncomeExpenditureQueryParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockTransactionDetailServiceImpl implements TransactionDetailService {


	@Override
	public Result<TotalSalesDTO> selectTotalSales(@RequestBody TotalSalesQueryParam param) {
		return null;
	}

	@Override
	public Result<UserIncomeExpenditureDatailDTO> selectUserIncomeExpenditure(@RequestBody UserIncomeExpenditureQueryParam param) {
		return null;
	}
}
