package com.lawu.eshop.ad.srv.service.impl.transaction;

import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.ad.srv.constants.TransactionConstant;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 用户领取商家发的红包---主事务
 *
 * @author zhangrc
 * @date 2017/4/12
 */
@Service("userSweepRedTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.USER_SWEET_MONEY, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_SWEEP_RED)
public class UserSweepRedTransactionMainServiceImpl extends AbstractTransactionMainService<AdPointNotification, Reply> {

    @Autowired
    private PointPoolDOMapper pointPoolDOMapper;
    @Autowired
    private AdDOMapper adDOMapper;

    @Override
    public AdPointNotification selectNotification(Long id) {
        PointPoolDO pointPoolDO = pointPoolDOMapper.selectByPrimaryKey(id);
        AdPointNotification notification = new AdPointNotification();
        notification.setUserNum(pointPoolDO.getMemberNum());
        notification.setPoint(pointPoolDO.getPoint());
        notification.setAdId(pointPoolDO.getAdId());
        AdDO ad = adDOMapper.selectByPrimaryKey(pointPoolDO.getAdId());
        notification.setTitle(ad.getMerchantStoreName());
        notification.setGmtExecute(pointPoolDO.getGmtCreate());
        return notification;
    }


}
