package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.CashManageFrontService;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.dto.CashFeeDTO;
import com.lawu.eshop.property.dto.CashFeeParamDTO;
import com.lawu.eshop.property.dto.WithdrawCashDetailDTO;
import com.lawu.eshop.property.dto.WithdrawCashQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashStatusDTO;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashChargeDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockCashManageFrontService extends BaseController implements CashManageFrontService {

	@Override
	public Result save(@RequestBody CashDataParam cash) {
		return successCreated();
	}

	@Override
	public Result<Page<WithdrawCashQueryDTO>> findCashList(@RequestBody CashBillDataParam cparam) {
		WithdrawCashQueryDTO dto = new WithdrawCashQueryDTO();
		List<WithdrawCashQueryDTO> list = new ArrayList<>();
		list.add(dto);
		Page<WithdrawCashQueryDTO> page = new Page();
		page.setRecords(list);
		page.setCurrentPage(1);
		page.setTotalCount(100);
		return successCreated(page);
	}

	@Override
	public Result<WithdrawCashDetailDTO> cashDetail(@RequestParam("id") Long id) {
		return null;
	}

	@Override
	public Result<List<WithdrawCashStatusDTO>> findCashDetailStatus(@RequestParam("ids") List<Long> ids) {
		WithdrawCashStatusDTO dto = new WithdrawCashStatusDTO();
		dto.setId(1L);
		dto.setStatus(CashStatusEnum.ACCEPT);
		List<WithdrawCashStatusDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

	@Override
	public Result<Boolean> isExistCash(@RequestParam("userNum") String userNum, @RequestParam("bankAccountId") Long bankAccountId) {
		return successCreated(true);
	}

	@Override
	public Result<CashFeeDTO> calculationFee(@RequestBody CashChargeDataParam cashChargeDataParam) {
		return null;
	}

	@Override
	public Result<CashFeeParamDTO> getCalculationFeeParam(@RequestParam("userNum") String userNum) {
		return null;
	}
}
