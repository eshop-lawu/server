package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.framework.web.Result;

@FeignClient(value = "order-srv")
public interface OrderService {

	/**
	 * 支付时查询订单总金额
	 * @param orderIds
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shoppingOrder/selectOrderMoney")
	double selectOrderMoney(@RequestParam("orderIds") String orderIds);

	/**
	 * 查询商家是否存在未完结订单
	 * @param currentUserId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shoppingOrder/isNoOnGoingOrder/{merchantId}")
	Result<ShoppingOrderIsNoOnGoingOrderDTO> isNoOnGoingOrder(@PathVariable("merchantId") Long merchantId);

}
