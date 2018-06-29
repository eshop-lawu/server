package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.ProductModeUpdateInventoryDTO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderCreateOrderNotification;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 创建购物订单事务-主模块
 * 
 * @author Sunny
 * @date 2017/04/06
 */
@Service("shoppingOrderCreateOrderTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.CREATE_ORDER, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CREATE_ORDER)
public class ShoppingOrderCreateOrderTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderCreateOrderNotification, ShoppingOrderCreateOrderReply> {
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	/**
	 * 组装其他模块发送的数组
	 */
    @Override
    public ShoppingOrderCreateOrderNotification selectNotification(Long shoppingOrderId) {
        // 获取购物订单以及订单项
        ShoppingOrderExtendBO shoppingOrderExtendBO = shoppingOrderService.get(shoppingOrderId);
        
        // 如果没有相关记录返回null
        if (shoppingOrderExtendBO == null || shoppingOrderExtendBO.getId() == null || shoppingOrderExtendBO.getId() <= 0) {
        	return null;
        }
        
        ShoppingOrderCreateOrderNotification shoppingCartCreateOrderNotification = new ShoppingOrderCreateOrderNotification();
        
        // 组装发送数据
        shoppingCartCreateOrderNotification.setShoppingOrderId(shoppingOrderId);
        
        List<ProductModeUpdateInventoryDTO> params = new ArrayList<>();
        for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
        	ProductModeUpdateInventoryDTO productModeUpdateInventoryParam = new ProductModeUpdateInventoryDTO();
        	productModeUpdateInventoryParam.setProdecutModelId(shoppingOrderItemBO.getProductModelId());
        	productModeUpdateInventoryParam.setQuantity(shoppingOrderItemBO.getQuantity());
        	productModeUpdateInventoryParam.setActivityProductModelId(shoppingOrderItemBO.getActivityProductModelId());
        	params.add(productModeUpdateInventoryParam);
        }
        
        shoppingCartCreateOrderNotification.setParams(params);
        
        return shoppingCartCreateOrderNotification;
    }
    
    /**
     * 事务成功回调时
     * 根据购物订单id更新订单状态
     * 删除对应的购物车记录
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void afterSuccess(Long shoppingOrderId, ShoppingOrderCreateOrderReply reply) {
    	/*
    	 * 更新购物订单以及购物订单的状态为待支付状态
    	 * 删除购物车记录
    	 */
    	shoppingOrderService.minusInventorySuccess(shoppingOrderId, reply);
    }
}
