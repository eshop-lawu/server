package com.lawu.eshop.merchant.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.merchant.api.service.MerchantProfileService;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.merchant.api.service.RichPowerTaskRecordService;
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
    private MerchantService merchantService;

    @Autowired
    private MerchantProfileService merchantProfileService;

    @Override
    public void execute(RichPowerTaskEvent event) {
        RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
        taskRecordParam.setMerchantNum(event.getMerchantNum());
        taskRecordParam.setType(event.getType());
        taskRecordParam.setPoint(event.getPoint());
        taskRecordParam.setFensInviteCount(event.getFensInviteCount());

        if (event.getType() == MerchantPowerTaskTypeEnum.INVITE_FRIEND) {
            Result<String> result = merchantService.getRichTaskInviterNum(event.getMerchantNum());
            if (result.getModel() == null) {
                return;
            }
            if (result.getModel().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                RichPowerTaskRecordParam richPowerTaskRecordParam = new RichPowerTaskRecordParam();
                richPowerTaskRecordParam.setMemberNum(result.getModel());
                richPowerTaskRecordParam.setType(PowerTaskTypeEnum.INVITE);
                richPowerTaskRecordService.saveRichPowerTaskRecord(richPowerTaskRecordParam);
            } else if (result.getModel().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                taskRecordParam.setMerchantNum(result.getModel());
                richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
            } else {
                return;
            }
            merchantProfileService.updateHelpRichTask(event.getMerchantNum());
            return;
        }
        richPowerTaskRecordService.saveRichMerchantPowerTaskRecord(taskRecordParam);
    }

}
