package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.TakeLotteryParam;
import com.lawu.eshop.activity.param.TakePartLotteryParam;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityRecordQuery;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityMonthRecordDOExample;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityMonthRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityRecordDOMapper;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/2/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class DrawLotteryActivityRecordServiceTest {

    @Autowired
    private DrawLotteryActivityRecordService drawLotteryActivityRecordService;

    @Autowired
    private DrawLotteryActivityRecordDOMapper drawLotteryActivityRecordDOMapper;

    @Autowired
    private DrawLotteryActivityMonthRecordDOMapper drawLotteryActivityMonthRecordDOMapper;

    @Autowired
    private DrawLotteryActivityDOMapper drawLotteryActivityDOMapper;

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getTakePartLottery() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setDrawLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        Long result = drawLotteryActivityRecordService.getTakePartLottery(recordDO.getDrawLotteryActivityId(), recordDO.getUserNum(), DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY);
        Assert.assertNotNull(result);
        Assert.assertEquals(recordDO.getId(), result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void takePartLottery() {
        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(100L);
        param.setUserNum("M001");
        param.setUserAccount("13666666666");
        param.setDrawLotteryActivityId(10L);
        param.setPayPoint(BigDecimal.valueOf(20));
        param.setChannelEnum(DrawLotteryActivityRecordChannelEnum.FREE_LOTTERY);
        Long result = drawLotteryActivityRecordService.takePartLottery(param);

        DrawLotteryActivityRecordDO recordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(result);
        Assert.assertNotNull(recordDO);
        Assert.assertEquals(recordDO.getUserNum(), param.getUserNum());

        DrawLotteryActivityMonthRecordDOExample example = new DrawLotteryActivityMonthRecordDOExample();
        example.createCriteria().andUserNumEqualTo(param.getUserNum());
        List<DrawLotteryActivityMonthRecordDO> recordDOS = drawLotteryActivityMonthRecordDOMapper.selectByExample(example);
        Assert.assertNotNull(recordDOS);
        Assert.assertEquals(1, recordDOS.get(0).getFreeTimes().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLotteryStatus() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setDrawLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        drawLotteryActivityRecordService.updateLotteryStatus(recordDO.getId(), DrawLotteryActivityRecordStatusEnum.NOT_LOTTERY);
        DrawLotteryActivityRecordDO activityRecordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(activityRecordDO);
        Assert.assertEquals(DrawLotteryActivityRecordStatusEnum.NOT_LOTTERY.getVal(), activityRecordDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void winLottery() throws Exception {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(activityDO.getId());
        prizeDO.setInventory(1);
        prizeDO.setName("test");
        prizeDO.setStatus(DrawLotteryActivityPrizeStatusEnum.INVALID.getVal());
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        drawLotteryActivityRecordService.winLottery(recordDO.getId(), prizeDO.getId());
        DrawLotteryActivityRecordDO activityRecordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(activityRecordDO);
        Assert.assertEquals(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal(), activityRecordDO.getStatus());

        DrawLotteryActivityDO drawLotteryActivityDO = drawLotteryActivityDOMapper.selectByPrimaryKey(activityDO.getId());
        Assert.assertNotNull(drawLotteryActivityDO);
        Assert.assertEquals(DrawLotteryActivityStatusEnum.FINISHED.getVal(), drawLotteryActivityDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void takeLottery() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        TakeLotteryParam param = new TakeLotteryParam();
        param.setId(recordDO.getId());
        drawLotteryActivityRecordService.takeLottery(param);
        DrawLotteryActivityRecordDO activityRecordDO = drawLotteryActivityRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(activityRecordDO);
        Assert.assertEquals(DrawLotteryActivityRecordStatusEnum.TAKE_LOTTERY.getVal(), activityRecordDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getDrawLotteryActivityRecord() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setUserAccount("13666666666");
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        DrawLotteryActivityRecordBO recordBO = drawLotteryActivityRecordService.getDrawLotteryActivityRecord(recordDO.getId());
        Assert.assertNotNull(recordBO);
        Assert.assertEquals(recordDO.getUserNum(), recordBO.getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorDrawLotteryActivityRecord() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setDrawLotteryActivityId(100L);
        recordDO.setUserNum("M001");
        recordDO.setUserAccount("13666666666");
        recordDO.setPrizeName("test");
        recordDO.setConsigneeName("创维大厦");
        recordDO.setConsigneeMobile("13888888888");
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        OperatorDrawLotteryActivityRecordQuery query = new OperatorDrawLotteryActivityRecordQuery();
        query.setDrawLotteryActivityId(recordDO.getDrawLotteryActivityId());
        query.setUserAccount(recordDO.getUserAccount());
        query.setPrizeName(recordDO.getPrizeName());
        query.setConsigneeName(recordDO.getConsigneeName());
        query.setConsigneeMobile(recordDO.getConsigneeMobile());
        query.setStatusEnum(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY);
        Page<DrawLotteryActivityRecordBO> recordBOPage = drawLotteryActivityRecordService.listOperatorDrawLotteryActivityRecord(query);
        Assert.assertNotNull(recordBOPage.getRecords());
        Assert.assertEquals(recordDO.getUserNum(), recordBOPage.getRecords().get(0).getUserNum());
    }

}
