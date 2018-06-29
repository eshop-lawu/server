package com.lawu.eshop.ad.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.service.UserRedPacketService;
import com.lawu.eshop.mq.dto.property.MemberRedPacketPaymentNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户发红包修改红包状态-从事务
 */
@Service("memberRedPacketPaymentTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_MEMBER_RED_PACKET)
public class MemberRedPacketPaymentTransactionFollowServiceImpl extends AbstractTransactionFollowService<MemberRedPacketPaymentNotification, Reply> {

    @Autowired
    private UserRedPacketService userRedPacketService;

    /**
     * 接收资产模块支付购物订单时发送的消息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(MemberRedPacketPaymentNotification notification) {
        UserRedPacketUpdateParam param = new UserRedPacketUpdateParam();
        param.setPayType(notification.getPaymentMethod());
        param.setRedId(Long.valueOf(notification.getRedPacketId()));
        param.setThirdNum(notification.getThirdNumber());
        userRedPacketService.updateUserPacketInfo(param);
    }
}