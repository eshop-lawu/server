package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.order.ProductModeUpdateInventoryDTO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessIncreaseSalesNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.constants.ShoppingOrderStatusEnum;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderItemBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;

/**
 * 确认收货
 * 添加商品销量-主事务
 * 
 * @author Sunny
 * @date 2017/04/14
 */
@Service("shoppingOrderTradingSuccessIncreaseSalesTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.TRADING_SUCCESS_INCREASE_SALES, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_TRADING_SUCCESS_INCREASE_SALES)
public class ShoppingOrderTradingSuccessIncreaseSalesTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderTradingSuccessIncreaseSalesNotification, Reply> {
	
	@Autowired
	private ShoppingOrderService shoppingOrderService;
	
	/**
	 * 组装其他模块发送的数组
	 */
    @Override
    public ShoppingOrderTradingSuccessIncreaseSalesNotification selectNotification(Long shoppingOrderId) {
    	ShoppingOrderTradingSuccessIncreaseSalesNotification rtn = new ShoppingOrderTradingSuccessIncreaseSalesNotification();
    	
    	// 获取购物订单以及订单项
        ShoppingOrderExtendBO shoppingOrderExtendBO = shoppingOrderService.get(shoppingOrderId);
    	
    	if (shoppingOrderExtendBO == null || shoppingOrderExtendBO.getId() == null || shoppingOrderExtendBO.getId() <= 0) {
    		return rtn;
    	}
    	
    	rtn.setShoppingOrderId(shoppingOrderId);
    	List<ProductModeUpdateInventoryDTO> params = new ArrayList<>();
        for (ShoppingOrderItemBO shoppingOrderItemBO : shoppingOrderExtendBO.getItems()) {
        	// 排除已经退完款的订单项
        	if (!ShoppingOrderStatusEnum.CANCEL_TRANSACTION.equals(shoppingOrderItemBO.getOrderStatus())) {
	        	ProductModeUpdateInventoryDTO productModeUpdateInventoryParam = new ProductModeUpdateInventoryDTO();
	        	productModeUpdateInventoryParam.setProdecutModelId(shoppingOrderItemBO.getProductModelId());
	        	productModeUpdateInventoryParam.setQuantity(shoppingOrderItemBO.getQuantity());
	        	params.add(productModeUpdateInventoryParam);
        	}
        }
        rtn.setParams(params);
    	
        return rtn;
    }
}
