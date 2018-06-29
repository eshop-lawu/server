package com.lawu.eshop.jobs.mock.service;

import java.util.List;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.jobs.service.OrderSrvService;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockOrderSrvServiceImpl implements OrderSrvService {


	@Override
	public Result<List<ShoppingOrderCommissionDTO>> commissionShoppingOrder(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
		return null;
	}

	@Override
	public Result updateCommissionStatus(@RequestParam("ids") List<Long> ids) {
		Result result = new Result();
		result.setRet(ResultCode.SUCCESS);
		return result;
	}

	@Override
	public Result<List<ShoppingOrderCommissionDTO>> selectNotCommissionOrder(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize) {
		return null;
	}

	@Override
	public Result updatePayOrderCommissionStatus(@RequestParam("ids") List<Long> ids) {
		Result result = new Result();
		result.setRet(ResultCode.SUCCESS);
		return result;
	}
}
