package com.lawu.eshop.member.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.member.api.service.MemberProfileService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.RichPowerTaskRecordService;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
@Component
public class RichPowerTaskEventHandle implements AsyncEventHandle<RichPowerTaskEvent> {

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberProfileService memberProfileService;

    @Override
    public void execute(RichPowerTaskEvent event) {
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setMemberNum(event.getMemberNum());
        taskRecordParam.setType(event.getType());
        taskRecordParam.setShoppingAmount(event.getShoppingAmount());

        if (event.getType() == PowerTaskTypeEnum.INVITE) {
            Result<String> result = memberService.getRichTaskInviterNum(event.getMemberNum());
            if (result.getModel() == null) {
                return;
            }
            if (result.getModel().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                taskRecordParam.setMemberNum(result.getModel());
                richPowerTaskRecordService.saveRichPowerTaskRecord(taskRecordParam);
            } else if (result.getModel().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                RichMerchantPowerTaskRecordParam richMerchantPowerTaskRecordParam = new RichMerchantPowerTaskRecordParam();
                richMerchantPowerTaskRecordParam.setMerchantNum(result.getModel());
                richMerchantPowerTaskRecordParam.setType(MerchantPowerTaskTypeEnum.INVITE_FRIEND);
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(richMerchantPowerTaskRecordParam);
            } else {
                return;
            }
            memberProfileService.updateHelpRichTask(event.getMemberNum());
            return;
        }
        richPowerTaskRecordService.saveRichPowerTaskRecord(taskRecordParam);
    }

}
