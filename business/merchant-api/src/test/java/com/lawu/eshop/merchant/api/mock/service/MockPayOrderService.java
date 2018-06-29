package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.PayOrderService;
import com.lawu.eshop.order.dto.MerchantPayOrderListDTO;
import com.lawu.eshop.order.param.MerchantPayOrderListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockPayOrderService extends BaseController implements PayOrderService {
    @Override
    public Result<Page<MerchantPayOrderListDTO>> getMerchantPayOrderList(@RequestParam("userId") Long userId, @RequestBody MerchantPayOrderListParam param) {
        return successCreated();
    }
}
