package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.mall.constants.VerifyCodePurposeEnum;
import com.lawu.eshop.member.api.service.SmsRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class MockSmsRecordService extends BaseController implements SmsRecordService {


    @Override
    public Result sendSms(@PathVariable("mobile") String mobile, @RequestParam("ip") String ip, @RequestParam("purpose") VerifyCodePurposeEnum purpose) {
        return successCreated();
    }
}

