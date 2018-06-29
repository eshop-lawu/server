package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;

/**
 * @author meishuquan
 * @date 2018/1/4.
 */
public class HelpRedpacketAttendDetailConverterTest {

    @Test
    public void converterBOS() {
        List<HelpRedpacketAttendDetailDO> attendDetailDOS = new ArrayList<>();
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setAccount("test");
        attendDetailDO.setHeadimg("test");
        attendDetailDO.setUserNum("test");
        attendDetailDO.setId(100L);
        attendDetailDO.setActivityId(100);
        attendDetailDO.setWxOpenid("test");
        attendDetailDO.setFinalMoney(100);
        attendDetailDOS.add(attendDetailDO);
        List<HelpRedpacketAttendDetailBO> attendDetailBOS = HelpRedpacketAttendDetailConverter.converterBOS(attendDetailDOS);
        Assert.assertNotNull(attendDetailBOS);
        Assert.assertEquals(attendDetailDO.getAccount(), attendDetailBOS.get(0).getAccount());
    }

    @Test
    public void converterDTOS() {
        List<HelpRedpacketAttendDetailBO> attendDetailBOS = new ArrayList<>();
        HelpRedpacketAttendDetailBO attendDetailBO = new HelpRedpacketAttendDetailBO();
        attendDetailBO.setId(100L);
        attendDetailBO.setActivityId(100);
        attendDetailBO.setUserNum("test");
        attendDetailBO.setWxOpenid("test");
        attendDetailBO.setFinalMoney(BigDecimal.valueOf(100));
        attendDetailBOS.add(attendDetailBO);
        List<HelpRedpacketAttendSendDTO> attendSendDTOS = HelpRedpacketAttendDetailConverter.converterDTOS(attendDetailBOS);
        Assert.assertNotNull(attendSendDTOS);
        Assert.assertEquals(attendDetailBO.getId(), attendSendDTOS.get(0).getId());
    }

}
