package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderPaymentsToMerchantNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.param.OrderReleaseFreezeParam;
import com.lawu.eshop.property.srv.service.OrderService;

/**
 * 订单超过退款时间，打款给商家-从模块
 *
 * @author Sunny
 * @date 2017年4月14日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_TRADING_SUCCESS_PAYMENTS_TO_MERCHANT)
public class ShoppingOrderPaymentsToMerchantTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingOrderPaymentsToMerchantNotification, Reply> {

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(ShoppingOrderPaymentsToMerchantNotification notification) {
        // 组装请求参数
        OrderReleaseFreezeParam param = new OrderReleaseFreezeParam();
        param.setOrderIds(notification.getShoppingOrderId().toString());
        param.setUserNums(notification.getMerchantNum());
        param.setRegionPaths(notification.getMerchantStoreRegionPath());
        param.setPayWays(notification.getPaymentMethod().getVal());
        param.setOrderItemProductName(notification.getOrderItemProductName());
        param.setMemberNum(notification.getMemberNum());
        param.setGmtExecute(notification.getGmtExecute());
        orderService.comfirmReleaseFreeze(param);
    }
}
