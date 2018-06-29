package com.lawu.eshop.user.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.RegNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.constants.UserInviterTypeEnum;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.MerchantService;

/**
 * @author Leach
 * @date 2017/3/29
 */
@Service("memberRegTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.MEMBER_REGISTER, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_REG)
public class MemberRegTransactionMainServiceImpl extends AbstractTransactionMainService<RegNotification, Reply> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public RegNotification selectNotification(Long memberId) {
        MemberBO memberBO = memberService.getMemberById(memberId);
        if (memberBO == null) {
            return null;
        }
        RegNotification regNotification = new RegNotification();
        regNotification.setUserNum(memberBO.getNum());
        regNotification.setGmtCreate(memberBO.getGmtCreate());
        if (UserInviterTypeEnum.INVITER_TYPE_MEMBER.val.equals(memberBO.getInviterType())) {
            //用户
            MemberBO inviteBO = memberService.getMemberById(memberBO.getInviterId());
            if (inviteBO != null) {
                regNotification.setInviteNum(inviteBO.getNum());
                regNotification.setInviteAccount(inviteBO.getAccount());
            }
        } else {
            MerchantBO merchantBO = merchantService.getMerchantBOById(memberBO.getInviterId());
            if (merchantBO != null) {
                regNotification.setInviteNum(merchantBO.getNum());
                regNotification.setInviteAccount(merchantBO.getAccount());
            }
        }

        return regNotification;
    }


}
