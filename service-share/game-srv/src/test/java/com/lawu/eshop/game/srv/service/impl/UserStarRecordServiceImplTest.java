package com.lawu.eshop.game.srv.service.impl;

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

import com.lawu.eshop.game.constants.StarRecordStatusEnum;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.bo.UserRankBO;
import com.lawu.eshop.game.srv.domain.UserStarRecordDO;
import com.lawu.eshop.game.srv.mapper.UserStarRecordDOMapper;
import com.lawu.eshop.game.srv.service.UserStarRecordService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class UserStarRecordServiceImplTest {

    @Autowired
    private UserStarRecordService userStarRecordService;

    @Autowired
    private UserStarRecordDOMapper userStarRecordDOMapper;

    @Transactional
    @Test
    @Rollback
    public void addUserStartRecord() {
        UserStarRecordParam param = new UserStarRecordParam();
        param.setStarCount(10);
        param.setStatus(StarRecordStatusEnum.INCREASE);
        param.setUserNum("M123456");
        param.setAccount("13111111");
        userStarRecordService.addUserStartRecord(param);
        List<UserStarRecordDO> oldRecords = userStarRecordDOMapper.selectByExample(null);
        Assert.assertEquals(param.getUserNum(), oldRecords.get(0).getUserNum());
        Assert.assertEquals(true, oldRecords.get(0).getStatus());
        Assert.assertEquals(param.getAccount(), oldRecords.get(0).getAccount());
        Assert.assertEquals(param.getStarCount(), oldRecords.get(0).getMonthStarCount());
        UserStarRecordParam newParam = new UserStarRecordParam();
        newParam.setStarCount(10);
        newParam.setStatus(StarRecordStatusEnum.REDUCE);
        newParam.setUserNum("M123456");
        newParam.setAccount("13111111");
        userStarRecordService.addUserStartRecord(newParam);
        List<UserStarRecordDO> recordDOS = userStarRecordDOMapper.selectByExample(null);
        Assert.assertEquals(param.getUserNum(), recordDOS.get(0).getUserNum());
        Assert.assertEquals(true, recordDOS.get(0).getStatus());
        Assert.assertEquals(param.getAccount(), recordDOS.get(0).getAccount());
        Assert.assertEquals(0, recordDOS.get(0).getMonthStarCount().intValue());

    }

    @Transactional
    @Test
    @Rollback
    public void getStarRankList() {
        UserStarRecordDO recordDO = new UserStarRecordDO();
        recordDO.setAccount("123456");
        recordDO.setMonthStarCount(10);
        recordDO.setGmtCreate(new Date());
        recordDO.setGmtReport(DateUtil.getFirstDayOfMonth(new Date()));
        recordDO.setUserNum("M123456");
        recordDO.setGmtModified(new Date());
        recordDO.setStatus(true);
        userStarRecordDOMapper.insertSelective(recordDO);
        RankListParam param = new RankListParam();
        param.setReportDate(DateUtil.getFirstDayOfMonth(new Date()));
        param.setCurrentPage(1);
        param.setPageSize(5);

        Page<RankListBO> page = userStarRecordService.getStarRankList(param);
        Assert.assertEquals(1, page.getTotalCount().intValue());
        Assert.assertEquals(recordDO.getUserNum(), page.getRecords().get(0).getUserNum());
        Assert.assertEquals(recordDO.getMonthStarCount(), page.getRecords().get(0).getMonthStarCount());
    }

    @Transactional
    @Test
    @Rollback
    public void currentUserRank(){
        UserRankBO rankBO = userStarRecordService.currentUserRank("M123456");
        Assert.assertEquals(0,rankBO.getRank().intValue());
        Assert.assertEquals(0,rankBO.getStarCount().intValue());
        UserStarRecordDO recordDO = new UserStarRecordDO();
        recordDO.setAccount("123456");
        recordDO.setMonthStarCount(10);
        recordDO.setGmtCreate(new Date());
        recordDO.setGmtReport(DateUtil.getFirstDayOfMonth(new Date()));
        recordDO.setUserNum("M123456");
        recordDO.setGmtModified(new Date());
        recordDO.setStatus(true);
        userStarRecordDOMapper.insertSelective(recordDO);
        UserRankBO rankBO2 = userStarRecordService.currentUserRank(recordDO.getUserNum());
        Assert.assertEquals(1,rankBO2.getRank().intValue());
        Assert.assertEquals(recordDO.getMonthStarCount().intValue(),rankBO2.getStarCount().intValue());
    }
}
