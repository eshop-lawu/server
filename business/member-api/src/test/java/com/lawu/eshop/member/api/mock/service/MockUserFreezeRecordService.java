package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.member.api.service.UserFreezeRecordService;
import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/9/11.
 */
@Service
public class MockUserFreezeRecordService implements UserFreezeRecordService {

    @Override
    public Result saveUserFreezeRecord(@RequestBody UserFreezeRecordParam param) {
        return null;
    }
}
