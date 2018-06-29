package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.property.DownProductNotification;
import com.lawu.eshop.mq.dto.property.StoreStatusNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;
import com.lawu.eshop.property.srv.mapper.BusinessDepositDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangqh
 * @date 2017/8/16.
 * 退款成功操作后，发送消息修改下架 - 主事务
 */
@Service("handleDepositRefundSuccessDownProductTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HANDLE_DESPOISIT_AUDIT_CANCEL_DOWN_PRODUCT, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_DEPOSIT_DOWN_PRODUCT)
public class HandleDepositRefundSuccessDownProductTransactionMainServiceImpl extends AbstractTransactionMainService<DownProductNotification, Reply> {
    @Autowired
    private BusinessDepositDOMapper businessDepositDOMapper;

    @Override
    public DownProductNotification selectNotification(Long depositId) {
        BusinessDepositDO depositDO = businessDepositDOMapper.selectByPrimaryKey(depositId);
        DownProductNotification notification = new DownProductNotification();
        notification.setMerchantId(depositDO.getBusinessId());
        return notification;
    }
}
