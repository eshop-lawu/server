package com.lawu.eshop.user.srv.service.impl.transaction;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.MerchantFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.service.FansInviteContentService;

/**
 * 商家邀请粉丝退还积分 - 主事务
 *
 * @author meishuquan
 * @date 2017/9/21
 */
@Service("merchantFansTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.MERCHANT_FANS, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_MERCHANT_FANS)
public class MerchantFansTransactionMainServiceImpl extends AbstractTransactionMainService<MerchantFansNotification, Reply> {

    @Autowired
    private FansInviteContentService fansInviteContentService;

    @Override
    public MerchantFansNotification selectNotification(Long id) {
        FansInviteContentBO fansInviteContentBO = fansInviteContentService.selectInviteContentById(id);
        MerchantFansNotification fansNotification = new MerchantFansNotification();
        fansNotification.setUserNum(fansInviteContentBO.getMerchantNum());
        fansNotification.setPoint(BigDecimal.valueOf(fansInviteContentBO.getRefuseNumber()));
        fansNotification.setBizId(id);
        fansNotification.setGmtExecute(fansInviteContentBO.getGmtModified());
        return fansNotification;
    }

}
