package com.lawu.eshop.merchant.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.RechargeService;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.property.constants.ThirdPayStatusEnum;
import com.lawu.eshop.property.param.RechargeSaveDataParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockRechargeService extends BaseController implements RechargeService {
    @Override
    public Result save(@RequestBody RechargeSaveDataParam param) {
        return successCreated();
    }

    @Override
    public ThirdPayCallBackQueryPayOrderDTO getRechargeMoney(@RequestParam("rechargeId") String rechargeId) {
        ThirdPayCallBackQueryPayOrderDTO dto = new ThirdPayCallBackQueryPayOrderDTO();
        dto.setActualMoney(100);
        return dto;
    }

    @Override
    public Result<ThirdPayStatusEnum> getRechargeById(@PathVariable("rechargeId") Long rechargeId) {
        return successGet(ThirdPayStatusEnum.SUCCESS);
    }
}
