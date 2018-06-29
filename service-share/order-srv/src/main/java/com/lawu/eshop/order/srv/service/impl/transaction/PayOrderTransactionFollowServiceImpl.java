package com.lawu.eshop.order.srv.service.impl.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.order.PayOrderNotification;
import com.lawu.eshop.mq.dto.user.FansInfo;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.order.constants.PayOrderStatusEnum;
import com.lawu.eshop.order.srv.domain.PayOrderDO;
import com.lawu.eshop.order.srv.domain.PayOrderDOExample;
import com.lawu.eshop.order.srv.mapper.PayOrderDOMapper;
import com.lawu.mq.message.MessageProducerService;

/**
 * 买单时支付成功修改买单状态 - 从事务
 *
 * @author zhangyong
 * @date 2017/4/11.
 */
@Service("payOrderTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_PAYORDER)
public class PayOrderTransactionFollowServiceImpl extends AbstractTransactionFollowService<PayOrderNotification, Reply> {

    @Autowired
    private PayOrderDOMapper payOrderDOMapper;

    @Autowired
    private MessageProducerService messageProducerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(PayOrderNotification notification) {
        Long payOrderId = notification.getPayOrderId();
        // 更改订单状态
        PayOrderDO payOrderDO = new PayOrderDO();
        payOrderDO.setId(payOrderId);
        payOrderDO.setStatus(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
        payOrderDO.setPayType(notification.getPayType());
        payOrderDO.setThirdNumber(notification.getThirdNum());
        payOrderDO.setGmtModified(new Date());
        payOrderDOMapper.updateByPrimaryKeySelective(payOrderDO);

        PayOrderDO oldOrder = payOrderDOMapper.selectByPrimaryKey(payOrderId);
        // 发送消息增加买单笔数并成为粉丝
        FansInfo fansInfo = new FansInfo();
        fansInfo.setMemberId(oldOrder.getMemberId());
        fansInfo.setMerchantId(oldOrder.getMerchantId());

        // 查询当前商家买单笔数
        PayOrderDOExample example = new PayOrderDOExample();
        example.createCriteria().andMerchantIdEqualTo(oldOrder.getMerchantId()).andStatusEqualTo(PayOrderStatusEnum.STATUS_PAY_SUCCESS.getVal());
        int count = payOrderDOMapper.countByExample(example);
        fansInfo.setPayOrderCount(count);

        messageProducerService.sendMessage(MqConstant.TOPIC_ORDER_SRV, MqConstant.TAG_BUY_NUMBERS, fansInfo);
    }
}