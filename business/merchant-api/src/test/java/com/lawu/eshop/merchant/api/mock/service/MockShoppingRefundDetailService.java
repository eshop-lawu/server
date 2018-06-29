package com.lawu.eshop.merchant.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.merchant.api.service.ShoppingRefundDetailService;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailRerurnAddressParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToApplyForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockShoppingRefundDetailService extends BaseController implements ShoppingRefundDetailService {
    @Override
    public Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result<ShoppingRefundDetailDTO> getRefundDetail(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId, @RequestParam("merchantId") Long merchantId) {
        return successGet();
    }

    @Override
    public Result agreeToApply(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailAgreeToApplyForeignParam param) {
        return successCreated();
    }

    @Override
    public Result fillReturnAddress(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailRerurnAddressParam param) {
        return successCreated();
    }

    @Override
    public Result agreeToRefund(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailAgreeToRefundForeignParam param) {
        return successCreated();
    }

	@Override
	public Result<ShoppingOrderExpressInfoDTO> expressInfo(Long id, Long merchantId) {
		return successGet();
	}
}
