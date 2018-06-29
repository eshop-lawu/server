package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.member.api.service.UserVisitService;
import com.lawu.framework.web.Result;


@Service
class MockUserVisitService implements UserVisitService {

    @Override
    public Result addUserVisitCount(@RequestParam("userNum") String userNum, @RequestParam("nowTimeStr") String nowTimeStr, @RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        return null;
    }

    @Override
    public Result addUserVisitTime(@RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        return null;
    }

    @Override
    public Result addUserVisitFrequency(@RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type, @RequestParam("expireTime") Integer expireTime) {
        return null;
    }

    @Override
    public Result<Long> getUserVisitTime(@RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        return null;
    }

    @Override
    public Result<Integer> getUserVisitFrequency(@RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        return null;
    }

    @Override
    public Result delUserVisitFrequency(@RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        return null;
    }

    @Override
    public Result<Long> addUserVisitCountAndTime(@RequestParam("userNum") String userNum, @RequestParam("nowTimeStr") String nowTimeStr, @RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type, @RequestParam("currTime") String currTime) {
        return null;
    }

    @Override
    public Result<Integer> getDailyVisitTimes(@RequestParam("userNum") String userNum) {
        return null;
    }
}
