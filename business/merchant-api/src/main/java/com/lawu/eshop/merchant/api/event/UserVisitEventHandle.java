package com.lawu.eshop.merchant.api.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.framework.web.impl.event.UserVisitEvent;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.merchant.api.service.UserFreezeRecordService;
import com.lawu.eshop.merchant.api.service.UserVisitService;
import com.lawu.eshop.user.dto.MobileDTO;
import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
@Component
public class UserVisitEventHandle implements AsyncEventHandle<UserVisitEvent> {

    @Autowired
    private UserVisitService userVisitService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantApiConfig merchantApiConfig;

    @Autowired
    private UserFreezeRecordService userFreezeRecordService;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public void execute(UserVisitEvent event) {
        String nowTimeStr = DateUtil.getIntDate();
        Long currTime = System.currentTimeMillis();
        Result<Long> timeResult = userVisitService.addUserVisitCountAndTime(event.getUserNum(), nowTimeStr, event.getUserId(), event.getUserType(), String.valueOf(currTime));
        if (currTime - timeResult.getModel() < merchantApiConfig.getVisitTimeInterval()) {
            userVisitService.addUserVisitFrequency(event.getUserId(), event.getUserType(), merchantApiConfig.getExpireTime());
        }

        //查询时间周期内访问接口频率
        Result<Integer> frequencyResult = userVisitService.getUserVisitFrequency(event.getUserId(), event.getUserType());
        if (frequencyResult.getModel() >= merchantApiConfig.getVisitFrequencyCount()) {
            userVisitService.delUserVisitFrequency(event.getUserId(), event.getUserType());

            Result<MobileDTO> dtoResult = merchantService.selectMobile(event.getUserId());
            //保存冻结记录
            UserFreezeRecordParam param = new UserFreezeRecordParam();
            param.setUserNum(event.getUserNum());
            param.setAccount(dtoResult.getModel().getMobile());
            param.setUserType(event.getUserType().getValue());
            param.setCause("访问频率过高(" + merchantApiConfig.getVisitFrequencyCount() + "次/" + merchantApiConfig.getExpireTime() + "分)，系统冻结");
            userFreezeRecordService.saveUserFreezeRecord(param);
        }

        Result<Integer> visitTimesResult = userVisitService.getDailyVisitTimes(event.getUserNum());
        if (visitTimesResult.getModel() == 1) {
            RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
            taskRecordParam.setMerchantNum(event.getUserNum());
            taskRecordParam.setType(MerchantPowerTaskTypeEnum.LOGIN);
            eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
        }
    }
}
