package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.constants.AppTypeEnum;
import com.lawu.eshop.member.api.service.AppPatchVersionService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


/**
 * @author meishuquan
 * @date 2017/12/12.
 */
@Service
public class MockAppPatchVersionService extends BaseController implements AppPatchVersionService {

    @Override
    public Result<Integer> getAppPatchVersion(@RequestParam("typeEnum") AppTypeEnum typeEnum, @RequestParam("platform") Byte platform, @RequestParam("appVersion") String appVersion) {
        return successGet(new Integer(0));
    }

}
