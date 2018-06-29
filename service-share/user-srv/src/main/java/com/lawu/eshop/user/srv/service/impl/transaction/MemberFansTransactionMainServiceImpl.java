package com.lawu.eshop.user.srv.service.impl.transaction;

import java.util.List;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.MemberFansNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.domain.FansInviteResultDO;
import com.lawu.eshop.user.srv.domain.FansInviteResultDOExample;
import com.lawu.eshop.user.srv.mapper.FansInviteResultDOMapper;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.MerchantStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author meishuquan
 * @date 2017/9/21
 */
@Service("memberFansTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.MEMBER_FANS, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_MEMBER_FANS)
public class MemberFansTransactionMainServiceImpl extends AbstractTransactionMainService<MemberFansNotification, Reply> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private FansMerchantService fansMerchantService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private FansInviteResultDOMapper fansInviteResultDOMapper;

    @Override
    public MemberFansNotification selectNotification(Long id) {
        FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchantById(id);
        MemberBO memberBO = memberService.getMemberById(fansMerchantBO.getMemberId());
        MerchantStoreBO merchantStoreBO = merchantStoreService.selectMerchantStore(fansMerchantBO.getMerchantId());
        MemberFansNotification fansNotification = new MemberFansNotification();
        fansNotification.setUserNum(memberBO.getNum());
        fansNotification.setMerchantStoreName(merchantStoreBO.getName());

        //获取用户同意邀请时间
        FansInviteResultDOExample example = new FansInviteResultDOExample();
        example.createCriteria().andMemberIdEqualTo(fansMerchantBO.getMemberId()).andMerchantIdEqualTo(fansMerchantBO.getMerchantId());
        List<FansInviteResultDO> fansInviteResultList = fansInviteResultDOMapper.selectByExample(example);
        if(fansInviteResultList != null && !fansInviteResultList.isEmpty()){
            fansNotification.setGmtExecute(fansInviteResultList.get(0).getGmtCreate());
        }

        return fansNotification;
    }

}
