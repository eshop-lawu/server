package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.CancelOrderRefundPointsNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 创建购物订单扣除积分事务-从模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_ORDER_SRV, tags = MqConstant.TAG_CANCEL_ORDER_REFUND_POINTS)
public class CancelOrderRefundPointsTransactionFollowServiceImpl extends
        AbstractTransactionFollowService<CancelOrderRefundPointsNotification, Reply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;
    
    @Override
    public void execute(CancelOrderRefundPointsNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getMemberNum());
        param.setPoint(notification.getPoint().toString());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_REDUND);
        param.setBizId(String.valueOf(notification.getShoppingOrderId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderAddPoint(param);
    }
}
