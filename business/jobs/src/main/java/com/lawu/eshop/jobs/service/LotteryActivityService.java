package com.lawu.eshop.jobs.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
@FeignClient(value = "mall-srv")
public interface LotteryActivityService {

    /**
     * 定时更新活动进行中、已结束状态
     *
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "lotteryActivity/executeUpdateLotteryActivityStatus", method = RequestMethod.PUT)
    Result executeUpdateLotteryActivityStatus();

}
