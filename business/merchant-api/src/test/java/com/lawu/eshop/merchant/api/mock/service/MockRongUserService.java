package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.RongUserService;
import com.lawu.eshop.user.dto.RongYunOnlineDTO;
import com.lawu.eshop.user.dto.RongYunRefreshDTO;
import com.lawu.eshop.user.dto.RongYunTokenDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockRongUserService extends BaseController implements RongUserService {
    @Override
    public Result<RongYunTokenDTO> getRongToken(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri) throws Exception {
        return successGet();
    }

    @Override
    public Result<RongYunOnlineDTO> checkOnline(@PathVariable("userId") String userId) throws Exception {
        return successGet();
    }

    @Override
    public Result<RongYunRefreshDTO> refreshUserInfo(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri) throws Exception {
        return successGet();
    }
}
