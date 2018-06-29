package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/17.
 */
@FeignClient(value = "activity-srv", path = "drawLotteryActivity/")
public interface DrawLotteryActivityService {

    /**
     * 定时更新抽奖活动进行中和已结束状态
     *
     * @author meishuquan
     */
    @RequestMapping(value = "executeUpdateDrawLotteryActivityStatus", method = RequestMethod.PUT)
    Result executeUpdateDrawLotteryActivityStatus();

}
