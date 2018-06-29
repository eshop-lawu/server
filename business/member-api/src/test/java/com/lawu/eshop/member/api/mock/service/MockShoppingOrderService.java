package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ShoppingOrderService;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.dto.CommentOrderDTO;
import com.lawu.eshop.order.dto.ShoppingOrderMoneyDTO;
import com.lawu.eshop.order.dto.ShoppingOrderPaymentDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendQueryDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderStatusDTO;
import com.lawu.eshop.order.param.ActivityProductBuyQueryParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
public class MockShoppingOrderService extends BaseController implements ShoppingOrderService {

	@Override
	public Result<List<Long>> save(@RequestBody List<ShoppingOrderSettlementParam> params) {
		List<Long> ids = new ArrayList<>();
		return successCreated(ids);
	}

	@Override
	public Result<CommentOrderDTO> getOrderCommentStatus(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId) {
		CommentOrderDTO dto = new CommentOrderDTO();
		dto.setEvaluation(false);
		return successCreated(dto);
	}

	@Override
	public Result<Page<ShoppingOrderExtendQueryDTO>> selectPageByMemberId(@PathVariable("memberId") Long memberId, @RequestBody ShoppingOrderQueryForeignToMemberParam param) {
		return successCreated();
	}

	@Override
	public Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result cancelOrder(@PathVariable("memberId") Long memberId, @PathVariable("id") Long id) {
		return successCreated();
	}

	@Override
	public Result deleteOrder(@PathVariable("memberId") Long memberId, @PathVariable("id") Long id) {
		return successCreated();
	}

	@Override
	public Result tradingSuccess(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public Result requestRefund(@PathVariable("shoppingOrderitemId") Long shoppingOrderitemId, @RequestParam("memberId") Long memberId, @RequestBody ShoppingOrderRequestRefundParam param) {
		return successCreated();
	}

	@Override
	public Result<Page<ShoppingOrderItemRefundDTO>> selectRefundPageByMemberId(@PathVariable("memberId") Long memberId, @RequestBody ShoppingRefundQueryForeignParam param) {
		return successCreated();
	}

	@Override
	public Result<ShoppingOrderMoneyDTO> selectOrderMoney(@RequestParam("orderIds") String orderIds) {
		ShoppingOrderMoneyDTO dto = new ShoppingOrderMoneyDTO();
		dto.setOrderTotalPrice(new BigDecimal("100"));
		return successCreated(dto);
	}

	@Override
	public Result<ShoppingOrderNumberOfOrderStatusDTO> numberOfOrderStartus(@PathVariable("memberId") Long memberId) {
		ShoppingOrderNumberOfOrderStatusDTO dto = new ShoppingOrderNumberOfOrderStatusDTO();
		dto.setBeShippedCount(1L);
		dto.setEvaluationCount(1L);
		dto.setPendingPaymentCount(1L);
		dto.setRefundingCount(1L);
		dto.setToBeReceivedCount(1L);
		return successCreated(dto);
	}

	@Override
	public Result<ShoppingOrderPaymentDTO> orderPayment(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		ShoppingOrderPaymentDTO dto = new ShoppingOrderPaymentDTO();
		dto.setId(1L);
		dto.setOrderNum("M0003");
		dto.setOrderTotalPrice(new BigDecimal(1));
		return successCreated(dto);
	}

	@Override
	public Result<String> getOrderItemProductName(@PathVariable("id") String id) {
		return successCreated("name");
	}

	@Override
	public Result<ShoppingOrderDetailDTO> detail(Long id, Long memberId) {
		return successCreated();
	}

	@Override
	public Result<ShoppingOrderExpressInfoDTO> expressInfo(Long id, Long memberId) {
		return successGet();
	}

    @Override
    public Result<Boolean> isBuy(ActivityProductBuyQueryParam param) {
        return successGet(true);
    }

	@Override
	public Result<BigDecimal> selectDeductionPointsAmount(String orderIds) {
		return null;
	}

    @Override
    public Result<ShoppingOrderStatusDTO> orderStatus(Long id, Long memberId) {
        ShoppingOrderStatusDTO model = new ShoppingOrderStatusDTO();
        model.setOrderStatus(ShoppingOrderStatusEnum.BE_SHIPPED);
        return successGet(model);
    }
}
