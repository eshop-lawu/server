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

import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDO;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.RedpacketSendRecordDOMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RedpacketSendRecordServiceImplTest {

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    @Autowired
    private RedpacketSendRecordDOMapper redpacketSendRecordDOMapper;

    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listSendRedpacketMchBillno() {
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setActivityId(100);
        sendRecordDO.setAttendDetailId(100L);
        sendRecordDO.setStatus(RedpacketSendRecordStatusEnum.SENDING.getVal());
        redpacketSendRecordDOMapper.insertSelective(sendRecordDO);

        List<RedpacketSendRecordBO> sendRecordBOS = redpacketSendRecordService.listSendRedpacketMchBillno(0, 10);
        Assert.assertNotNull(sendRecordBOS);
        Assert.assertEquals(sendRecordDO.getId(), sendRecordBOS.get(0).getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveRedpacketSendRecord() {
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setAccount("18267122441");
        attendDetailDO.setHeadimg("head/1503319642099389798.jpg");
        attendDetailDO.setNickname("E店用户");
        attendDetailDO.setUserNum("M894378717298556928");
        attendDetailDO.setWxOpenid("1234");
        attendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
        attendDetailDO.setGmtCreate(new Date());
        attendDetailDO.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.insertSelective(attendDetailDO);

        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setActivityId(100);
        param.setAttendDetailId(attendDetailDO.getId());
        param.setUserNum("test");
        param.setOpenid("test");
        param.setReturnCode("success");
        param.setReturnMsg("test");
        param.setResultCode("success");
        param.setErrCode("10");
        param.setErrCodeDes("10");
        param.setMchBillno("12");
        param.setSendListId("14");
        param.setTotalAmount(100);
        param.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        redpacketSendRecordService.saveRedpacketSendRecord(param);

        attendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(attendDetailDO.getId());
        Assert.assertNotNull(attendDetailDO);
        Assert.assertEquals(1, attendDetailDO.getSendCount().intValue());
        Assert.assertEquals(ActivityAttendStatusEnum.SEND.getVal().byteValue(), attendDetailDO.getStatus().byteValue());

        List<RedpacketSendRecordDO> sendRecordDOS = redpacketSendRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(sendRecordDOS);
        Assert.assertEquals(1, sendRecordDOS.size());
        Assert.assertEquals(param.getUserNum(), sendRecordDOS.get(0).getUserNum());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateRedpacketSendRecordStatus() {
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setAccount("18267122441");
        attendDetailDO.setHeadimg("head/1503319642099389798.jpg");
        attendDetailDO.setNickname("E店用户");
        attendDetailDO.setUserNum("M894378717298556928");
        attendDetailDO.setWxOpenid("1234");
        attendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
        attendDetailDO.setGmtCreate(new Date());
        attendDetailDO.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.insertSelective(attendDetailDO);

        RedpacketSendRecordParam saveParam = new RedpacketSendRecordParam();
        saveParam.setActivityId(100);
        saveParam.setAttendDetailId(attendDetailDO.getId());
        saveParam.setUserNum("test");
        saveParam.setOpenid("test");
        saveParam.setReturnCode("success");
        saveParam.setReturnMsg("test");
        saveParam.setResultCode("success");
        saveParam.setErrCode("10");
        saveParam.setErrCodeDes("10");
        saveParam.setMchBillno("12");
        saveParam.setSendListId("14");
        saveParam.setTotalAmount(100);
        saveParam.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        redpacketSendRecordService.saveRedpacketSendRecord(saveParam);

        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setAttendDetailId(attendDetailDO.getId());
        param.setMchBillno("12");
        param.setRcvTime(new Date());
        param.setRefundTime(new Date());
        param.setStatusEnum(RedpacketSendRecordStatusEnum.RECEIVED);
        redpacketSendRecordService.updateRedpacketSendRecordStatus(param);

        attendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(attendDetailDO.getId());
        Assert.assertNotNull(attendDetailDO);
        Assert.assertEquals(1, attendDetailDO.getSendCount().intValue());
        Assert.assertEquals(ActivityAttendStatusEnum.SEND_SUCCESS.getVal().byteValue(), attendDetailDO.getStatus().byteValue());

        List<RedpacketSendRecordDO> sendRecordDOS = redpacketSendRecordDOMapper.selectByExample(null);
        Assert.assertNotNull(sendRecordDOS);
        Assert.assertEquals(1, sendRecordDOS.size());
        Assert.assertEquals(RedpacketSendRecordStatusEnum.RECEIVED.getVal(), sendRecordDOS.get(0).getStatus());
    }

}
