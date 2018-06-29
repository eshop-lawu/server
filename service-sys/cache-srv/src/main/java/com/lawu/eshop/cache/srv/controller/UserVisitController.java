package com.lawu.eshop.cache.srv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.UserVisitService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/7/3.
 */
@RestController
@RequestMapping(value = "userVisit/")
public class UserVisitController extends BaseController {

    @Autowired
    private UserVisitService userVisitService;

    @RequestMapping(value = "addUserVisitCount", method = RequestMethod.POST)
    public Result addUserVisitCount(@RequestParam("userNum") String userNum,
                                    @RequestParam("nowTimeStr") String nowTimeStr,
                                    @RequestParam("userId") Long userId, @RequestParam("type") UserTypeEnum type) {
        userVisitService.addUserVisitCount(userNum, nowTimeStr, userId, type);
        return successCreated();
    }

    @RequestMapping(value = "getVisitRecords", method = RequestMethod.GET)
    Map<String, String> getVisitRecords(@RequestParam("currentPage") Integer currentPage,
                                        @RequestParam("time") String time,
                                        @RequestParam("type") Byte type) {
        Map<String, String> records = userVisitService.getVisitRecords(currentPage, time, type);
        return records;
    }

    @RequestMapping(value = "delVisitRecords", method = RequestMethod.DELETE)
    public Result delVisitRecords(@RequestParam("time") String time) {
        userVisitService.delVisitRecords(time);
        return successDelete();
    }

    /**
     * 用户上次访问接口时间
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    @Deprecated
    @RequestMapping(value = "addUserVisitTime", method = RequestMethod.GET)
    public Result addUserVisitTime(@RequestParam Long userId, @RequestParam UserTypeEnum type) {
        userVisitService.addUserVisitTime(userId, type);
        return successGet();
    }

    /**
     * 用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @param expireTime
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "addUserVisitFrequency", method = RequestMethod.GET)
    public Result addUserVisitFrequency(@RequestParam Long userId, @RequestParam UserTypeEnum type, @RequestParam Integer expireTime) {
        userVisitService.addUserVisitFrequency(userId, type, expireTime);
        return successGet();
    }

    /**
     * 查询用户上次访问接口时间
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    @Deprecated
    @RequestMapping(value = "getUserVisitTime", method = RequestMethod.GET)
    public Result<Long> getUserVisitTime(@RequestParam Long userId, @RequestParam UserTypeEnum type) {
        Long time = userVisitService.getUserVisitTime(userId, type);
        return successGet(time);
    }

    /**
     * 查询用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getUserVisitFrequency", method = RequestMethod.GET)
    public Result<Integer> getUserVisitFrequency(@RequestParam Long userId, @RequestParam UserTypeEnum type) {
        Integer frequency = userVisitService.getUserVisitFrequency(userId, type);
        return successGet(frequency);
    }

    /**
     * 删除用户时间周期内访问接口频率
     *
     * @param userId
     * @param type
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "delUserVisitFrequency", method = RequestMethod.DELETE)
    public Result delUserVisitFrequency(@RequestParam Long userId, @RequestParam UserTypeEnum type) {
        userVisitService.delUserVisitFrequency(userId, type);
        return successDelete();
    }

    /**
     * 保存用户访问接口次数和时间
     *
     * @param userNum
     * @param nowTimeStr
     * @param userId
     * @param type
     * @param currTime
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "addUserVisitCountAndTime", method = RequestMethod.GET)
    public Result<Long> addUserVisitCountAndTime(@RequestParam String userNum, @RequestParam String nowTimeStr,
                                                 @RequestParam Long userId, @RequestParam UserTypeEnum type, @RequestParam String currTime) {
        Long lastTime = userVisitService.addUserVisitCountAndTime(userNum, nowTimeStr, userId, type, currTime);
        return successGet(lastTime);
    }

    /**
     * 获取当天访问接口次数
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getDailyVisitTimes", method = RequestMethod.GET)
    public Result<Integer> getDailyVisitTimes(@RequestParam String userNum) {
        Integer visitTimes = userVisitService.getDailyVisitTimes(userNum);
        return successGet(visitTimes);
    }

}
