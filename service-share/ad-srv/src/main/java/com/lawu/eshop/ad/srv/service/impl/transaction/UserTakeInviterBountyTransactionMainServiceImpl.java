package com.lawu.eshop.ad.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.ad.srv.constants.TransactionConstant;
import com.lawu.eshop.ad.srv.domain.TakeInviterBountyDetailDO;
import com.lawu.eshop.ad.srv.mapper.TakeInviterBountyDetailDOMapper;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mq.dto.ad.UserTakeInviterBountyNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 领取邀请奖励金 - 主事务
 *
 * @author zhangrc
 * @date 2017/12/21
 */
@Service("userTakeInviterBountyTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.USER_TKAE_INVITER_BOUNTY, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_TAKE_INVITER_BOUNTY)
public class UserTakeInviterBountyTransactionMainServiceImpl extends AbstractTransactionMainService<UserTakeInviterBountyNotification, Reply> {

    @Autowired
    private TakeInviterBountyDetailDOMapper takeInviterBountyDetailDOMapper;

    @Override
    public UserTakeInviterBountyNotification selectNotification(Long id) {
        TakeInviterBountyDetailDO takeInviterBountyDetailDO = takeInviterBountyDetailDOMapper.selectByPrimaryKey(id);
        UserTakeInviterBountyNotification notification = new UserTakeInviterBountyNotification();
        notification.setUserNum(takeInviterBountyDetailDO.getUserNum());
        notification.setMoney(takeInviterBountyDetailDO.getPoint());
        notification.setId(takeInviterBountyDetailDO.getId());

        if (takeInviterBountyDetailDO.getUserNum().startsWith("M")) {
            notification.setUserType(UserTypeEnum.MEMBER);
        } else {
            notification.setUserType(UserTypeEnum.MERCHANT);
        }
        notification.setGmtExecute(takeInviterBountyDetailDO.getGmtCreate());
        return notification;
    }


}
