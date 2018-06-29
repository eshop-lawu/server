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
import com.lawu.eshop.mall.param.LotteryRecordParam;
import com.lawu.eshop.mall.query.LotteryRecordQuery;
import com.lawu.eshop.mall.query.OperatorLotteryRecordQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.LotteryInfoBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordOperatorBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDO;
import com.lawu.eshop.mall.srv.mapper.LotteryActivityDOMapper;
import com.lawu.eshop.mall.srv.mapper.LotteryRecordDOMapper;
import com.lawu.eshop.mall.srv.service.LotteryRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author meishuquan
 * @date 2017/11/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class LotteryRecordServiceImplTest {

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @Autowired
    private LotteryRecordDOMapper lotteryRecordDOMapper;

    @Autowired
    private LotteryActivityDOMapper lotteryActivityDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveLotteryRecord() {
        LotteryRecordParam param = new LotteryRecordParam();
        param.setUserNum("M001");
        lotteryRecordService.saveLotteryRecord(param);
        List<LotteryRecordDO> recordDOS = lotteryRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(recordDOS);
        Assert.assertEquals(1, recordDOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLotteryCountByUserNumAndLotteryActivityId() {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setLotteryCount(1);
        recordDO.setLotteryActivityId(100L);
        lotteryRecordDOMapper.insertSelective(recordDO);

        lotteryRecordService.updateLotteryCountByUserNumAndLotteryActivityId(recordDO.getUserNum(), recordDO.getLotteryActivityId());
        LotteryRecordDO lotteryRecordDO = lotteryRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(lotteryRecordDO);
        Assert.assertEquals(2, lotteryRecordDO.getLotteryCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listLotteryInfo() {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setLotteryActivityId(100L);
        recordDO.setLotteryResult(true);
        recordDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryRecordDOMapper.insertSelective(recordDO);

        List<LotteryInfoBO> infoBOS = lotteryRecordService.listLotteryInfo();
        Assert.assertNotNull(infoBOS);
        Assert.assertEquals(1, infoBOS.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listLotteryRecord() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setStatus(LotteryActivityStatusEnum.FINISHED.getVal());
        activityDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryActivityDOMapper.insertSelective(activityDO);

        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setLotteryActivityId(100L);
        recordDO.setLotteryResult(true);
        recordDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryRecordDOMapper.insertSelective(recordDO);

        LotteryRecordQuery query = new LotteryRecordQuery();
        Page<LotteryRecordBO> recordBOPage = lotteryRecordService.listLotteryRecord(query);
        Assert.assertNotNull(recordBOPage.getRecords());
        Assert.assertEquals(1, recordBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listOperatorLotteryRecord() {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setLotteryActivityId(100L);
        recordDO.setLotteryResult(true);
        recordDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryRecordDOMapper.insertSelective(recordDO);

        OperatorLotteryRecordQuery query = new OperatorLotteryRecordQuery();
        query.setLotteryActivityId(recordDO.getLotteryActivityId());
        Page<LotteryRecordOperatorBO> operatorBOPage = lotteryRecordService.listOperatorLotteryRecord(query);
        Assert.assertNotNull(operatorBOPage.getRecords());
        Assert.assertEquals(1, operatorBOPage.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void lotteryRecord() {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setLotteryActivityId(100L);
        recordDO.setLotteryResult(true);
        recordDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryRecordDOMapper.insertSelective(recordDO);

        Boolean result = lotteryRecordService.lotteryRecord(recordDO.getLotteryActivityId(), recordDO.getUserNum());
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateLotteryResult() {
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setAccount("13666666666");
        recordDO.setLotteryActivityId(100L);
        recordDO.setLotteryResult(false);
        recordDO.setGmtModified(DateUtil.getDayBefore(new Date()));
        lotteryRecordDOMapper.insertSelective(recordDO);

        lotteryRecordService.updateLotteryResult(100L, recordDO.getAccount());
        LotteryRecordDO lotteryRecordDO = lotteryRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(lotteryRecordDO);
        Assert.assertTrue(lotteryRecordDO.getLotteryResult());
    }

}
