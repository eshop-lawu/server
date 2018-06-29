package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/04/26
 */
@FeignClient(value = "order-srv")
public interface ShoppingOrderService {
	
	/**
	 * 查询已完成但是未计算提成的购物订单
	 * 
	 * @param memberId 会员id
	 * @return
	 * @author Sunny
	 */
	@RequestMapping(value = "shoppingOrder/commissionShoppingOrder", method = RequestMethod.GET)
	Result<List<ShoppingOrderCommissionDTO>> commissionShoppingOrder();
	
	/**
	 * 查询已完成但是未计算提成的购物订单
	 * 
	 * @param memberId 会员id
	 * @return
	 * @author Sunny
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "shoppingOrder/updateCommissionStatus", method = RequestMethod.PUT)
	Result updateCommissionStatus(@RequestParam("ids") List<Long> ids);
}
