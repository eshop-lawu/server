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

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.HelpTypeEnum;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.AttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendPageBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDO;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.framework.core.page.Page;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketInviteRecordDOMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class HelpRedpacketAttendDetailServiceImplTest {

	@Autowired
	private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;
	
	@Autowired
	private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
	
    @Autowired
    private HelpRedpacketInviteRecordDOMapper helpRedpacketInviteRecordDOMapper;

    @Autowired
    private HelpRedpacketActivityDOMapper helpRedpacketActivityDOMapper;
	
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void helpRedpacketAttend() {
        HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setTotalAutoAmount(new BigDecimal(0));
        expected.setTotalManualAmount(new BigDecimal(0));
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(expected);

        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("18267122441");
        record.setHeadimg("head/1503319642099389798.jpg");
        record.setNickname("E店用户");
        record.setUserNum("M894378717298556928");
        record.setWxOpenid("1234");
        record.setGmtCreate(new Date());
        record.setActivityId(expected.getId());
        record.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.insertSelective(record);

        HelpRedpacketAttendParam attendParam = new HelpRedpacketAttendParam();
        attendParam.setActivityId(expected.getId());
        attendParam.setAccount("18267122441");
        attendParam.setHeadimg("head/1503319642099389798.jpg");
        attendParam.setNickname("E店用户");
        attendParam.setUserNum(record.getUserNum());
        attendParam.setWxOpenid("1234");
        // 重复助力
        AttendBO attendBO = helpRedpacketAttendDetailService.helpRedpacketAttend(attendParam);
        Assert.assertNotNull(attendBO);
        Assert.assertTrue(attendBO.getIsAttend());
        
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        example.createCriteria().andActivityIdEqualTo(attendParam.getActivityId()).andUserNumEqualTo(attendParam.getUserNum());
        long count = helpRedpacketAttendDetailDOMapper.countByExample(example);
        Assert.assertEquals(1, count);
        
        attendParam.setUserNum("M894378717298556929");
        helpRedpacketAttendDetailService.helpRedpacketAttend(attendParam);
        
        example = new HelpRedpacketAttendDetailDOExample();
        example.createCriteria().andActivityIdEqualTo(attendParam.getActivityId()).andUserNumEqualTo(attendParam.getUserNum());
        HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByExample(example).get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(attendParam.getActivityId(), actual.getActivityId());
        Assert.assertEquals(attendParam.getAccount(), actual.getAccount());
        Assert.assertEquals(attendParam.getHeadimg(), actual.getHeadimg());
        Assert.assertEquals(attendParam.getNickname(), actual.getNickname());
        Assert.assertEquals(attendParam.getUserNum(), actual.getUserNum());
        Assert.assertEquals(attendParam.getWxOpenid(), actual.getWxOpenid());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void helpRedpacketAttendPageOperator() {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setStartPic("startPic.jpg");
        helpRedpacketActivityDO.setEndPic("endPic.jpg");
        helpRedpacketActivityDO.setEndUrl("endUrl");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);

        HelpRedpacketAttendDetailDO expected = new HelpRedpacketAttendDetailDO();
        expected.setAccount("18267122441");
        expected.setHeadimg("head/1503319642099389798.jpg");
        expected.setNickname("E店用户");
        expected.setUserNum("M894378717298556928");
        expected.setWxOpenid("1234");
        expected.setGmtCreate(new Date());
        expected.setActivityId(helpRedpacketActivityDO.getId());
        expected.setGmtModified(new Date());
        expected.setFinalMoney(0);
        helpRedpacketAttendDetailDOMapper.insertSelective(expected);

        HelpRedpacketDetailOperatorParam detailParam = new HelpRedpacketDetailOperatorParam();
        detailParam.setActivityId(helpRedpacketActivityDO.getId());
        detailParam.setPageSize(20);
        detailParam.setCurrentPage(1);
        Page<HelpRedpacketAttendPageBO> page = helpRedpacketAttendDetailService
                .helpRedpacketAttendPageOperator(detailParam);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(detailParam.getCurrentPage(), page.getCurrentPage());
        Assert.assertTrue(page.getRecords().size() > 0);
        for (HelpRedpacketAttendPageBO actual : page.getRecords()) {
            Assert.assertNotNull(actual);
            Assert.assertNotNull(actual.getGmtCreate());
            Assert.assertEquals(expected.getAccount(), actual.getAccount());
            Assert.assertEquals(expected.getAllotTime(), actual.getAllotTime());
            Assert.assertEquals(expected.getFinalMoney().intValue(), actual.getFinalMoney().multiply(new BigDecimal(100)).intValue());
            Assert.assertEquals(expected.getHeadimg(), actual.getHeadimg());
            Assert.assertEquals(0, actual.getHelpCount().intValue());
            Assert.assertEquals(expected.getNickname(), actual.getNickname());
            Assert.assertEquals(expected.getSendRemark(), actual.getSendRemark());
            Assert.assertEquals(expected.getSendTime(), actual.getSendTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ATTEND, actual.getStatusEnum());
            Assert.assertEquals(expected.getTakeTime(), actual.getTakeTime());
        }
    }

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void helpRedpacketAttendDetail() {
	    HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setTotalAutoAmount(new BigDecimal(0));
        expected.setTotalManualAmount(new BigDecimal(0));
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(expected);
	    
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setGmtCreate(new Date());
		record.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		
		HelpRedpacketAttendBO helpAttendBO = helpRedpacketAttendDetailService.helpRedpacketAttendDetail(expected.getId(), "M894378717298556928", 10);
		Assert.assertNotNull(helpAttendBO);
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByPrimaryKey() {
		
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setGmtCreate(new Date());
		record.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		
		HelpRedpacketAttendDetailBO helpAttendBO = helpRedpacketAttendDetailService.selectByPrimaryKey(record.getId());
		Assert.assertNotNull(helpAttendBO);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void addHelpCount() {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setStartPic("startPic.jpg");
        helpRedpacketActivityDO.setEndPic("endPic.jpg");
        helpRedpacketActivityDO.setEndUrl("endUrl");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
	    
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setGmtCreate(new Date());
		record.setActivityId(helpRedpacketActivityDO.getId());
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		
		helpRedpacketAttendDetailService.addHelpCount(record.getId());
		
		HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(record.getId());
		Assert.assertEquals(1, actual.getHelpCount().intValue());
    }
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByAttendUserNumAndActivityId() {
		
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setGmtCreate(new Date());
		record.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		
		HelpRedpacketAttendDetailBO  helpRedpacketAttendDetailBO = helpRedpacketAttendDetailService.selectByAttendUserNum(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID, "M894378717298556928");
		Assert.assertNotNull(helpRedpacketAttendDetailBO);
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getHelpRedpacket() {
		
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
		record.setGmtCreate(new Date());
		record.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		
		HelpRedpacketBO  helpRedpacketBO = helpRedpacketAttendDetailService.getHelpRedpacket(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID, "M894378717298556928");
		Assert.assertNotNull(helpRedpacketBO);
    }

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listSendRedpacketAttendDetail() {
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount("18267122441");
		record.setHeadimg("head/1503319642099389798.jpg");
		record.setNickname("E店用户");
		record.setUserNum("M894378717298556928");
		record.setWxOpenid("1234");
		record.setStatus(ActivityAttendStatusEnum.GET.getVal());
		record.setGmtCreate(new Date());
		record.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);

		List<HelpRedpacketAttendDetailBO> attendDetailBOS = helpRedpacketAttendDetailService.listSendRedpacketAttendDetail(0, 10);
		Assert.assertNotNull(attendDetailBOS);
		Assert.assertEquals(record.getAccount(), attendDetailBOS.get(0).getAccount());
	}

    @Transactional
    @Rollback
    @Test
    public void identifiedAsAbnormal() {
        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = new HelpRedpacketAttendDetailDO();
        helpRedpacketAttendDetailDO.setAccount("18267122441");
        helpRedpacketAttendDetailDO.setHeadimg("head/1503319642099389798.jpg");
        helpRedpacketAttendDetailDO.setNickname("E店用户");
        helpRedpacketAttendDetailDO.setUserNum("M894378717298556928");
        helpRedpacketAttendDetailDO.setWxOpenid("1234");
        helpRedpacketAttendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
        helpRedpacketAttendDetailDO.setGmtCreate(new Date());
        helpRedpacketAttendDetailDO.setActivityId(1);
        helpRedpacketAttendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDO.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        helpRedpacketAttendDetailDO.setAbnormalStatus(AbnormalStatusEnum.NORMAL.getVal());
        helpRedpacketAttendDetailDOMapper.insert(helpRedpacketAttendDetailDO);

        HelpRedpacketInviteRecordDO helpRedpacketInviteRecordDO = new HelpRedpacketInviteRecordDO();
        helpRedpacketInviteRecordDO.setActivityId(1);
        helpRedpacketInviteRecordDO.setAttendId(helpRedpacketAttendDetailDO.getId());
        helpRedpacketInviteRecordDO.setAttendUserNum(helpRedpacketAttendDetailDO.getUserNum());
        helpRedpacketInviteRecordDO.setGmtCreate(new Date());
        helpRedpacketInviteRecordDO.setHelpType(HelpTypeEnum.REF.getVal());
        helpRedpacketInviteRecordDO.setHelpUserAccount("123456");
        helpRedpacketInviteRecordDO.setHelpUserHeadimg("test.jpg");
        helpRedpacketInviteRecordDO.setHelpUserNum("M123456");
        helpRedpacketInviteRecordDOMapper.insert(helpRedpacketInviteRecordDO);

        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO1 = new HelpRedpacketAttendDetailDO();
        helpRedpacketAttendDetailDO1.setAccount(helpRedpacketInviteRecordDO.getHelpUserAccount());
        helpRedpacketAttendDetailDO1.setHeadimg("head/1503319642099389798.jpg");
        helpRedpacketAttendDetailDO1.setNickname("E店用户");
        helpRedpacketAttendDetailDO1.setUserNum(helpRedpacketInviteRecordDO.getHelpUserNum());
        helpRedpacketAttendDetailDO1.setWxOpenid("1234");
        helpRedpacketAttendDetailDO1.setStatus(ActivityAttendStatusEnum.GET.getVal());
        helpRedpacketAttendDetailDO1.setGmtCreate(new Date());
        helpRedpacketAttendDetailDO1.setActivityId(1);
        helpRedpacketAttendDetailDO1.setGmtModified(new Date());
        helpRedpacketAttendDetailDO1.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        helpRedpacketAttendDetailDO1.setAbnormalStatus(AbnormalStatusEnum.NORMAL.getVal());
        helpRedpacketAttendDetailDOMapper.insert(helpRedpacketAttendDetailDO1);
/*
        List<IdentifiedAsAbnormalBO> inviteUserNumList = helpRedpacketAttendDetailService.identifiedAsAbnormal(helpRedpacketAttendDetailDO.getUserNum());
        Assert.assertEquals(helpRedpacketAttendDetailDO1.getUserNum(), inviteUserNumList.get(0).getUserNum());
        Assert.assertEquals(helpRedpacketAttendDetailDO1.getAccount(), inviteUserNumList.get(0).getAccount());*/
        
        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDOActual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(helpRedpacketAttendDetailDO.getId());
        Assert.assertEquals(AbnormalStatusEnum.ABNORMAL.getVal(), helpRedpacketAttendDetailDOActual.getAbnormalStatus());

        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO1Actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(helpRedpacketAttendDetailDO1.getId());
        Assert.assertEquals(AbnormalStatusEnum.ABNORMAL.getVal(), helpRedpacketAttendDetailDO1Actual.getAbnormalStatus());
    }

}
