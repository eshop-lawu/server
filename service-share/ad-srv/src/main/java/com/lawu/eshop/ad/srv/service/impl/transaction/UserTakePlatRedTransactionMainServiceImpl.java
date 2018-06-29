package com.lawu.eshop.ad.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.ad.srv.constants.TransactionConstant;
import com.lawu.eshop.ad.srv.domain.TakePlatformRedPacketDO;
import com.lawu.eshop.ad.srv.mapper.TakePlatformRedPacketDOMapper;
import com.lawu.eshop.mq.dto.ad.UserTakePlatRedPacketNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 领取平台红包 - 主事务
 *
 * @author zhangrc
 * @date 2017/10/19
 */
@Service("userTakePlatRedTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.USER_TKAE_PLAT_MONEY, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_TAKE_PLAT_RED)
public class UserTakePlatRedTransactionMainServiceImpl extends AbstractTransactionMainService<UserTakePlatRedPacketNotification, Reply> {

    @Autowired
    private TakePlatformRedPacketDOMapper takePlatformRedPacketDOMapper;

    @Override
    public UserTakePlatRedPacketNotification selectNotification(Long id) {
        TakePlatformRedPacketDO takePlatformRedPacketDO = takePlatformRedPacketDOMapper.selectByPrimaryKey(id);
        UserTakePlatRedPacketNotification notification = new UserTakePlatRedPacketNotification();
        notification.setUserNum(takePlatformRedPacketDO.getUserNum());
        notification.setMoney(takePlatformRedPacketDO.getPoint());
        notification.setId(takePlatformRedPacketDO.getId());
        notification.setGmtExecute(takePlatformRedPacketDO.getGmtCreate());
        return notification;
    }


}
