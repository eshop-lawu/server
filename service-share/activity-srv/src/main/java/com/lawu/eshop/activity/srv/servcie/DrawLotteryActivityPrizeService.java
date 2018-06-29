package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityPrizeParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityPrizeQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public interface DrawLotteryActivityPrizeService {

    /**
     * 查询奖品详情
     *
     * @param id
     * @return
     * @author meishuquan
     */
    DrawLotteryActivityPrizeBO getPrizeDetail(Long id);

    /**
     * 查询活动所有奖品
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    List<DrawLotteryActivityPrizeBO> getActivityAllPrize(Long drawLotteryActivityId);

    /**
     * 新增抽奖奖品
     *
     * @param param
     * @author meishuquan
     */
    void saveDrawLotteryActivityPrize(DrawLotteryActivityPrizeParam param);

    /**
     * 运营平台抽奖奖品列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<DrawLotteryActivityPrizeBO> listOperatorDrawLotteryActivityPrize(OperatorDrawLotteryActivityPrizeQuery query);

    /**
     * 更新奖品状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateDrawLotteryActivityPrizeStatus(Long id, DrawLotteryActivityPrizeStatusEnum statusEnum);

    /**
     * 查询奖品剩余中奖率
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    BigDecimal getLeftPrizeRate(Long drawLotteryActivityId);

    /**
     * 随机查询奖品广告图片
     *
     * @param drawLotteryActivityId
     * @return
     * @author meishuquan
     */
    String getRandPrizeAdImg(Long drawLotteryActivityId);

    /**
     * 根据抽奖记录id查询奖品信息
     *
     * @param recordId
     * @return
     * @author meishuquan
     */
    DrawLotteryActivityPrizeBO getPrizeByRecordId(Long recordId);

}
