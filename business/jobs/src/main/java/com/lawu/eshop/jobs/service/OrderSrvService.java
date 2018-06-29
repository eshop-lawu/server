package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月26日 下午7:37:11
 *
 */
@FeignClient(value = "order-srv")
public interface OrderSrvService {

	/**
	 * 查询确认收货后7天冻结资金转给商家后且未计算提成的订单 
	 * 查询出14天系统自动确认收货且未计算提成的订单
	 * 
	 * @return
	 * @author yangqh
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shoppingOrder/commissionShoppingOrder")
	Result<List<ShoppingOrderCommissionDTO>> commissionShoppingOrder(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

	/**
	 * 批量修改是否计算过提成状态
	 * @param ids
	 * @return
	 * @author yangqh
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "shoppingOrder/updateCommissionStatus")
	Result updateCommissionStatus(@RequestParam("ids") List<Long> ids);

	/**
	 * 查询已支付且未计算提成的买单
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "payOrder/selectNotCommissionOrder")
	Result<List<ShoppingOrderCommissionDTO>> selectNotCommissionOrder(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);
	
	/**
	 * 修改买单是否提成状态
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, value = "payOrder/updatePayOrderCommissionStatus")
	Result updatePayOrderCommissionStatus(@RequestParam("ids") List<Long> ids);
}
