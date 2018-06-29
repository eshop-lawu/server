package com.lawu.eshop.order.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.constants.TransactionConstant;
import com.lawu.eshop.order.srv.service.ShoppingOrderItemService;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.utils.NumberUtil;

/**
 * 购物车订单确认收货事务-主模块
 *
 * @author Sunny
 * @date 2017/04/14
 */
@Service("shoppingOrderTradingSuccessTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.TRADING_SUCCESS, topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_TRADING_SUCCESS)
public class ShoppingOrderTradingSuccessTransactionMainServiceImpl extends AbstractTransactionMainService<ShoppingOrderTradingSuccessNotification, Reply> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Autowired
    private ShoppingOrderItemService shoppingOrderItemService;

    /**
     * 组装其他模块发送的数组
     */
    @Override
    public ShoppingOrderTradingSuccessNotification selectNotification(Long shoppingOrderId) {
        ShoppingOrderTradingSuccessNotification rtn = null;
        ShoppingOrderBO shoppingOrderBO = shoppingOrderService.getShoppingOrder(shoppingOrderId);
        if (shoppingOrderBO == null || shoppingOrderBO.getId() <= 0) {
            return rtn;
        }
        rtn = new ShoppingOrderTradingSuccessNotification();
        rtn.setPaymentMethod(TransactionPayTypeEnum.getEnum(shoppingOrderBO.getPaymentMethod().getVal()));
        rtn.setMerchantNum(shoppingOrderBO.getMerchantNum());
        rtn.setMerchantStoreRegionPath(shoppingOrderBO.getMerchantStoreRegionPath());
        rtn.setShoppingOrderId(shoppingOrderId);
        rtn.setOrderTotalPrice(NumberUtil.format(shoppingOrderBO.getActualAmount()));
        rtn.setIsAutoReceipt(shoppingOrderBO.getIsAutomaticReceipt());
        rtn.setOrderNum(shoppingOrderBO.getOrderNum());
        String orderItemProductName = shoppingOrderItemService.getOrderItemProductName(shoppingOrderId);
        rtn.setOrderItemProductName(orderItemProductName);
        rtn.setMemberNum(shoppingOrderBO.getMemberNum());
        rtn.setGmtExecute(shoppingOrderBO.getGmtModified());
        return rtn;
    }
}
