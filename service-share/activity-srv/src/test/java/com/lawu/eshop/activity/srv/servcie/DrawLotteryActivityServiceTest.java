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

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.DrawLotteryActivityParam;
import com.lawu.eshop.activity.query.DrawLotteryActivityQuery;
import com.lawu.eshop.activity.query.OperatorDrawLotteryActivityQuery;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityPrizeDOMapper;
import com.lawu.eshop.activity.srv.mapper.DrawLotteryActivityRecordDOMapper;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/2/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class DrawLotteryActivityServiceTest {

    @Autowired
    private DrawLotteryActivityService drawLotteryActivityService;

    @Autowired
    private DrawLotteryActivityDOMapper drawLotteryActivityDOMapper;

    @Autowired
    private DrawLotteryActivityPrizeDOMapper drawLotteryActivityPrizeDOMapper;

    @Autowired
    private DrawLotteryActivityRecordDOMapper drawLotteryActivityRecordDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listDrawLotteryActivity() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityQuery query = new DrawLotteryActivityQuery();
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listDrawLotteryActivity(query);
        Assert.assertNotNull(activityBOPage.getRecords());
        Assert.assertEquals(activityDO.getName(), activityBOPage.getRecords().get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getDrawLotteryActivityDetail() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setDrawLotteryActivityId(activityDO.getId());
        prizeDO.setStatus(DrawLotteryActivityPrizeStatusEnum.VALID.getVal());
        prizeDO.setNumber(10);
        drawLotteryActivityPrizeDOMapper.insertSelective(prizeDO);

        DrawLotteryActivityBO activityBO = drawLotteryActivityService.getDrawLotteryActivityDetail(activityDO.getId(), "M001");
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(prizeDO.getNumber(), activityBO.getPrizeNumber());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listDrawLotteryActivityNotice() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setDrawLotteryActivityId(activityDO.getId());
        recordDO.setStatus(DrawLotteryActivityRecordStatusEnum.GET_LOTTERY.getVal());
        recordDO.setUserAccount("1366666666");
        recordDO.setPrizeName("test");
        drawLotteryActivityRecordDOMapper.insertSelective(recordDO);

        DrawLotteryActivityQuery query = new DrawLotteryActivityQuery();
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listDrawLotteryActivityNotice(query);
        Assert.assertNotNull(activityBOPage.getRecords());
        Assert.assertEquals(recordDO.getPrizeName(), activityBOPage.getRecords().get(0).getLotteryInfoBOS().get(0).getPrizeName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getDrawLotteryActivityById() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityBO activityBO = drawLotteryActivityService.getDrawLotteryActivityById(activityDO.getId());
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(activityDO.getName(), activityBO.getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveDrawLotteryActivity() {
        DrawLotteryActivityParam param = new DrawLotteryActivityParam();
        param.setName("test");
        param.setImgPath("png");
        param.setGrade((byte) 1);
        param.setBeginTime("2018-02-26");
        param.setEndTime("2018-02-28");
        param.setStatusEnum(DrawLotteryActivityStatusEnum.UN_PUBLISH);
        drawLotteryActivityService.saveDrawLotteryActivity(param);
        List<DrawLotteryActivityDO> activityDOS = drawLotteryActivityDOMapper.selectByExample(null);
        Assert.assertNotNull(activityDOS);
        Assert.assertEquals(param.getName(), activityDOS.get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorDrawLotteryActivity() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        OperatorDrawLotteryActivityQuery query = new OperatorDrawLotteryActivityQuery();
        query.setName(activityDO.getName());
        query.setBeginTime("2018-01-01");
        query.setEndTime("2028-01-01");
        query.setStatusEnum(DrawLotteryActivityStatusEnum.LOTTERYING);
        Page<DrawLotteryActivityBO> activityBOPage = drawLotteryActivityService.listOperatorDrawLotteryActivity(query);
        Assert.assertNotNull(activityBOPage.getRecords());
        Assert.assertEquals(activityDO.getName(), activityBOPage.getRecords().get(0).getName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateDrawLotteryActivityStatus() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        drawLotteryActivityService.updateDrawLotteryActivityStatus(activityDO.getId(), DrawLotteryActivityStatusEnum.FINISHED);
        DrawLotteryActivityDO drawLotteryActivityDO = drawLotteryActivityDOMapper.selectByPrimaryKey(activityDO.getId());
        Assert.assertNotNull(drawLotteryActivityDO);
        Assert.assertEquals(DrawLotteryActivityStatusEnum.FINISHED.getVal(), drawLotteryActivityDO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeUpdateDrawLotteryActivityStatus() {
        DrawLotteryActivityDO activityDO = new DrawLotteryActivityDO();
        activityDO.setName("test");
        activityDO.setStatus(DrawLotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO);

        DrawLotteryActivityDO activityDO1 = new DrawLotteryActivityDO();
        activityDO1.setName("test");
        activityDO1.setStatus(DrawLotteryActivityStatusEnum.PUBLISHED.getVal());
        activityDO1.setGrade((byte) 1);
        drawLotteryActivityDOMapper.insertSelective(activityDO1);

        drawLotteryActivityService.executeUpdateDrawLotteryActivityStatus();

        DrawLotteryActivityDO drawLotteryActivityDO = drawLotteryActivityDOMapper.selectByPrimaryKey(activityDO.getId());
        Assert.assertNotNull(drawLotteryActivityDO);
        Assert.assertEquals(DrawLotteryActivityStatusEnum.FINISHED.getVal(), drawLotteryActivityDO.getStatus());

        DrawLotteryActivityDO drawLotteryActivityDO1 = drawLotteryActivityDOMapper.selectByPrimaryKey(activityDO1.getId());
        Assert.assertNotNull(drawLotteryActivityDO1);
        Assert.assertEquals(DrawLotteryActivityStatusEnum.LOTTERYING.getVal(), drawLotteryActivityDO1.getStatus());
    }

}
