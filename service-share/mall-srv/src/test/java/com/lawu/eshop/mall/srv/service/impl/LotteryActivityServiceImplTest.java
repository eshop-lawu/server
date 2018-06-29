package com.lawu.eshop.mall.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.param.LotteryActivityParam;
import com.lawu.eshop.mall.query.ListLotteryActivityQuery;
import com.lawu.eshop.mall.query.LotteryActivityRealQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.mapper.LotteryActivityDOMapper;
import com.lawu.eshop.mall.srv.service.LotteryActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class LotteryActivityServiceImplTest {

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Autowired
    private LotteryActivityDOMapper lotteryActivityDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listLotteryActivity() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.FINISHED.getVal());
        lotteryActivityDOMapper.insertSelective(activityDO);

        LotteryActivityRealQuery query = new LotteryActivityRealQuery();
        query.setUserNum("M001");
        Page<LotteryActivityBO> page = lotteryActivityService.listLotteryActivity(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getLotteryActivityById() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.FINISHED.getVal());
        lotteryActivityDOMapper.insertSelective(activityDO);

        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(activityDO.getId());
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(LotteryActivityStatusEnum.FINISHED.getVal(), activityBO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorLotteryActivity() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.FINISHED.getVal());
        lotteryActivityDOMapper.insertSelective(activityDO);

        ListLotteryActivityQuery query = new ListLotteryActivityQuery();
        query.setStatusEnum(LotteryActivityStatusEnum.FINISHED);
        Page<LotteryActivityBO> page = lotteryActivityService.listOperatorLotteryActivity(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLotteryActivityStatus() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.LOTTERYING.getVal());
        lotteryActivityDOMapper.insertSelective(activityDO);

        lotteryActivityService.updateLotteryActivityStatus(activityDO.getId(), LotteryActivityStatusEnum.FINISHED);
        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(activityDO.getId());
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(LotteryActivityStatusEnum.FINISHED.getVal(), activityBO.getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveLotteryActivity() {
        LotteryActivityParam param = new LotteryActivityParam();
        param.setPrizeName("礼品");
        param.setStatusEnum(LotteryActivityStatusEnum.UN_PUBLISH);
        param.setBeginTime("2017-01-01 00:00");
        param.setEndTime("2017-01-01 00:00");
        lotteryActivityService.saveLotteryActivity(param);

        List<LotteryActivityDO> activityDOS = lotteryActivityDOMapper.selectByExample(null);
        Assert.assertNotNull(activityDOS);
        Assert.assertEquals(1, activityDOS.size());
        Assert.assertEquals(param.getPrizeName(), activityDOS.get(0).getPrizeName());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void executeUpdateLotteryActivityStatus() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.LOTTERYING.getVal());
        activityDO.setEndTime(DateUtil.getDayBefore(new Date()));
        lotteryActivityDOMapper.insertSelective(activityDO);

        lotteryActivityService.executeUpdateLotteryActivityStatus();
        LotteryActivityBO activityBO = lotteryActivityService.getLotteryActivityById(activityDO.getId());
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(LotteryActivityStatusEnum.FINISHED.getVal(), activityBO.getStatus());
    }

}
