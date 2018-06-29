package com.lawu.eshop.member.api.mock.service;

import com.lawu.eshop.member.api.service.RongUserService;
import com.lawu.eshop.user.dto.RongYunOnlineDTO;
import com.lawu.eshop.user.dto.RongYunRefreshDTO;
import com.lawu.eshop.user.dto.RongYunTokenDTO;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class MockRongUserService implements RongUserService {
    @Override
    public Result<RongYunTokenDTO> getRongToken(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri) {
        return null;
    }

    @Override
    public Result<RongYunOnlineDTO> checkOnline(@PathVariable("userId") String userId)  {
        return null;
    }

    @Override
    public Result<RongYunRefreshDTO> refreshUserInfo(@RequestParam("userId") String userId, @RequestParam("name") String name, @RequestParam("portraitUri") String portraitUri)  {
        return null;
    }
}
