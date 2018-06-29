package com.lawu.eshop.beh.analyze.srv.service.impl;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.srv.BehAnalyzeSrvApplicationTest;
import com.lawu.eshop.beh.analyze.srv.bo.InviteAbnormalDecideRecordBO;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.beh.analyze.srv.mapper.InviteAbnormalDecideRecordDOMapper;
import com.lawu.eshop.beh.analyze.srv.service.InviteAbnormalDecideRecordService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BehAnalyzeSrvApplicationTest.class)
public class InviteAbnormalDecideRecordServiceTest {

    @Autowired
    private InviteAbnormalDecideRecordService inviteAbnormalDecideRecordService;

    @Autowired
    private InviteAbnormalDecideRecordDOMapper inviteAbnormalDecideRecordDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void get() {
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
        InviteAbnormalDecideRecordBO recordBO = inviteAbnormalDecideRecordService.get(recordDO.getId());
        Assert.assertEquals(recordDO.getAccount(), recordBO.getAccount());
        Assert.assertEquals(recordDO.getUserNum(), recordBO.getUserNum());
        Assert.assertEquals(recordDO.getIsEarlyHf(), recordBO.getIsEarlyHf());
        Assert.assertEquals(recordDO.getIsIpHf(), recordBO.getIsIpHf());
        Assert.assertEquals(recordDO.getIsLongHf(), recordBO.getIsLongHf());
        Assert.assertEquals(recordDO.getIsManyLongHf(), recordBO.getIsManyLongHf());
        Assert.assertEquals(recordDO.getIsManyShortHf(), recordBO.getIsManyShortHf());
        Assert.assertEquals(recordDO.getIsOneDayHf(), recordBO.getIsOneDayHf());

        Assert.assertEquals(recordDO.getIsShortHf(), recordBO.getIsShortHf());
        Assert.assertEquals(recordDO.getIsAbnormal(), recordBO.getIsAbnormal());
        Assert.assertEquals(recordDO.getIsNoticed(), recordBO.getIsNoticed());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void notProcessed() {
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
        inviteAbnormalDecideRecordService.notProcessed(recordDO.getId());
        InviteAbnormalDecideRecordDO newRecord = inviteAbnormalDecideRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertEquals(ProcessEnum.NOT_HANDLE.getVal(), newRecord.getProcessType());
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void freeze() {
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
        inviteAbnormalDecideRecordService.freeze(recordDO.getId());
        InviteAbnormalDecideRecordDO newRecord = inviteAbnormalDecideRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertEquals(ProcessEnum.FREEZE.getVal(), newRecord.getProcessType());
    }

}
