package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.BusinessDepositService;
import com.lawu.eshop.property.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.property.dto.BusinessDepositDTO;
import com.lawu.eshop.property.dto.BusinessDepositDetailDTO;
import com.lawu.eshop.property.dto.BusinessDepositInitDTO;
import com.lawu.eshop.property.param.BusinessDepositSaveDataParam;
import com.lawu.eshop.property.param.BusinessRefundDepositDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockBusinessDepositService extends BaseController implements BusinessDepositService {
    @Override
    public Result<BusinessDepositInitDTO> save(@RequestBody BusinessDepositSaveDataParam param) {
        return successCreated();
    }

    @Override
    public Result<BusinessDepositDetailDTO> selectDeposit(@PathVariable("businessId") String businessId) {
        return successGet();
    }

    @Override
    public Result refundDeposit(@RequestBody BusinessRefundDepositDataParam dparam) {
        return successCreated();
    }

    @Override
    public Result getDepositValue() {
        return successGet();
    }

    @Override
    public Result<BusinessDepositStatusEnum> getDepositStatusById(@PathVariable("depositId") Long depositId) {
        return null;
    }

    @Override
    public Result<BusinessDepositDTO> getDepositById(@PathVariable("depositId") Long depositId) {
        return null;
    }
}
