package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.PayOrderService;
import com.lawu.eshop.order.constants.EvaluationEnum;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.dto.MemberPayOrderInfoDTO;
import com.lawu.eshop.order.dto.PayOrderBaseDTO;
import com.lawu.eshop.order.dto.PayOrderDTO;
import com.lawu.eshop.order.dto.PayOrderIdDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.order.param.PayOrderDataParam;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.PayOrderParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MockPayOrderService extends BaseController implements PayOrderService {

	@Override
	public Result<PayOrderIdDTO> savePayOrderInfo(@PathVariable("memberId") Long memberId, @ModelAttribute PayOrderDataParam param, @RequestParam("numNum") String numNum) {
		return successCreated();
	}

	@Override
	public Result<Page<PayOrderDTO>> getpayOrderList(@PathVariable("memberId") Long memberId, @ModelAttribute PayOrderListParam param) {
		PayOrderDTO dto = new PayOrderDTO();
		dto.setActualAmount(new BigDecimal("1"));
		dto.setEvaluationEnum(EvaluationEnum.UN_EVALUATION);
		dto.setFavoredAmount(new BigDecimal("1"));
		dto.setGmtCreate(new Date());
		dto.setId(1L);
		dto.setMerchantId(1L);
		dto.setTotalAmount(new BigDecimal("2"));
		List<PayOrderDTO> list = new ArrayList<>();
		Page<PayOrderDTO> page = new Page<>();
		page.setCurrentPage(1);
		page.setTotalCount(10);
		page.setRecords(list);
		return successCreated(page);
	}

	@Override
	public Result delPayOrderInfo(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
		return successCreated();
	}

	@Override
	public ThirdPayCallBackQueryPayOrderDTO selectThirdPayCallBackQueryPayOrder(@RequestParam("orderId") String orderId) {
		ThirdPayCallBackQueryPayOrderDTO dto = new ThirdPayCallBackQueryPayOrderDTO();
		dto.setPayOrderStatusEnum(PayOrderStatusEnum.STATUS_UNPAY);
		dto.setBusinessUserNum("B00001");
		dto.setActualMoney(Double.parseDouble("100"));
		dto.setOrderNum("323212321312");
		return dto;
	}

	@Override
	public Result<MemberPayOrderInfoDTO> getOrderInfo(@RequestParam("id") Long id, @RequestParam("memberId") Long memberId) {
		MemberPayOrderInfoDTO dto = new MemberPayOrderInfoDTO();
		dto.setStoreUrl("1.jpg");
		dto.setName("name");
		dto.setMerchantId(1L);
		dto.setEvaluationEnum(EvaluationEnum.EVALUATION_SUCCESS);
		return successCreated(dto);
	}

	@Override
	public PayOrderBaseDTO getPayOrderById(@RequestParam("id") String id) {
		return null;
	}

}
