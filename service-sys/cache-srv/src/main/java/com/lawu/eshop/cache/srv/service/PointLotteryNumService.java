package com.lawu.eshop.cache.srv.service;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
public interface PointLotteryNumService {

    /**
     * 获取最新的积分抽奖号码
     *
     * @param pointLotteryActivityId
     * @return
     * @author meishuquan
     */
    Long getNewPointLotteryNum(Long pointLotteryActivityId);

    /**
     * 删除结束的积分抽奖活动
     *
     * @param pointLotteryActivityId
     * @author meishuquan
     */
    void delPointLotteryActivity(Long pointLotteryActivityId);

}
