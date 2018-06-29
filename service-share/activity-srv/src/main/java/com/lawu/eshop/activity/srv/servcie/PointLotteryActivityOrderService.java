package com.lawu.eshop.activity.srv.servcie;

import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryAttentBO;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public interface PointLotteryActivityOrderService {

    /**
     * 保存积分抽奖活动订单
     *
     * @param param
     * @author meishuquan
     */
    Long savePointLotteryActivityOrder(PointLotteryAttentParam param);

    /**
     * 更新活动订单状态并保存抽奖记录
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateLotteryOrderAndRecord(Long id, PointLotteryActivityOrderStatusEnum statusEnum);

    /**
     * 根据id查询抽奖活动订单
     *
     * @param id
     * @return
     * @author meishuquan
     */
    PointLotteryActivityOrderBO getPointLotteryActivityOrder(Long id);

    /**
     * 参与抽奖详情
     *
     * @param pointLotteryActivityOrderId
     * @return
     * @author meishuquan
     */
    PointLotteryAttentBO getPointLotteryAttentInfo(Long pointLotteryActivityOrderId);

}
