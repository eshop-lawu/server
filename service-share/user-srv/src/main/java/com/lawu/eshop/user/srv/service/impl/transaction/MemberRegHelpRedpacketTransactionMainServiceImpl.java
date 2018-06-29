package com.lawu.eshop.user.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.RegHelpRedpacktNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.service.MemberService;

/**
 * @author yangqh
 * @date 2017/12/28
 */
@Service("memberRegHelpRedpacketTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HELP_PACKET, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_REG_HELP_RED_PACKET)
public class MemberRegHelpRedpacketTransactionMainServiceImpl extends AbstractTransactionMainService<RegHelpRedpacktNotification, Reply> {

    @Autowired
    private MemberService memberService;

    @Override
    public RegHelpRedpacktNotification selectNotification(Long memberId) {
        MemberBO memberBO = memberService.getMemberById(memberId);
        if (memberBO == null) {
            return null;
        }
        MemberBO memberBO1 = memberService.getMemberById(memberBO.getInviterId());
        if(memberBO1 == null){
            return null;
        }
        RegHelpRedpacktNotification regNotification = new RegHelpRedpacktNotification();
        regNotification.setHelpUserNum(memberBO.getNum());
        regNotification.setHelpUserAccount(memberBO.getAccount());
        regNotification.setHelpUserHeadimg(memberBO.getHeadimg());
        regNotification.setAttendUserNum(memberBO1.getNum());
        regNotification.setRegOrigin(memberBO.getRegOrigin());
        return regNotification;
    }


}
