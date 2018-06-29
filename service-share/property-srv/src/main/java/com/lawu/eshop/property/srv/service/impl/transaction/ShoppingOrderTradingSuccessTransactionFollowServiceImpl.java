package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderTradingSuccessNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.param.OrderComfirmDataParam;
import com.lawu.eshop.property.param.OrderSysJobParam;
import com.lawu.eshop.property.srv.service.OrderService;

/**
 * 购物车订单确认收货事务-从模块
 *
 * @author Sunny
 * @date 2017年4月14日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_TRADING_SUCCESS)
public class ShoppingOrderTradingSuccessTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderTradingSuccessNotification, Reply> {

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(ShoppingOrderTradingSuccessNotification notification) {
        if (!notification.getIsAutoReceipt()) {
            // 组装请求参数
            OrderComfirmDataParam param = new OrderComfirmDataParam();
            param.setBizId(notification.getShoppingOrderId().toString());
            param.setTotalOrderMoney(notification.getOrderTotalPrice());
            param.setUserNum(notification.getMerchantNum());
            param.setOrderNum(notification.getOrderNum());
            orderService.comfirmDelivery(param);
        } else {
            OrderSysJobParam param = new OrderSysJobParam();
            param.setOrderActualMoney(notification.getOrderTotalPrice());
            param.setOrderIds(notification.getShoppingOrderId().toString());
            param.setUserNums(notification.getMerchantNum());
            param.setRegionPaths(notification.getMerchantStoreRegionPath());
            param.setPayWays(notification.getPaymentMethod().getVal());
            param.setOrderItemProductName(notification.getOrderItemProductName());
            param.setMemberNum(notification.getMemberNum());
            param.setGmtExecute(notification.getGmtExecute());
            orderService.comfirmSysJob(param);
        }
    }
}
