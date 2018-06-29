package com.lawu.eshop.activity.srv.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketAttendDetailService;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketInviteService;
import com.lawu.eshop.activity.srv.servcie.RichPowerTaskRecordService;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.mq.dto.order.CompleteShoppingTaskNotification;
import com.lawu.eshop.mq.dto.user.FreezeAccountNotification;
import com.lawu.eshop.mq.dto.user.RegHelpRedpacktUpdateHeadimgNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.mq.message.impl.AbstractMessageConsumerListener;

/**
 *
 */
@Service
public class MessageConsumerListener extends AbstractMessageConsumerListener {

    @Autowired
    private HelpRedpacketInviteService helpRedpacketInviteService;
    
    @Autowired
    private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;
    
    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @Override
    public void consumeMessage(String topic, String tags, Object message) {
        if (MqConstant.TOPIC_USER_SRV.equals(topic)) {
            if (MqConstant.TAG_REG_HELP_RED_PACKET_REFLASH_HEADIMG.equals(tags)) {
                RegHelpRedpacktUpdateHeadimgNotification notification = (RegHelpRedpacktUpdateHeadimgNotification) message;
                helpRedpacketInviteService.updateInviteRecordHeadimg(notification.getHelpUserNum(), notification.getHelpUserHeadimg());
                return;
            }
            
            // 用户账号冻结操作
            if (MqConstant.TAG_FREEZE_USER_ACCOUNT.equals(tags)) {
                FreezeAccountNotification freezeAccountNotification = (FreezeAccountNotification) message;
                helpRedpacketAttendDetailService.settingInvalid(freezeAccountNotification);
                return;
            }
            return;
        }
        
        if (MqConstant.TOPIC_ORDER_SRV.equals(topic)) {
            // 完成购物任务增加动力值
            if (MqConstant.TAG_COMPLETE_SHOPPING_TASK.equals(tags)) {
                CompleteShoppingTaskNotification notification = (CompleteShoppingTaskNotification) message;
                RichPowerTaskRecordParam param = new RichPowerTaskRecordParam();
                param.setType(PowerTaskTypeEnum.SHOPPING);
                param.setShoppingAmount(notification.getOrderAmount().intValue());
                param.setMemberNum(notification.getMemberNum());
                richPowerTaskRecordService.saveRichPowerTaskRecord(param);
                return;
            }
        }
    }
}
