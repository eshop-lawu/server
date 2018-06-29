package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/04/14
 */
@FeignClient(value = "order-srv", path = "shoppingRefundDetail/")
public interface ShoppingRefundDetailService {
	
	/**
	 * 退款给买家
	 * 
	 * @param id
	 *            退款详情id
	 * @param param
	 *            参数 是否同意申请
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "agreeToRefund/{id}", method = RequestMethod.PUT)
	Result agreeToRefund(@PathVariable("id") Long id, @RequestBody ShoppingRefundDetailAgreeToRefundForeignParam param);
	
	/**
	 * 撤销退货申请
	 * 
	 * @param id
	 *            退款详情id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "revokeRefundRequest/{id}", method = RequestMethod.PUT)
	Result revokeRefundRequest(@PathVariable("id") Long id);
	
	/**
	 * 根据订单项id查询退款详情
	 * 
	 * @param shoppingOrderitemId
	 *            购物订单项id
	 * @return
	 */
	@RequestMapping(value = "getRefundDetail/{shoppingOrderItemId}", method = RequestMethod.GET)
	Result<ShoppingRefundDetailDTO> getRefundDetail(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId);
}