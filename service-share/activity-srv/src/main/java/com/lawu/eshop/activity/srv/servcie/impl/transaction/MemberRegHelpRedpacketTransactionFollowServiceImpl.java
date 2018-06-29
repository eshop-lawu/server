package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.activity.param.HelpRedpacketInviteRegParam;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketAttendDetailService;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketInviteService;
import com.lawu.eshop.mq.dto.user.RegHelpRedpacktNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangqh
 * @date 2017/12/28
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_REG_HELP_RED_PACKET)
public class MemberRegHelpRedpacketTransactionFollowServiceImpl extends AbstractTransactionFollowService<RegHelpRedpacktNotification, Reply> {

    @Autowired
    private HelpRedpacketInviteService helpRedpacketInviteService;

    @Autowired
    private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;

    @Override
    public void execute(RegHelpRedpacktNotification notification) {
        if(StringUtils.isBlank(notification.getRegOrigin())){
            notification.setRegOrigin(String.valueOf(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID));
        }
        HelpRedpacketAttendDetailBO helpRedpacketAttendDetailBO = helpRedpacketAttendDetailService.selectByAttendUserNum(Integer.valueOf(notification.getRegOrigin()), notification.getAttendUserNum());
        HelpRedpacketInviteRegParam param = new HelpRedpacketInviteRegParam();
        param.setAttendId(helpRedpacketAttendDetailBO.getId());
        param.setAttendUserNum(notification.getAttendUserNum());
        param.setHelpUserNum(notification.getHelpUserNum());
        param.setHelpUserAccount(notification.getHelpUserAccount());
        param.setHelpUserHeadimg(notification.getHelpUserHeadimg());
        param.setRegOrigin(notification.getRegOrigin());
        helpRedpacketInviteService.doRegHelp(param);

    }
}
