package com.lawu.eshop.user.srv.service.impl.transaction;

import java.util.Map;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.InviteFansNotification;
import com.lawu.eshop.mq.dto.user.reply.InviteFansReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.service.FansInviteContentService;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.eshop.user.srv.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 邀请粉丝 - 主事务
 *
 * @author meishuquan
 * @date 2017/11/6
 */
@Service("inviteFansTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.INVITE_FANS, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_INVITE_FANS)
public class InviteFansTransactionMainServiceImpl extends AbstractTransactionMainService<InviteFansNotification, InviteFansReply> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private FansMerchantService fansMerchantService;

    @Autowired
    private FansInviteContentService fansInviteContentService;

    @Override
    public InviteFansNotification selectNotification(Long id) {
        FansInviteContentBO fansInviteContentBO = fansInviteContentService.selectInviteContentById(id);
        Map<String, Object> inviteFansMap = fansInviteContentService.getInviteFansMap();

        InviteFansNotification fansNotification = new InviteFansNotification();
        fansNotification.setFansInviteContentId(id);
        fansNotification.setUserNum(fansInviteContentBO.getMerchantNum());
        fansNotification.setPoint(inviteFansMap.get("inviteFansCount").toString());
        fansNotification.setMerchantId(fansInviteContentBO.getMerchantId());
        fansNotification.setRegionName(inviteFansMap.get("regionName").toString());
        fansNotification.setInviteFansCount(Integer.valueOf(inviteFansMap.get("inviteFansCount").toString()));
        fansNotification.setSex(Byte.valueOf(inviteFansMap.get("sex").toString()));
        fansNotification.setAge(inviteFansMap.get("age").toString());
        fansNotification.setGmtExecute(fansInviteContentBO.getGmtCreate());
        return fansNotification;
    }

    @Override
    public void afterSuccess(Long relateId, InviteFansReply reply) {
        fansInviteContentService.updateFansInviteDetailId(relateId, reply.getFansInviteDetailId());
    }

}
