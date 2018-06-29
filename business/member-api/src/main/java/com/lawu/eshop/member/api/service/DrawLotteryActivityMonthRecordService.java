package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/26.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivityMonthRecord/")
public interface DrawLotteryActivityMonthRecordService {

    /**
     * 统计用户当月免费抽奖次数
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "countMonthFreeLottery", method = RequestMethod.GET)
    Result<Integer> countMonthFreeLottery(@RequestParam("userNum") String userNum);

}
