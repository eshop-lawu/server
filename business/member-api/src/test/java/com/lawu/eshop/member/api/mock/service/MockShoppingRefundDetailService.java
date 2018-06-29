package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ShoppingRefundDetailService;
import com.lawu.eshop.order.dto.foreign.RefundInformationDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailLogisticsInformationParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;



@Service
public class MockShoppingRefundDetailService extends BaseController implements ShoppingRefundDetailService {

	@Override
	public Result fillLogisticsInformation(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId, @RequestBody ShoppingRefundDetailLogisticsInformationParam param) {
		return successCreated();
	}

	@Override
	public Result platformIntervention(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result<ShoppingRefundDetailDTO> getRefundDetail(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result revokeRefundRequest(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

    @Override
    public Result<RefundInformationDTO> getRefundInformation(Long shoppingOrderItemId, Long memberId) {
        return successGet();
    }
}
