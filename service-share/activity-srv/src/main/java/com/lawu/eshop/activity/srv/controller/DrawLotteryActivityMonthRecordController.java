package com.lawu.eshop.activity.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.srv.servcie.DrawLotteryActivityMonthRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/26.
 */
@RestController
@RequestMapping(value = "drawLotteryActivityMonthRecord/")
public class DrawLotteryActivityMonthRecordController extends BaseController {

    @Autowired
    private DrawLotteryActivityMonthRecordService drawLotteryActivityMonthRecordService;

    /**
     * 统计用户当月免费抽奖次数
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "countMonthFreeLottery", method = RequestMethod.GET)
    public Result<Integer> countMonthFreeLottery(@RequestParam String userNum) {
        Integer monthFreeLottery = drawLotteryActivityMonthRecordService.countMonthFreeLottery(userNum);
        return successGet(monthFreeLottery);
    }

}
