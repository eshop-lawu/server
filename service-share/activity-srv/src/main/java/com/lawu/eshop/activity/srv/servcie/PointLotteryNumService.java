package com.lawu.eshop.activity.srv.servcie;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
@FeignClient(value = "cache-srv")
public interface PointLotteryNumService {

    /**
     * 获取最新的积分抽奖号码
     *
     * @param pointLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "pointLotteryNum/getNewPointLotteryNum/{pointLotteryActivityId}", method = RequestMethod.GET)
    Result<Long> getNewPointLotteryNum(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId);

    /**
     * 删除结束的积分抽奖活动
     *
     * @param pointLotteryActivityId
     * @author meishuquan
     */
    @RequestMapping(value = "pointLotteryNum/delRecommendFoodConsume/{pointLotteryActivityId}", method = RequestMethod.DELETE)
    Result delRecommendFoodConsume(@PathVariable("pointLotteryActivityId") Long pointLotteryActivityId);

}
