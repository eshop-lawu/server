package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.srv.service.PointLotteryNumService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
@RestController
@RequestMapping(value = "pointLotteryNum/")
public class PointLotteryNumController extends BaseController {

    @Autowired
    private PointLotteryNumService pointLotteryNumService;

    /**
     * 获取最新的积分抽奖号码
     *
     * @param pointLotteryActivityId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getNewPointLotteryNum/{pointLotteryActivityId}", method = RequestMethod.GET)
    public Result<Long> getNewPointLotteryNum(@PathVariable Long pointLotteryActivityId) {
        Long lotteryNum = pointLotteryNumService.getNewPointLotteryNum(pointLotteryActivityId);
        return successGet(lotteryNum);
    }

    /**
     * 删除结束的积分抽奖活动
     *
     * @param pointLotteryActivityId
     * @author meishuquan
     */
    @RequestMapping(value = "delRecommendFoodConsume/{pointLotteryActivityId}", method = RequestMethod.DELETE)
    public Result delRecommendFoodConsume(@PathVariable Long pointLotteryActivityId) {
        pointLotteryNumService.delPointLotteryActivity(pointLotteryActivityId);
        return successDelete();
    }

}
