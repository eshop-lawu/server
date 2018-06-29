package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.param.LotteryActivityParam;
import com.lawu.eshop.mall.query.ListLotteryActivityQuery;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public interface LotteryActivityService {

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<LotteryActivityBO> listLotteryActivity(LotteryActivityRealQuery query);

    /**
     * 根据id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    LotteryActivityBO getLotteryActivityById(Long id);

    /**
     * 运营平台查询抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<LotteryActivityBO> listOperatorLotteryActivity(ListLotteryActivityQuery query);

    /**
     * 根据id更新活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateLotteryActivityStatus(Long id, LotteryActivityStatusEnum statusEnum);

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    void saveLotteryActivity(LotteryActivityParam param);

    /**
     * 定时更新活动进行中、已结束状态
     *
     * @author meishuquan
     */
    void executeUpdateLotteryActivityStatus();

}
