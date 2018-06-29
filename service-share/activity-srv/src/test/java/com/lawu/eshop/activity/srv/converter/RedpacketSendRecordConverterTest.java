package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.RedpacketMchBillnoDTO;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDO;

/**
 * @author meishuquan
 * @date 2018/1/4.
 */
public class RedpacketSendRecordConverterTest {

    @Test
    public void converBO() {
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(100L);
        sendRecordDO.setActivityId(100);
        sendRecordDO.setAttendDetailId(100L);
        sendRecordDO.setUserNum("test");
        sendRecordDO.setOpenid("test");
        sendRecordDO.setReturnCode("test");
        sendRecordDO.setReturnMsg("test");
        sendRecordDO.setResultCode("test");
        sendRecordDO.setErrCodeDes("test");
        sendRecordDO.setErrCodeDes("test");
        sendRecordDO.setMchBillno("test");
        sendRecordDO.setSendListId("test");
        sendRecordDO.setTotalAmount(100);
        sendRecordDO.setStatus((byte) 1);
        sendRecordDO.setRcvTime(new Date());
        sendRecordDO.setRefundTime(new Date());
        sendRecordDO.setGmtModified(new Date());
        sendRecordDO.setGmtCreate(new Date());
        RedpacketSendRecordBO sendRecordBO = RedpacketSendRecordConverter.converBO(sendRecordDO);
        Assert.assertNotNull(sendRecordBO);
        Assert.assertEquals(sendRecordDO.getId(), sendRecordBO.getId());
    }

    @Test
    public void converBOS() {
        List<RedpacketSendRecordDO> sendRecordDOS = new ArrayList<>();
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(100L);
        sendRecordDO.setActivityId(100);
        sendRecordDO.setAttendDetailId(100L);
        sendRecordDO.setUserNum("test");
        sendRecordDO.setOpenid("test");
        sendRecordDO.setReturnCode("test");
        sendRecordDO.setReturnMsg("test");
        sendRecordDO.setResultCode("test");
        sendRecordDO.setErrCodeDes("test");
        sendRecordDO.setErrCodeDes("test");
        sendRecordDO.setMchBillno("test");
        sendRecordDO.setSendListId("test");
        sendRecordDO.setTotalAmount(100);
        sendRecordDO.setStatus((byte) 1);
        sendRecordDO.setRcvTime(new Date());
        sendRecordDO.setRefundTime(new Date());
        sendRecordDO.setGmtModified(new Date());
        sendRecordDO.setGmtCreate(new Date());
        sendRecordDOS.add(sendRecordDO);
        List<RedpacketSendRecordBO> sendRecordBOS = RedpacketSendRecordConverter.converBOS(sendRecordDOS);
        Assert.assertNotNull(sendRecordBOS);
        Assert.assertEquals(sendRecordDO.getId(), sendRecordBOS.get(0).getId());
    }

    @Test
    public void converRedpacketMchBillnoDTOS() {
        List<RedpacketSendRecordBO> sendRecordBOS = new ArrayList<>();
        RedpacketSendRecordBO sendRecordBO = new RedpacketSendRecordBO();
        sendRecordBO.setMchBillno("test");
        sendRecordBO.setAttendDetailId(100L);
        sendRecordBOS.add(sendRecordBO);
        List<RedpacketMchBillnoDTO> mchBillnoDTOS = RedpacketSendRecordConverter.converRedpacketMchBillnoDTOS(sendRecordBOS);
        Assert.assertNotNull(mchBillnoDTOS);
        Assert.assertEquals(sendRecordBO.getMchBillno(), mchBillnoDTOS.get(0).getMchBillno());
        Assert.assertEquals(sendRecordBO.getAttendDetailId(), mchBillnoDTOS.get(0).getAttendDetailId());
    }

}
