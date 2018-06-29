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

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityPrizeImageDO;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityPrizeImageDOMapper;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2018/2/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class PointLotteryActivityServiceImplTest {

    @Autowired
    private PointLotteryActivityService pointLotteryActivityService;

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Autowired
    private PointLotteryActivityPrizeImageDOMapper pointLotteryActivityPrizeImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listPointLotteryActivity() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.PUBLISHED.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.PARTICIPATION_END.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        activityDO = new PointLotteryActivityDO();
        activityDO.setStatus(PointLotteryActivityStatusEnum.ALREADY_LOTTERY.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityQuery query = new PointLotteryActivityQuery();
        Page<PointLotteryActivityBO> page = pointLotteryActivityService.listPointLotteryActivity(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(page.getRecords().size(), 4);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPointLotteryActivity() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        PointLotteryActivityBO activityBO = pointLotteryActivityService.getPointLotteryActivity(activityDO.getId());
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(activityDO.getPrizeName(), activityBO.getPrizeName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void savePointLotteryActivity() {
        PointLotteryActivityParam param = new PointLotteryActivityParam();
        param.setPrizeName("test");
        param.setStatusEnum(PointLotteryActivityStatusEnum.UNPUBLISHED);
        param.setImagePath("png");
        param.setOrderNum("1");
        param.setType("1");
        pointLotteryActivityService.savePointLotteryActivity(param);

        List<PointLotteryActivityDO> activityDOS = pointLotteryActivityDOMapper.selectByExample(null);
        Assert.assertNotNull(activityDOS);
        Assert.assertEquals(param.getPrizeName(), activityDOS.get(0).getPrizeName());

        param.setId(activityDOS.get(0).getId());
        param.setStatusEnum(PointLotteryActivityStatusEnum.PUBLISHED);
        pointLotteryActivityService.savePointLotteryActivity(param);
        PointLotteryActivityDO activityDO = pointLotteryActivityDOMapper.selectByPrimaryKey(activityDOS.get(0).getId());
        Assert.assertNotNull(activityDO);
        Assert.assertEquals(activityDO.getStatus(), PointLotteryActivityStatusEnum.PUBLISHED.getVal());

        List<PointLotteryActivityPrizeImageDO> imageDOS = pointLotteryActivityPrizeImageDOMapper.selectByExample(null);
        Assert.assertNotNull(imageDOS);
        Assert.assertEquals(imageDOS.size(), 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorPointLotteryActivity() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setStatus(PointLotteryActivityStatusEnum.UNPUBLISHED.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        OperatorPointLotteryActivityQuery query = new OperatorPointLotteryActivityQuery();
        query.setPrizeName("test");
        query.setBeginTime("2018-02-08");
        query.setEndTime("2020-02-08");
        query.setStatusEnum(PointLotteryActivityStatusEnum.UNPUBLISHED);
        Page<PointLotteryActivityBO> page = pointLotteryActivityService.listOperatorPointLotteryActivity(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(page.getRecords().size(), 1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePointLotteryActivityStatus() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setStatus(PointLotteryActivityStatusEnum.UNPUBLISHED.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        pointLotteryActivityService.updatePointLotteryActivityStatus(activityDO.getId(), PointLotteryActivityStatusEnum.PUBLISHED);
        PointLotteryActivityDO activityDO1 = pointLotteryActivityDOMapper.selectByPrimaryKey(activityDO.getId());
        Assert.assertNotNull(activityDO1);
        Assert.assertEquals(activityDO1.getStatus(), PointLotteryActivityStatusEnum.PUBLISHED.getVal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeUpdatePointLotteryActivityStatus() {
        Date date = DateUtil.getDayBefore(new Date());
        PointLotteryActivityDO activityDO1 = new PointLotteryActivityDO();
        activityDO1.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        activityDO1.setEndTime(date);
        pointLotteryActivityDOMapper.insertSelective(activityDO1);

        PointLotteryActivityDO activityDO2 = new PointLotteryActivityDO();
        activityDO2.setStatus(PointLotteryActivityStatusEnum.PUBLISHED.getVal());
        activityDO2.setBeginTime(date);
        pointLotteryActivityDOMapper.insertSelective(activityDO2);
        pointLotteryActivityService.executeUpdatePointLotteryActivityStatus();

        PointLotteryActivityDO activityDO4 = pointLotteryActivityDOMapper.selectByPrimaryKey(activityDO1.getId());
        Assert.assertNotNull(activityDO4);
        Assert.assertEquals(activityDO4.getStatus(), PointLotteryActivityStatusEnum.PARTICIPATION_END.getVal());

        PointLotteryActivityDO activityDO5 = pointLotteryActivityDOMapper.selectByPrimaryKey(activityDO2.getId());
        Assert.assertNotNull(activityDO5);
        Assert.assertEquals(activityDO5.getStatus(), PointLotteryActivityStatusEnum.PROCESSING.getVal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listRelatePointLotteryActivity() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setStatus(PointLotteryActivityStatusEnum.PROCESSING.getVal());
        activityDO.setGmtCreate(new Date());
        pointLotteryActivityDOMapper.insertSelective(activityDO);

        List<PointLotteryActivityBO> activityBOS = pointLotteryActivityService.listRelatePointLotteryActivity();
        Assert.assertNotNull(activityBOS);
        Assert.assertEquals(activityDO.getPrizeName(), activityBOS.get(0).getPrizeName());
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void generateBasicNumber() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setStatus(PointLotteryActivityStatusEnum.PARTICIPATION_END.getVal());
        activityDO.setGmtCreate(new Date());
        activityDO.setAttentNumber(10);
        pointLotteryActivityDOMapper.insertSelective(activityDO);
        
        GenerateBasicNumberParam param = new GenerateBasicNumberParam();
        param.setShanghaiCompositeIndex("3193.30");
        param.setShenzhenComponentIndex("10672.52");
        Integer basicNumber = pointLotteryActivityService.generateBasicNumber(activityDO.getId(), param);
        System.out.println(basicNumber);
    }

}
