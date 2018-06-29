package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.order.ShoppingRefundAgreeToRefundNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.OrderRefundDataParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.OrderService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;
import com.lawu.utils.NumberUtil;

/**
 * 商家确认退款事务-从模块
 *
 * @author Sunny
 * @date 2017年4月17日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_AGREE_TO_REFUND)
public class ShoppingRefundAgreeToRefundTransactionFollowServiceImpl extends AbstractTransactionFollowService<ShoppingRefundAgreeToRefundNotification, Reply> {
    
    @Autowired
    private PropertyInfoDataService propertyInfoDataService;
    
    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(ShoppingRefundAgreeToRefundNotification notification) {
        // 组装请求参数
        OrderRefundDataParam orderRefundDataParam = new OrderRefundDataParam();
        orderRefundDataParam.setLast(notification.getIsLast());
        orderRefundDataParam.setOrderId(notification.getShoppingOrderId().toString());
        orderRefundDataParam.setOrderItemIds(notification.getShoppingOrderItemId().toString());
        orderRefundDataParam.setRefundMoney(NumberUtil.format(notification.getActualAmount()));
        orderRefundDataParam.setRefundExtraMoney(NumberUtil.format(notification.getAmount()));
        orderRefundDataParam.setFreightMoney(NumberUtil.format(notification.getFreightPrice()));
        orderRefundDataParam.setSideUserNum(notification.getMemberNum());
        orderRefundDataParam.setUserNum(notification.getMerchantNum());
        orderRefundDataParam.setTradeNo(notification.getThirdNumber());
        orderRefundDataParam.setOrderRefundStatusEnum(notification.getStatus());
        orderRefundDataParam.setTransactionPayTypeEnum(TransactionPayTypeEnum.getEnum(notification.getPaymentMethod().getVal()));
        orderRefundDataParam.setOrderItemProdcutName(notification.getOrderItemProdcutName());
        orderRefundDataParam.setGmtExecute(notification.getGmtExecute());
        try {
            orderService.doRefundScopeInside(orderRefundDataParam);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getMemberNum());
        param.setPoint(notification.getPoint().toString());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_REDUND);
        param.setBizId(String.valueOf(notification.getShoppingOrderItemId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderAddPoint(param);
    }
}
