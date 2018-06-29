package com.lawu.eshop.member.api.service;

/**
 * @author Sunny
 * @date 2017/4/14
 */
public interface ShoppingOrderExtendService {

	/**
	 * 用户确认收货
	 * 
	 * @param id
	 *            购物订单id
	 * @author Sunny
	 */
	void comfirmDelivery(Long id);

}
