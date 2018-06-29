package com.lawu.eshop.order.srv.service;

import com.lawu.eshop.order.param.foreign.ShoppingRefundQueryForeignParam;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemExtendBO;
import com.lawu.framework.core.page.Page;

/**
 * 购物订单项服务接口
 *
 * @author Sunny
 * @date 2017/4/11
 */
public interface ShoppingOrderItemService {

	/**
	 * 根据购物订单id获取购物订单项
	 * 
	 * @param id 购物订单项id
	 * @return
	 */
	ShoppingOrderItemBO get(Long id);
	
	/**
	 * 查询处于退款中的订单项
	 * To Member
	 * 
	 * @param memberId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	Page<ShoppingOrderItemExtendBO> selectRefundPageByMemberId(Long memberId, ShoppingRefundQueryForeignParam param);
	
	/**
	 * 查询处于退款中的订单项
	 * To Merchant
	 * 
	 * @param merchantId
	 *            会员id
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	Page<ShoppingOrderItemExtendBO> selectRefundPageByMerchantId(Long merchantId, ShoppingRefundQueryForeignParam param);
	
	/**
	 * 评论成功更新订单项为已评论
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 */
	void commentsSuccessful(Long id);
	
	/**
	 * 查询处于退款中的订单项
	 * To Operator
	 * 
	 * @param param
	 *            查询参数
	 * @return 订单列表
	 */
	Page<ShoppingOrderItemExtendBO> selectRefundPage(ShoppingRefundQueryForeignParam param);
	
	/**
	 * 查询购物订单项以及订单资料
	 * 用于发送评论资料
	 * 
	 * @param id 购物订单项id
	 * @return
	 */
	ShoppingOrderItemExtendBO getByComment(Long id);

	/**
	 * 获取订单中第一个商品名称
	 * @param id
	 * @return
	 */
    String getOrderItemProductName(Long id);
}
