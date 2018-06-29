package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.ClickAdRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class MockClickAdRecordService extends BaseController implements ClickAdRecordService {


    @Override
    public Result setClickAdRecord(@RequestParam("key") String key) {
        return null;
    }

    @Override
    public Result<Boolean> getClickAdRecord(@RequestParam("key") String key) {
        return successCreated(false);
    }
}
