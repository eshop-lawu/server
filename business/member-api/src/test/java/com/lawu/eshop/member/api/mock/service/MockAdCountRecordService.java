package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.AdCountRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockAdCountRecordService extends BaseController implements AdCountRecordService {

    @Override
    public Result<Object> getAdCountRecord(@RequestParam("id") Long id) {
        return null;
    }

    @Override
    public Result updateAdCountRecord(@RequestParam("id") Long id) {
        return null;
    }

    @Override
    public Result setUserRedPacketCountRecord(@RequestParam("id") Long id, @RequestParam("count") Integer count) {
        return null;
    }
}
