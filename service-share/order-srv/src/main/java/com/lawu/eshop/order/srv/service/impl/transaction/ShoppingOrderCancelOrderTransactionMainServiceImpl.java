package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.ProductModeUpdateInventoryDTO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCancelOrderNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 取消购物订单-主模块
 * 
 * @author Sunny
 * @date 2017/04/06
 */
@Service("shoppingOrderCancelOrderTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.CANCEL_ORDER, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CANCEL_ORDER)
public class ShoppingOrderCancelOrderTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderCancelOrderNotification, Reply> {
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	/**
	 * 组装其他模块发送的数组
	 * 
	 * 取消订单向产品模块发送MQ消息，通过产品模块释放库存
	 */
    @Override
    public ShoppingOrderCancelOrderNotification selectNotification(Long shoppingOrderId) {
        // 获取购物订单以及订单项
        ShoppingOrderExtendBO shoppingOrderExtendBO = shoppingOrderService.get(shoppingOrderId);
        // 如果没有相关记录返回null
        if (shoppingOrderExtendBO == null || shoppingOrderExtendBO.getId() == null || shoppingOrderExtendBO.getId() <= 0) {
        	return null;
        }
        ShoppingOrderCancelOrderNotification shoppingOrderCancelOrderNotification = new ShoppingOrderCancelOrderNotification();
        // 组装发送数据
        shoppingOrderCancelOrderNotification.setShoppingOrderId(shoppingOrderId);
        List<ProductModeUpdateInventoryDTO> params = new ArrayList<>();
        for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
        	ProductModeUpdateInventoryDTO productModeUpdateInventoryParam = new ProductModeUpdateInventoryDTO();
        	productModeUpdateInventoryParam.setProdecutModelId(shoppingOrderItemBO.getProductModelId());
        	productModeUpdateInventoryParam.setQuantity(shoppingOrderItemBO.getQuantity());
        	productModeUpdateInventoryParam.setActivityProductModelId(shoppingOrderItemBO.getActivityProductModelId());
        	params.add(productModeUpdateInventoryParam);
        }
        shoppingOrderCancelOrderNotification.setParams(params);
        return shoppingOrderCancelOrderNotification;
    }
}
