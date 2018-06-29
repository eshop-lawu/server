package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.ShoppingCartService;
import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
class MockShoppingCartService extends BaseController implements ShoppingCartService {

	@Override
	public Result save(@PathVariable("memberId") Long memberId, @RequestBody ShoppingCartSaveParam parm) {
		return successCreated();
	}

	@Override
	public Result<List<ShoppingCartDTO>> findListByMemberId(@PathVariable(name = "memberId") Long memberId) {
		ShoppingCartDTO dto = new ShoppingCartDTO();
		dto.setMerchantStoreId(1L);
		dto.setProductId(1L);
		dto.setId(1L);
		dto.setMerchantId(1L);
		dto.setMerchantName("nfdf");
		dto.setProductModelId(1L);
		dto.setQuantity(1);
		dto.setSalesPrice(new BigDecimal(1));
		List<ShoppingCartDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

	@Override
	public Result update(@PathVariable(name = "id") Long id, @RequestParam("memberId") Long memberId, @RequestBody ShoppingCartUpdateParam parm) {
		return successCreated();
	}

	@Override
	public Result delete(@RequestParam("memberId") Long memberId, @RequestBody List<Long> ids) {
		return successCreated();
	}

	@Override
	public Result<List<ShoppingCartDTO>> findListByIds(@PathVariable("memberId") Long memberId, @RequestParam(name = "ids") List<Long> ids) {
		ShoppingCartDTO dto = new ShoppingCartDTO();
		dto.setProductModelId(1L);
		dto.setSalesPrice(new BigDecimal("1"));
		dto.setQuantity(1);
		dto.setMerchantId(1L);
		List<ShoppingCartDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

	@Override
	public Result<Long> findListByIds(@PathVariable("memberId") Long memberId) {
		return successCreated(1L);
	}
}
