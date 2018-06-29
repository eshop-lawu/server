package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.PropertyWithdrawCashService;
import com.lawu.eshop.property.dto.WithdrawCashReportDTO;
import com.lawu.eshop.property.dto.WithdrawCashTotalReportDTO;
import com.lawu.eshop.property.param.AgentWithdrawCashReportParam;
import com.lawu.eshop.property.param.WithdrawCashReportParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockPropertyWithdrawCashServiceImpl implements PropertyWithdrawCashService {


	@Override
	public Result<List<WithdrawCashReportDTO>> selectWithdrawCashListByDateAndStatus(@RequestBody WithdrawCashReportParam param) {
		return null;
	}

	@Override
	public Result<WithdrawCashTotalReportDTO> selectWithdrawCashTotalByDateAndStatus(@RequestBody WithdrawCashReportParam param) {
		return null;
	}

	@Override
	public Result<List<WithdrawCashReportDTO>> selectAgentWithdrawCashList(@RequestBody AgentWithdrawCashReportParam param) {
		return null;
	}

	@Override
	public Result<WithdrawCashTotalReportDTO> selectAgentWithdrawCashTotal(@RequestBody AgentWithdrawCashReportParam param) {
		return null;
	}
}
