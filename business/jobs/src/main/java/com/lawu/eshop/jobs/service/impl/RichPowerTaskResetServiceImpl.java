package com.lawu.eshop.jobs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.jobs.service.RichMerchantPowerTaskRecordService;
import com.lawu.eshop.jobs.service.RichPowerTaskRecordService;
import com.lawu.eshop.jobs.service.RichPowerTaskResetService;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/5/7.
 */
@Service
public class RichPowerTaskResetServiceImpl implements RichPowerTaskResetService {

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @Autowired
    private RichMerchantPowerTaskRecordService richMerchantPowerTaskRecordService;

    @Override
    public void executeTaskResetJob() {

        Result<Integer> totalResult = richPowerTaskRecordService.getPowerTaskRecordListCount();
        if (totalResult.getModel() != null && totalResult.getModel() > 0) {
            Integer totalSize = totalResult.getModel() / 100 + 1;
            for (int i = 1; i <= totalSize; i++) {
                richPowerTaskRecordService.resetTaskRecord(i, 100);
            }
        }

        Result<Integer> merchantResult = richMerchantPowerTaskRecordService.getMerchantPowerTaskRecordListCount();
        if (merchantResult.getModel() != null && merchantResult.getModel() > 0) {
            Integer merchantTotalSize = merchantResult.getModel() / 100 + 1;
            for (int i = 1; i <= merchantTotalSize; i++) {
                richMerchantPowerTaskRecordService.resetMerchantTaskRecord(i, 100);
            }
        }


    }

}
