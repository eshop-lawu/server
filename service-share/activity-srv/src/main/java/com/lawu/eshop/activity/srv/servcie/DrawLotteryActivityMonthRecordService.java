package com.lawu.eshop.activity.srv.servcie;

/**
 * @author meishuquan
 * @date 2018/1/26.
 */
public interface DrawLotteryActivityMonthRecordService {

    /**
     * 统计用户当月免费抽奖次数
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    Integer countMonthFreeLottery(String userNum);

}
