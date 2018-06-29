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
 * 用户点E咻广告 - 主事务
 *
 * @author zhangrc
 * @date 2017/4/12
 */
@Service("adUserAddPointTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.AD_USER_ADD_POINT, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_ADD_POINT)
public class AdUserAddPointTransactionMainServiceImpl extends AbstractTransactionMainService<AdPointNotification, Reply> {

    @Autowired
    private PointPoolDOMapper pointPoolDOMapper;
    @Autowired
    private AdDOMapper adDOMapper;

    @Override
    public AdPointNotification selectNotification(Long pointPoolId) {
        PointPoolDO pointPoolDO = pointPoolDOMapper.selectByPrimaryKey(pointPoolId);
        AdPointNotification notification = new AdPointNotification();
        notification.setPoint(pointPoolDO.getPoint());
        notification.setUserNum(pointPoolDO.getMemberNum());
        notification.setAdId(pointPoolDO.getAdId());
        AdDO ad = adDOMapper.selectByPrimaryKey(pointPoolDO.getAdId());
        notification.setTitle(ad.getTitle());
        notification.setGmtExecute(pointPoolDO.getGmtCreate());
        return notification;
    }


}
