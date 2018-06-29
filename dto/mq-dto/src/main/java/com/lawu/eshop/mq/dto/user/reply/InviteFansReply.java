package com.lawu.eshop.mq.dto.user.reply;

import com.lawu.compensating.transaction.Reply;

/**
 * @author meishuquan
 * @date 2017/11/6.
 */
public class InviteFansReply extends Reply {

    private static final long serialVersionUID = 1441130404116713356L;

    private Long fansInviteDetailId;

    public Long getFansInviteDetailId() {
        return fansInviteDetailId;
    }

    public void setFansInviteDetailId(Long fansInviteDetailId) {
        this.fansInviteDetailId = fansInviteDetailId;
    }
}
