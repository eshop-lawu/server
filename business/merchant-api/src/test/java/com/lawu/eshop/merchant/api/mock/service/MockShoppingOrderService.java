package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.order.dto.foreign.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.ShoppingOrderService;
import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockShoppingOrderService extends BaseController implements ShoppingOrderService {
    @Override
    public Result<Page<ShoppingOrderQueryToMerchantDTO>> selectPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingOrderQueryForeignToMerchantParam param) {
        return successCreated();
    }

    @Override
    public Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result fillLogisticsInformation(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingOrderLogisticsInformationParam param) {
        return successCreated();
    }

    @Override
    public Result<ShoppingOrderIsNoOnGoingOrderDTO> isNoOnGoingOrder(@PathVariable("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result<Page<ShoppingOrderItemRefundForMerchantDTO>> selectRefundPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingRefundQueryForeignParam param) {
        return successCreated();
    }

    @Override
    public Result<ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO> numberOfOrderStartusByMerchant(@PathVariable("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId) {
        return successGet();
    }

	@Override
	public Result<ShoppingOrderDetailDTO> detail(Long id, Long merchantId) {
		return successGet();
	}

	@Override
	public Result<ShoppingOrderExpressInfoDTO> expressInfo(Long id, Long merchantId) {
		return successGet();
	}

    @Override
    public Result<String> getOrderItemProductName(@PathVariable("id") String id) {
        return null;
    }

    @Override
    public Result<ShoppingOrderStatusDTO> orderStatus(Long id, Long merchantId) {
        return null;
    }
}
