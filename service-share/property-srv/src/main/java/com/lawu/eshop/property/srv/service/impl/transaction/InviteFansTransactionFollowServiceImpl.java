package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.user.InviteFansNotification;
import com.lawu.eshop.mq.dto.user.reply.InviteFansReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 邀请粉丝 - 从事务
 * @author meishuquan
 * @date 2017/11/6
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_INVITE_FANS)
public class InviteFansTransactionFollowServiceImpl extends AbstractTransactionFollowService<InviteFansNotification, InviteFansReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Override
    public void execute(InviteFansNotification notification) {
        PropertyInfoDataParam propertyInfoDataParam = new PropertyInfoDataParam();
        propertyInfoDataParam.setBizId(String.valueOf(notification.getFansInviteContentId()));
        propertyInfoDataParam.setUserNum(notification.getUserNum());
        propertyInfoDataParam.setPoint(String.valueOf(notification.getInviteFansCount()));
        propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INVITE_FANS);
        propertyInfoDataParam.setMerchantId(notification.getMerchantId());
        propertyInfoDataParam.setRegionName(notification.getRegionName());
        propertyInfoDataParam.setInviteFansCount(notification.getInviteFansCount());
        propertyInfoDataParam.setSex(notification.getSex());
        propertyInfoDataParam.setAge(notification.getAge());
        propertyInfoDataParam.setGmtExecute(notification.getGmtExecute());
        Long fansInviteDetailId = propertyInfoDataService.doHanlderMinusPointByFans(propertyInfoDataParam);
        notification.setFansInviteDetailId(fansInviteDetailId);
    }

    @Override
    public InviteFansReply getReply(InviteFansNotification notification) {
        InviteFansReply inviteFansReply = new InviteFansReply();
        inviteFansReply.setFansInviteDetailId(notification.getFansInviteDetailId());
        return inviteFansReply;
    }
}
