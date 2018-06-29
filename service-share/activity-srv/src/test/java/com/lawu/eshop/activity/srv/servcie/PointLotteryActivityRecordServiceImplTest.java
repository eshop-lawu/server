package com.lawu.eshop.activity.srv.servcie;

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

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * 积分夺宝记录单元测试
 *
 * @author zhangrc
 * @Description
 * @date 2018年2月5日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityRecordServiceImplTest {

    @Autowired
    private PointLotteryActivityRecordService pointLotteryActivityRecordService;

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void attendPrizePage() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(activityDO.getId());
        recordDO.setUserNum("M944126086302662656");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);

        PointLotteryActivityRecordParam param = new PointLotteryActivityRecordParam();
        param.setCurrentPage(1);
        param.setPageSize(20);
        param.setUserNum("M944126086302662656");
        Page<PointLotteryActivityAttendRecordBO> page = pointLotteryActivityRecordService.attendPrizePage(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertTrue(page.getRecords().size() > 0);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void countPointLotteryActivityRecord() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
        int result = pointLotteryActivityRecordService.countPointLotteryActivityRecord(recordDO.getPointLotteryActivityId(), recordDO.getUserNum());
        Assert.assertEquals(result, 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPointLotteryActivityRecord() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
        PointLotteryActivityRecordBO recordBO = pointLotteryActivityRecordService.getPointLotteryActivityRecord(recordDO.getId());
        Assert.assertNotNull(recordBO);
        Assert.assertEquals(recordDO.getUserNum(), recordBO.getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLotteryStatus() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.NOT_WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
        pointLotteryActivityRecordService.updateLotteryStatus(recordDO.getId(), PointLotteryActivityRecordStatusEnum.WINNING);
        PointLotteryActivityRecordDO activityRecordDO = pointLotteryActivityRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(activityRecordDO);
        Assert.assertEquals(PointLotteryActivityRecordStatusEnum.WINNING.getVal(), activityRecordDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listLatestLotteryInfo() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setLotteryTime(DateUtil.getDayBefore(new Date()));
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(activityDO.getId());
        recordDO.setUserNum("M001");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
        List<PointLotteryRollView> rollViews = pointLotteryActivityRecordService.listLatestLotteryInfo();
        Assert.assertNotNull(rollViews);
        Assert.assertEquals(rollViews.size(), 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void page() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        recordDO.setMobile("13666666666");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.NOT_WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
        PointLotteryActivityQueryParam param = new PointLotteryActivityQueryParam();
        param.setStatus(PointLotteryActivityRecordStatusEnum.NOT_WINNING);
        param.setMobile(recordDO.getMobile());
        Page<PointLotteryActivityRecordBO> page = pointLotteryActivityRecordService.page(100L, param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(page.getRecords().size(), 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPointLotteryActivityAttendDetail() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal());
        activityDO.setLotteryPoint(10);
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(activityDO.getId());
        recordDO.setUserNum("M944126086302662656");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);

        PointLotteryActivityAttendDetailBO bo = pointLotteryActivityRecordService.getPointLotteryActivityAttendDetail("M944126086302662656", activityDO.getId());
        Assert.assertNotNull(bo);
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAttendCount() {
        PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
        recordDO.setPointLotteryActivityId(100L);
        recordDO.setUserNum("M944126086302662656");
        recordDO.setStatus(PointLotteryActivityRecordStatusEnum.WINNING.getVal());
        pointLotteryActivityRecordDOMapper.insertSelective(recordDO);

        Integer count = pointLotteryActivityRecordService.getAttendCount("M944126086302662656");
        Assert.assertTrue(count > 0);
    }

}
