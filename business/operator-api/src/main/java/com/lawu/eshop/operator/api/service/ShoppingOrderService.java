package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForOperatorDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToOperatorDTO;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/04/12
 */
@FeignClient(value = "order-srv", path = "shoppingOrder/")
public interface ShoppingOrderService {
	
	/**
	 * 根据查询参数分页查询
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectPage", method = RequestMethod.POST)
	public Result<Page<ShoppingOrderQueryToOperatorDTO>> selectPage(@RequestBody ShoppingOrderQueryForeignToOperatorParam param);
	
	/**
	 * 根据id查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") Long id);
	
	/**
	 * 根据id查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderDetailDTO> detail(@PathVariable("id") Long id);
	
	/**
	 * 更新订单信息
	 * @param id
	 * 			     购物订单id
	 * @param param
	 *            查询参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "updateInformation/{id}", method = RequestMethod.PUT)
	Result updateInformation(@PathVariable("id") Long id , @RequestBody ShoppingOrderUpdateInfomationParam param);
	
	/**
	 * 取消购物订单
	 * 
	 * @param id
	 *            购物订单id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "cancelOrder/{id}", method = RequestMethod.PUT)
	public Result cancelOrder(@PathVariable("id") Long id);
	
	/**
	 * 根据查询参数分页查询退款记录
	 * 购物订单 购物订单项 退款详情关联查询
	 * To Operator
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectRefundPage", method = RequestMethod.POST)
	Result<Page<ShoppingOrderItemRefundForOperatorDTO>> selectRefundPage(@RequestBody ShoppingRefundQueryForeignParam param);
}
