package com.lawu.eshop.ad.srv.service.impl.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.ad.constants.AdPayTypeEnum;
import com.lawu.eshop.ad.srv.constants.TransactionConstant;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.extend.PointPoolDOView;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.PointPoolDOMapperExtend;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 广告下架 - 主事务
 *
 * @author zhangrc
 * @date 2017/4/12
 */
@Service("adMerchantAddPointTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.AD_ME_ADD_POINT, topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_ME_ADD_POINT)
public class AdMerchantAddPointTransactionMainServiceImpl extends AbstractTransactionMainService<AdPointNotification, Reply> {

    @Autowired
    private AdDOMapper adDOMapper;

    @Autowired
    private PointPoolDOMapperExtend pointPoolDOMapperExtend;

    @Override
    public AdPointNotification selectNotification(Long id) {
        AdDO ad = adDOMapper.selectByPrimaryKey(id);

        AdPointNotification notification = new AdPointNotification();
        notification.setUserNum(ad.getMerchantNum());
        notification.setAdId(id);
        notification.setAdType(ad.getType());
        notification.setClientType(ad.getClientType());
        if (ad.getPayType() == null) {
            notification.setPayType(AdPayTypeEnum.POINT.getVal());
        } else {
            notification.setPayType(ad.getPayType());
        }
        if (ad.getThirdNumber() == null) {
            notification.setTradeNo("");
        } else {
            notification.setTradeNo(ad.getThirdNumber());
        }
        if (ad.getType() == AdTypeEnum.AD_TYPE_PRAISE.getVal() || ad.getType() == AdTypeEnum.AD_TYPE_PACKET.getVal()) {
            PointPoolDOView view = pointPoolDOMapperExtend.getTotlePoint(id);
            BigDecimal subMoney = new BigDecimal(0);
            // 剩余积分
            if (view == null) {
                subMoney = ad.getTotalPoint().subtract(BigDecimal.valueOf(0));
            } else {
                subMoney = ad.getTotalPoint().subtract(view.getPoint());
            }
            notification.setPoint(subMoney);
        } else {
            Integer hits = ad.getHits();
            BigDecimal point = ad.getPoint();

            notification.setPoint(ad.getTotalPoint().subtract(point.multiply(new BigDecimal(hits))));
        }
        notification.setGmtExecute(ad.getGmtModified());
        return notification;
    }

}
