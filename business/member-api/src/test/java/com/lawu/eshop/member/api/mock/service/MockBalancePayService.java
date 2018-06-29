package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.BalancePayService;
import com.lawu.eshop.property.param.BalancePayDataParam;
import com.lawu.eshop.property.param.BalancePayParam;
import com.lawu.eshop.property.param.BalancePayValidateDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MockBalancePayService extends BaseController implements BalancePayService {


	@Override
	public Result orderPay(@RequestBody BalancePayDataParam param) {
		return successCreated();
	}

	@Override
	public Result billPay(BalancePayDataParam dparam) {
		return successCreated();
	}

	@Override
	public Result balancePayPoint(BalancePayParam param) {
		return successCreated();
	}

	@Override
	public Result memberRedPacketPay(BalancePayDataParam param) {
		return null;
	}

	@Override
	public Result orderPayValidatePwd(@RequestBody BalancePayValidateDataParam param) {
		return null;
	}

	@Override
	public Result billPayValidatePwd(@RequestBody BalancePayValidateDataParam dparam) {
		return null;
	}

	@Override
	public Result balancePayPointValidatePwd(BalancePayValidateDataParam param) {
		return null;
	}

	@Override
	public Result memberRedPacketPayValidatePwd(BalancePayValidateDataParam param) {
		return null;
	}
}
