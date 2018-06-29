package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.CashManageFrontService;
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

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockCashManageFrontService extends BaseController implements CashManageFrontService {
    @Override
    public Result save(@RequestBody CashDataParam cash) {
        return successCreated();
    }

    @Override
    public Result<Page<WithdrawCashQueryDTO>> findCashList(@RequestBody CashBillDataParam cparam) {
        return successCreated();
    }

    @Override
    public Result<WithdrawCashDetailDTO> cashDetail(@RequestParam("id") Long id) {
        return successGet();
    }

    @Override
    public Result<List<WithdrawCashStatusDTO>> findCashDetailStatus(@RequestParam("ids") List<Long> ids) {
        WithdrawCashStatusDTO dto = new WithdrawCashStatusDTO();
        dto.setId(10L);
        dto.setStatus(CashStatusEnum.ALL);

        List<WithdrawCashStatusDTO> list = new ArrayList<>();
        list.add(dto);
        return successGet(list);
    }

    @Override
    public Result<Boolean> isExistCash(@RequestParam("userNum") String userNum, @RequestParam("bankAccountId") Long bankAccountId) {
        return successGet(false);
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
