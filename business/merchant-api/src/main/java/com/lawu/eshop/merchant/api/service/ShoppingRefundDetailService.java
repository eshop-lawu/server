package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingRefundDetailDTO;
import com.lawu.eshop.order.param.ShoppingRefundDetailRerurnAddressParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToApplyForeignParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundDetailAgreeToRefundForeignParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/04/06
 */
@FeignClient(value = "order-srv", path = "shoppingRefundDetail/")
public interface ShoppingRefundDetailService {

	/**
	 * 根据id查询退款详情的物流信息
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id(用于鉴权)
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Deprecated
	@RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
	
	/**
	 * 根据id查询退款详情的物流信息
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id(用于鉴权)
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExpressInfoDTO> expressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);

	/**
	 * 根据订单项id查询退款详情
	 * 
	 * @param shoppingOrderitemId
	 *            购物订单项id
	 * @return
	 */
	@RequestMapping(value = "getRefundDetail/{shoppingOrderItemId}", method = RequestMethod.GET)
	Result<ShoppingRefundDetailDTO> getRefundDetail(@PathVariable("shoppingOrderItemId") Long shoppingOrderItemId, @RequestParam("merchantId") Long merchantId);

	/**
	 * 商家是否同意买家的退货申请
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            参数 是否同意申请
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "agreeToApply/{id}", method = RequestMethod.PUT)
	Result agreeToApply(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailAgreeToApplyForeignParam param);
	
	/**
	 * 商家填写退货地址信息
	 * 
	 * @param id
	 *            退款详情id
	 * @param merchantId
	 *            商家id
	 * @param param
	 * 			     退货地址信息
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "fillReturnAddress/{id}", method = RequestMethod.PUT)
	Result fillReturnAddress(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailRerurnAddressParam param);
	
	/**
	 * 商家是否同意退款
	 * 
	 * @param id
	 *            退款详情Id
	 * @param merchantId
	 *            商家Id
	 * @param param
	 * 			      参数
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "agreeToRefund/{id}", method = RequestMethod.PUT)
	Result agreeToRefund(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingRefundDetailAgreeToRefundForeignParam param);
}