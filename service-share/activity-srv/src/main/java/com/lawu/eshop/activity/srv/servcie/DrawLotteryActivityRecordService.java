package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public interface DrawLotteryActivityRecordService {

    /**
     * 查询已参与的抽奖
     *
     * @param drawLotteryActivityId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    Long getTakePartLottery(Long drawLotteryActivityId, String userNum, DrawLotteryActivityRecordStatusEnum statusEnum);

    /**
     * 参与抽奖
     *
     * @param param
     * @return
     * @author meishuquan
     */
    Long takePartLottery(TakePartLotteryParam param);

    /**
     * 更新抽奖结果
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateLotteryStatus(Long id, DrawLotteryActivityRecordStatusEnum statusEnum);

    /**
     * 中奖
     *
     * @param id
     * @param drawLotteryActivityPrizeId
     * @return
     * @author meishuquan
     */
    Integer winLottery(Long id, Long drawLotteryActivityPrizeId) throws Exception;

    /**
     * 领奖
     *
     * @param param
     * @author meishuquan
     */
    void takeLottery(TakeLotteryParam param);

    /**
     * 根据id查询抽奖记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    DrawLotteryActivityRecordBO getDrawLotteryActivityRecord(Long id);

    /**
     * 运营平台抽奖记录列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<DrawLotteryActivityRecordBO> listOperatorDrawLotteryActivityRecord(OperatorDrawLotteryActivityRecordQuery query);

    /**
     * 
     * @param id
     * @param expressNum
     * @author zhangrc
     */
    void sendPrize(Long id, String expressNum);
    
    /**
     * 抽奖记录
     * @param memberNum
     * @return
     */
    Page<DrawLotteryActivityRecordBO> listDrawLotteryActivityRecord(String memberNum,DrawLotteryActivityRecordQuery query);
}
