package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.RechargeService;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockRechargeService extends BaseController implements RechargeService {


	@Override
	public Result save(@RequestBody RechargeSaveDataParam param) {
		return successCreated();
	}

	@Override
	public ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(@RequestParam("rechargeId") String rechargeId) {
		ThirdPayCallBackQueryPayOrderDTO dto = new ThirdPayCallBackQueryPayOrderDTO();
		dto.setActualMoney(Double.parseDouble("100"));
		dto.setOrderNum("4213123123");
		return dto;
	}
}
