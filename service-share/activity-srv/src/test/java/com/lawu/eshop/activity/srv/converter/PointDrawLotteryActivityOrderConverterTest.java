package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityOrderDO;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
public class PointDrawLotteryActivityOrderConverterTest {

    @Test
    public void convertBO() {
        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setId(100L);
        orderDO.setUserNum("test");
        orderDO.setMobile("13666666666");
        orderDO.setPointLotteryActivityId(100L);
        orderDO.setAttentCount(10);
        orderDO.setPayPoint(BigDecimal.valueOf(10));
        orderDO.setStatus((byte) 1);
        orderDO.setGmtModified(new Date());
        orderDO.setGmtCreate(new Date());
        PointLotteryActivityOrderBO orderBO = PointDrawLotteryActivityOrderConverter.convertBO(orderDO);
        Assert.assertNotNull(orderBO);
        Assert.assertEquals(orderDO.getId(), orderBO.getId());
    }

}
