package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
@FeignClient(value = "activity-srv", path = "pointLotteryActivity/")
public interface PointLotteryActivityService {

    /**
     * 定时更新抽奖活动进行中、已结束、已开奖状态
     *
     * @author meishuquan
     */
    @RequestMapping(value = "executeUpdatePointLotteryActivityStatus", method = RequestMethod.PUT)
    Result executeUpdatePointLotteryActivityStatus();

}
