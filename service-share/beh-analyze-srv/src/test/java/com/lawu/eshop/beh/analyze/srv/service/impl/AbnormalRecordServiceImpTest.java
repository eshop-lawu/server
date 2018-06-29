package com.lawu.eshop.beh.analyze.srv.service.impl;

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

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.param.AbnormalAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalJobAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalParam;
import com.lawu.eshop.beh.analyze.srv.BehAnalyzeSrvApplicationTest;
import com.lawu.eshop.beh.analyze.srv.EmbeddedRedis;
import com.lawu.eshop.beh.analyze.srv.bo.AbnormalBO;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.beh.analyze.srv.mapper.InviteAbnormalDecideRecordDOMapper;
import com.lawu.eshop.beh.analyze.srv.service.AbnormalRecordService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BehAnalyzeSrvApplicationTest.class, properties = {"lawu.synchronization-lock.redisson.enabled=true"})
public class AbnormalRecordServiceImpTest extends EmbeddedRedis {

    @Autowired
    private AbnormalRecordService abnormalRecordService;

    @Autowired
    private InviteAbnormalDecideRecordDOMapper inviteAbnormalDecideRecordDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void getAbnormalRecord() {
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M123456");
        recordDO.setAccount("1311111111");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setIsAbnormal(true);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        AbnormalParam param = new AbnormalParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setAccount(recordDO.getAccount());
        param.setUserType(UserTypeEnum.getEnum(recordDO.getType()));
        param.setProcessType(ProcessEnum.getEnum(recordDO.getProcessType()));

        Page<AbnormalBO> page = abnormalRecordService.getAbnormalRecord(param);
        Assert.assertEquals(1, page.getTotalCount().intValue());
        Assert.assertEquals(recordDO.getAccount(), page.getRecords().get(0).getAccount());
        Assert.assertEquals(recordDO.getUserNum(), page.getRecords().get(0).getUserNum());
        Assert.assertEquals(recordDO.getIsEarlyHf(), page.getRecords().get(0).getEarlyHf());
        Assert.assertEquals(recordDO.getIsLongHf(), page.getRecords().get(0).getLongHf());
        Assert.assertEquals(recordDO.getIsShortHf(), page.getRecords().get(0).getShortHf());
        Assert.assertEquals(recordDO.getIsManyLongHf(), page.getRecords().get(0).getManyLongHf());
        Assert.assertEquals(recordDO.getIsManyShortHf(), page.getRecords().get(0).getManyShortHf());
        Assert.assertEquals(recordDO.getIsOneDayHf(), page.getRecords().get(0).getOneDayHf());

    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void editAbnormalRecord() {
        AbnormalJobAddParam param = new AbnormalJobAddParam();
        param.setUserNum("M12345677");
        param.setAccount("131111111");
        param.setType(UserTypeEnum.MEMBER);
        abnormalRecordService.editAbnormalRecord(param);
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExample(null);
        Assert.assertEquals(1, list.size());
        Assert.assertEquals(param.getAccount(), list.get(0).getAccount());
        Assert.assertEquals(param.getUserNum(), list.get(0).getUserNum());
        Assert.assertEquals(true, list.get(0).getIsIpHf());
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M1234567676");
        recordDO.setAccount("1311111112");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setIsAbnormal(false);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        AbnormalJobAddParam param2 = new AbnormalJobAddParam();
        param2.setUserNum(recordDO.getUserNum());
        param2.setAccount(recordDO.getAccount());
        param2.setType(UserTypeEnum.getEnum(recordDO.getType()));
        abnormalRecordService.editAbnormalRecord(param2);
        InviteAbnormalDecideRecordDO old = inviteAbnormalDecideRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertEquals(recordDO.getAccount(), old.getAccount());
        Assert.assertEquals(recordDO.getUserNum(), old.getUserNum());
        Assert.assertEquals(true, old.getIsIpHf());
        Assert.assertEquals(true, old.getIsAbnormal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addOrUpdateAbnormalRecord() {
        AbnormalAddParam param = new AbnormalAddParam();
        param.setAbnormal(true);
        param.setEarlyHf(true);
        param.setShortHf(true);
        param.setIpHf(true);
        param.setManyShortHf(true);
        param.setLongHf(true);
        param.setManyLongHf(true);
        param.setOneDayHf(true);
        param.setUserNum("M123456");
        param.setAccount("13123648");
        param.setType(UserTypeEnum.MEMBER);
        abnormalRecordService.addOrUpdateAbnormalRecord(param);
        List<InviteAbnormalDecideRecordDO> list = inviteAbnormalDecideRecordDOMapper.selectByExample(null);
        Assert.assertEquals(param.getAccount(), list.get(0).getAccount());
        Assert.assertEquals(param.getUserNum(), list.get(0).getUserNum());
        Assert.assertEquals(param.getShortHf(), list.get(0).getIsAbnormal());
        Assert.assertEquals(param.getManyShortHf(), list.get(0).getIsManyShortHf());

        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M1234567676");
        recordDO.setAccount("1311111112");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setIsAbnormal(false);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        AbnormalAddParam param2 = new AbnormalAddParam();
        param2.setAbnormal(true);
        param2.setEarlyHf(true);
        param2.setShortHf(true);
        param2.setIpHf(true);
        param2.setManyShortHf(true);
        param2.setLongHf(true);
        param2.setManyLongHf(true);
        param2.setOneDayHf(true);
        param2.setUserNum(recordDO.getUserNum());
        param2.setAccount(recordDO.getAccount());
        param2.setType(UserTypeEnum.getEnum(recordDO.getType()));
        abnormalRecordService.addOrUpdateAbnormalRecord(param2);
        InviteAbnormalDecideRecordDO old = inviteAbnormalDecideRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertEquals(param2.getAccount(), old.getAccount());
        Assert.assertEquals(param2.getUserNum(), old.getUserNum());
        Assert.assertEquals(param2.getShortHf(), old.getIsAbnormal());
        Assert.assertEquals(param2.getManyShortHf(), old.getIsManyShortHf());
        Assert.assertEquals(true, old.getIsAbnormal());
        Assert.assertEquals(true, old.getIsNoticed());
    }
}
