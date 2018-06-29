package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.jobs.service.PropertyRechargeService;
import com.lawu.eshop.property.dto.RechargeReportDTO;
import com.lawu.eshop.property.dto.ReportAreaRechargeDailyDTO;
import com.lawu.eshop.property.param.AgentReportRechargeQueryParam;
import com.lawu.eshop.property.param.RechargeReportParam;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockPropertyRechargeServiceImpl implements PropertyRechargeService {


	@Override
	public Result<RechargeReportDTO> selectWithdrawCashListByDateAndStatus(@RequestBody RechargeReportParam param) {
		return null;
	}

	@Override
	public Result<List<ReportAreaRechargeDailyDTO>> selectAgentAreaReportRechargeListByDate(@RequestBody AgentReportRechargeQueryParam param) {
		return null;
	}
}
