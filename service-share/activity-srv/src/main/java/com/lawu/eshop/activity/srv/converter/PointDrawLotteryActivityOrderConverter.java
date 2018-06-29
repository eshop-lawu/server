package com.lawu.eshop.activity.srv.converter;

import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityOrderDO;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public class PointDrawLotteryActivityOrderConverter {

    public static PointLotteryActivityOrderBO convertBO(PointLotteryActivityOrderDO orderDO) {
        if (orderDO == null) {
            return null;
        }

        PointLotteryActivityOrderBO orderBO = new PointLotteryActivityOrderBO();
        orderBO.setId(orderDO.getId());
        orderBO.setUserNum(orderDO.getUserNum());
        orderBO.setMobile(orderDO.getMobile());
        orderBO.setPointLotteryActivityId(orderDO.getPointLotteryActivityId());
        orderBO.setAttentCount(orderDO.getAttentCount());
        orderBO.setPayPoint(orderDO.getPayPoint());
        orderBO.setStatus(orderDO.getStatus());
        orderBO.setGmtModified(orderDO.getGmtModified());
        orderBO.setGmtCreate(orderDO.getGmtCreate());
        return orderBO;
    }

}
