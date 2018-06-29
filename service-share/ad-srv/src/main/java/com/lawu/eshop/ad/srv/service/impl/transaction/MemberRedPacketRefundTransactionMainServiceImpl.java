package com.lawu.eshop.ad.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.ad.param.UserPacketRefundParam;
import com.lawu.eshop.ad.srv.constants.TransactionConstant;
import com.lawu.eshop.ad.srv.service.UserRedPacketService;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.property.MemberRedPacketBackNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户红包过期退款-主事务
 */
@Service("memberRedPacketRefundTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.USER_REDPACKED_CANNEL_REFUND_MONEY, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_REDPACKET_CANNEL_REFUND_MONEY)
public class MemberRedPacketRefundTransactionMainServiceImpl extends AbstractTransactionMainService<MemberRedPacketBackNotification, Reply> {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @Override
    public MemberRedPacketBackNotification selectNotification(Long redPacketId) {

        UserPacketRefundParam userRedPacketDO = userRedPacketService.selectBackTotalMoney(redPacketId);

        MemberRedPacketBackNotification notification = new MemberRedPacketBackNotification();

        notification.setRedPacketId(userRedPacketDO.getRedId().toString());
        notification.setRefundMoney(userRedPacketDO.getRefundMoney().toString());
        notification.setTradeNo(userRedPacketDO.getThirdNo());
        notification.setTransactionPayTypeEnum(TransactionPayTypeEnum.getEnum(userRedPacketDO.getPayType()));
        notification.setUserNum(userRedPacketDO.getUserNum());
        notification.setGmtExecute(userRedPacketDO.getGmtModified());
        return notification;
    }
}
