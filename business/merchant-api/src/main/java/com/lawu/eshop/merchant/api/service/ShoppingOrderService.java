package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ShoppingOrderIsNoOnGoingOrderDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExpressInfoDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderExtendDetailDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderItemRefundForMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderQueryToMerchantDTO;
import com.lawu.eshop.order.dto.foreign.ShoppingOrderStatusDTO;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
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
	 * To商家
	 * 根据查询参数分页查询
	 * 
	 * @param merchantId
	 *            商家id
	 * @param params
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectPageByMerchantId/{merchantId}", method = RequestMethod.POST)
	Result<Page<ShoppingOrderQueryToMerchantDTO>> selectPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingOrderQueryForeignToMerchantParam param);
	
	/**
	 * 根据id查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExtendDetailDTO> get(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
	
	/**
	 * 根据id查询订单详情
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderDetailDTO> detail(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
	
	/**
	 * 商家发货填写物流信息 并修改购物订单以及购物订单项的状态为待收货
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            订单物流参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "fillLogisticsInformation/{id}", method = RequestMethod.PUT)
	Result fillLogisticsInformation(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId, @RequestBody ShoppingOrderLogisticsInformationParam param);
	
	/**
	 * 查询商家是否有正在进行中的订单
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "isNoOnGoingOrder/{merchantId}", method = RequestMethod.GET)
	Result<ShoppingOrderIsNoOnGoingOrderDTO> isNoOnGoingOrder(@PathVariable("merchantId") Long merchantId);
	
	/**
	 * 根据查询参数分页查询退款记录
	 * 购物订单 购物订单项 退款详情关联查询
	 * To Merchant
	 * 
	 * @param merchantId
	 *            商家id
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "selectRefundPageByMerchantId/{merchantId}", method = RequestMethod.POST)
	Result<Page<ShoppingOrderItemRefundForMerchantDTO>> selectRefundPageByMerchantId(@PathVariable("merchantId") Long merchantId, @RequestBody ShoppingRefundQueryForeignParam param);
	
	/**
	 * 查询各种订单状态的数量
	 * To Merchant
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "numberOfOrderStartusByMerchant/{merchantId}", method = RequestMethod.GET)
	Result<ShoppingOrderNumberOfOrderStatusForMerchantForeignDTO> numberOfOrderStartusByMerchant(@PathVariable("merchantId") Long merchantId);
	
	/**
	 * 根据id查询订单物流信息
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "getExpressInfo/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExpressDTO> getExpressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
	
	/**
	 * 根据id查询订单物流信息
	 * 
	 * @param id
	 *            购物订单id
	 * @param merchantId
	 *            商家id
	 * @return
	 */
	@RequestMapping(value = "expressInfo/{id}", method = RequestMethod.GET)
	Result<ShoppingOrderExpressInfoDTO> expressInfo(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);

	/**
	 * 获取订单中第一个商品名称
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getOrderItemProductName/{id}", method = RequestMethod.GET)
	Result<String> getOrderItemProductName(@PathVariable("id") String id);
	
    /**
     * 查询订单状态
     * 
     * @param id
     * @param memberId
     * @return
     * @author jiangxinjun
     * @createDate 2018年4月26日
     * @updateDate 2018年4月26日
     */
    @RequestMapping(value = "orderStatus/{id}", method = RequestMethod.GET)
    Result<ShoppingOrderStatusDTO> orderStatus(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId);
}
