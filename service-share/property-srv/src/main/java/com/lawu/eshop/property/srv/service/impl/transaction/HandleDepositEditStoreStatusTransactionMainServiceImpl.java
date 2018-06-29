package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.property.StoreStatusNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.srv.constans.TransactionConstant;
import com.lawu.eshop.property.srv.domain.BusinessDepositDO;
import com.lawu.eshop.property.srv.mapper.BusinessDepositDOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangyong
 * @date 2017/6/7.
 * 已缴保证金待核实
 */
@Service("handleDepositEditStoreStatusTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HANDLE_DESPOISIT_SUCCESS, topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_DEPOSIT)
public class HandleDepositEditStoreStatusTransactionMainServiceImpl extends AbstractTransactionMainService<StoreStatusNotification, Reply> {
    @Autowired
    private BusinessDepositDOMapper businessDepositDOMapper;

    @Override
    public StoreStatusNotification selectNotification(Long depositId) {
        BusinessDepositDO depositDO = businessDepositDOMapper.selectByPrimaryKey(depositId);
        StoreStatusNotification notification = new StoreStatusNotification();
        notification.setMerchantId(depositDO.getBusinessId());
        notification.setShow(false);
        return notification;
    }
}
