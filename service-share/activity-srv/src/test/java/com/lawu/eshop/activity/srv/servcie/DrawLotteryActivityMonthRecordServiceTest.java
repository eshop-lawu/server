package com.lawu.eshop.activity.srv.servcie;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDO;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityMonthRecordDOMapper;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class DrawLotteryActivityMonthRecordServiceTest {

    @Autowired
    private DrawLotteryActivityMonthRecordService drawLotteryActivityMonthRecordService;

    @Autowired
    private DrawLotteryActivityMonthRecordDOMapper drawLotteryActivityMonthRecordDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void countMonthFreeLottery() {
        DrawLotteryActivityMonthRecordDO recordDO = new DrawLotteryActivityMonthRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setFreeTimes(10);
        recordDO.setRecordDate(DateUtil.getYearMonthDate());
        drawLotteryActivityMonthRecordDOMapper.insertSelective(recordDO);

        int result = drawLotteryActivityMonthRecordService.countMonthFreeLottery(recordDO.getUserNum());
        Assert.assertNotNull(result);
        Assert.assertEquals(recordDO.getFreeTimes().intValue(), result);
    }

}
