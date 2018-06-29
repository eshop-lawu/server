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
@Service("merchantRegTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.MERCHANT_REGISTER, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_REG)
public class MerchantRegTransactionMainServiceImpl extends AbstractTransactionMainService<RegNotification, Reply> {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MemberService memberService;

    @Override
    public RegNotification selectNotification(Long merchantId) {
        MerchantBO merchantBO = merchantService.getMerchantBOById(merchantId);
        if (merchantBO == null) {
            return null;
        }
        RegNotification regNotification = new RegNotification();
        regNotification.setUserNum(merchantBO.getNum());
        regNotification.setGmtCreate(merchantBO.getGmtCreate());
        if (UserInviterTypeEnum.INVITER_TYPE_MEMBER.val.equals(merchantBO.getInviterType())) {
            //用户
            MemberBO inviteBO = memberService.getMemberById(merchantBO.getInviterId());
            if (inviteBO != null) {
                regNotification.setInviteNum(inviteBO.getNum());
                regNotification.setInviteAccount(inviteBO.getAccount());
            }
        } else {
            MerchantBO inviteMerchant = merchantService.getMerchantBOById(merchantBO.getInviterId());
            if (inviteMerchant != null) {
                regNotification.setInviteAccount(inviteMerchant.getAccount());
                regNotification.setInviteNum(inviteMerchant.getNum());
            }
        }
        return regNotification;
    }


}
