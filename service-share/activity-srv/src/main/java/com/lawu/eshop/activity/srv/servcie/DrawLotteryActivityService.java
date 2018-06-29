package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public interface DrawLotteryActivityService {

    /**
     * 抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<DrawLotteryActivityBO> listDrawLotteryActivity(DrawLotteryActivityQuery query);

    /**
     * 抽奖活动详情
     *
     * @param id
     * @param userNum
     * @return
     * @author meishuquan
     */
    DrawLotteryActivityBO getDrawLotteryActivityDetail(Long id, String userNum);

    /**
     * 中奖公告列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<DrawLotteryActivityBO> listDrawLotteryActivityNotice(DrawLotteryActivityQuery query);

    /**
     * 根据抽奖活动id查询抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    DrawLotteryActivityBO getDrawLotteryActivityById(Long id);

    /**
     * 新增抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    void saveDrawLotteryActivity(DrawLotteryActivityParam param);

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<DrawLotteryActivityBO> listOperatorDrawLotteryActivity(OperatorDrawLotteryActivityQuery query);

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateDrawLotteryActivityStatus(Long id, DrawLotteryActivityStatusEnum statusEnum);

    /**
     * 定时更新抽奖活动进行中和已结束状态
     *
     * @author meishuquan
     */
    void executeUpdateDrawLotteryActivityStatus();

}
