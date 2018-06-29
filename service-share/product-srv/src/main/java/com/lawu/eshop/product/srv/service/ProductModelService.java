package com.lawu.eshop.product.srv.service;


import java.util.List;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCancelOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessIncreaseSalesNotification;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.product.srv.bo.CommentProductInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ShoppingCartProductModelBO;
import com.lawu.eshop.product.srv.bo.productModelDataBO;

/**
 * 
 * @author Sunny
 * @date 2017/3/30
 */
public interface ProductModelService {

	/**
	 * 根据产品型号ID查询
	 * 
	 * @param query
	 * @return
	 */
	ShoppingCartProductModelBO getShoppingCartProductModel(Long id);
	
	/**
	 * 根据产品型号ID列表查询
	 * 
	 * @param ids 产品型号ID列表
	 * @return
	 */
	List<ShoppingCartProductModelBO> getShoppingCartProductModel(List<Long> ids);
	
	/**
	 * 创建购物订单
	 * 订单模块发送消息减商品库存，并且保存商品库存流水记录
	 * 
	 * @param shoppingCartCreateOrderNotification 发送的数据
	 * @author Sunny
	 */
	void lessInventory(ShoppingOrderCreateOrderNotification shoppingOrderCreateOrderNotification);
	
	/**
	 * 取消购物订单
	 * 订单模块发送消息释放商品库存，并且保存商品库存流水记录
	 * 
	 * @param shoppingOrderCancelOrderNotification 发送的数据
	 * @author Sunny
	 */
	void releaseInventory(ShoppingOrderCancelOrderNotification shoppingOrderCancelOrderNotification);

	/**
	 * 商家查看评价时，显示商品信息和其型号信息
	 * @param productModelId
	 * @return
	 * @author Yangqh
	 */
	CommentProductInfoBO selectCommentProductInfo(Long productModelId);
	
	/**
	 * 确认收货，增加销量
	 * 
	 * @param notification 接收的数据
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	void increaseSales(ShoppingOrderTradingSuccessIncreaseSalesNotification notification);
	
	/**
	 * 检查库存是否扣除成功
	 * 
	 * @param shoppingOrderCreateOrderNotification 接收到的数据
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
	ShoppingOrderCreateOrderReply checkLessInventory(ShoppingOrderCreateOrderNotification shoppingOrderCreateOrderNotification);
	
	
	/**
	 * 商品对应的型号
	 * @param productId
	 * @return
	 * @author zhangrc
	 * @date 2017/11/23
	 */
	List<productModelDataBO> queryProductModel(Long productId);
	
	/**
	 * 根据抢购活动商品型号id
	 * 查询抢购活动所需要的商品型号数据
	 * 
	 * @param activityProductModelId 抢购活动商品型号id
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月24日
	 * @updateDate 2017年11月24日
	 */
	SeckillActivityProductModelInfoBO seckillActivityProductModel(Long activityProductModelId) throws DataNotExistException;
}
