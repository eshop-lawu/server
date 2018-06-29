package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.OrderService;
import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockOrderService extends BaseController implements OrderService {
    @Override
    public double selectOrderMoney(@RequestParam("orderIds") String orderIds) {
        return 0;
    }

    @Override
    public Result<ShoppingOrderIsNoOnGoingOrderDTO> isNoOnGoingOrder(@PathVariable("merchantId") Long merchantId) {
        ShoppingOrderIsNoOnGoingOrderDTO dto = new ShoppingOrderIsNoOnGoingOrderDTO();
        dto.setIsNoOnGoingOrder(true);
        return successGet(dto);
    }
}
