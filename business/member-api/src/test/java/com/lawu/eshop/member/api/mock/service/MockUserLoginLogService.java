package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.member.api.service.UserLoginLogService;
import com.lawu.eshop.user.param.UserLoginLogParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/9/8.
 */
@Service
public class MockUserLoginLogService extends BaseController implements UserLoginLogService {

    @Override
    public Result saveLoginLog(@RequestBody UserLoginLogParam loginLogParam) {
        return null;
    }
}
