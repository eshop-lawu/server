package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryAttentBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityOrderDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityReportDO;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityOrderDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityReportDOMapper;

/**
 * @author meishuquan
 * @date 2018/2/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityOrderServiceImplTest {

    @Autowired
    private PointLotteryActivityOrderService pointLotteryActivityOrderService;

    @Autowired
    private PointLotteryActivityOrderDOMapper pointLotteryActivityOrderDOMapper;

    @Autowired
    private PointLotteryActivityReportDOMapper pointLotteryActivityReportDOMapper;

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Transactional
    @Rollback
    @Test
    public void savePointLotteryActivityOrder() {
        PointLotteryAttentParam param = new PointLotteryAttentParam();
        param.setUserNum("M001");
        param.setMobile("13666666666");
        param.setPointLotteryActivityId(1L);
        param.setAttentCount(1);
        param.setPayPoint(BigDecimal.valueOf(10));
        param.setStatusEnum(PointLotteryActivityOrderStatusEnum.PENDING);
        Long id = pointLotteryActivityOrderService.savePointLotteryActivityOrder(param);
        PointLotteryActivityOrderDO orderDO = pointLotteryActivityOrderDOMapper.selectByPrimaryKey(id);
        Assert.assertNotNull(orderDO);
        Assert.assertEquals(param.getUserNum(), orderDO.getUserNum());
    }

    @Transactional
    @Rollback
    @Test
    public void updateLotteryOrderAndRecord() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setAttentNumber(0);
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setUserNum("M001");
        orderDO.setAttentCount(1);
        orderDO.setPointLotteryActivityId(activityDO.getId());
        orderDO.setStatus(PointLotteryActivityOrderStatusEnum.PENDING.getVal());
        pointLotteryActivityOrderDOMapper.insertSelective(orderDO);

        pointLotteryActivityOrderService.updateLotteryOrderAndRecord(orderDO.getId(), PointLotteryActivityOrderStatusEnum.SUCCESS);
        PointLotteryActivityOrderDO activityOrderDO = pointLotteryActivityOrderDOMapper.selectByPrimaryKey(orderDO.getId());
        Assert.assertNotNull(activityOrderDO);
        Assert.assertEquals(PointLotteryActivityOrderStatusEnum.SUCCESS.getVal(), activityOrderDO.getStatus());

        List<PointLotteryActivityReportDO> reportDOS = pointLotteryActivityReportDOMapper.selectByExample(null);
        Assert.assertNotNull(reportDOS);
        Assert.assertEquals(orderDO.getUserNum(), reportDOS.get(0).getUserNum());

        List<PointLotteryActivityRecordDO> recordDOS = pointLotteryActivityRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(recordDOS);
        Assert.assertEquals(orderDO.getPointLotteryActivityId(), recordDOS.get(0).getPointLotteryActivityId());

        PointLotteryActivityDO pointLotteryActivityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(orderDO.getPointLotteryActivityId());
        Assert.assertNotNull(pointLotteryActivityDO);
        Assert.assertEquals(pointLotteryActivityDO.getAttentNumber().intValue(), 1);
    }

    @Transactional
    @Rollback
    @Test
    public void getPointLotteryActivityOrder() {
        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setUserNum("M001");
        orderDO.setPointLotteryActivityId(100L);
        pointLotteryActivityOrderDOMapper.insertSelective(orderDO);

        PointLotteryActivityOrderBO orderBO = pointLotteryActivityOrderService.getPointLotteryActivityOrder(orderDO.getId());
        Assert.assertNotNull(orderBO);
        Assert.assertEquals(orderDO.getUserNum(), orderBO.getUserNum());
    }

    @Transactional
    @Rollback
    @Test
    public void getPointLotteryAttentInfo() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setLotteryTime(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setPointLotteryActivityId(activityDO.getId());
        recordDO.setPointLotteryActivityOrderId(200L);
        recordDO.setLotteryNum(10);
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);

        PointLotteryAttentBO attentBO = pointLotteryActivityOrderService.getPointLotteryAttentInfo(recordDO.getPointLotteryActivityOrderId());
        Assert.assertNotNull(attentBO);
        Assert.assertEquals(recordDO.getLotteryNum(), Integer.valueOf(attentBO.getLotteryNum()));
    }

}
