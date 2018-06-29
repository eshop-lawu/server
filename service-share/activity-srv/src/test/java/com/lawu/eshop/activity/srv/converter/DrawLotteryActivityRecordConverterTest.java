package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityRecordOperatorDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityRecordDO;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class DrawLotteryActivityRecordConverterTest {

    @Test
    public void convertBO() {
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserId(10L);
        recordDO.setUserNum("test");
        recordDO.setUserAccount("13666666666");
        recordDO.setDrawLotteryActivityId(1L);
        recordDO.setDrawLotteryActivityPrizeId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setChannel((byte) 1);
        recordDO.setPayPoint(BigDecimal.valueOf(10));
        recordDO.setConsigneeName("test");
        recordDO.setConsigneeMobile("13666666666");
        recordDO.setConsigneeAddress("test");
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        DrawLotteryActivityRecordBO recordBO = DrawLotteryActivityRecordConverter.convertBO(recordDO);
        Assert.assertNotNull(recordBO);
        Assert.assertEquals(recordDO.getId(), recordBO.getId());
    }

    @Test
    public void convertDTO() {
        DrawLotteryActivityRecordBO recordBO = new DrawLotteryActivityRecordBO();
        recordBO.setId(100L);
        recordBO.setUserId(10L);
        recordBO.setUserNum("test");
        recordBO.setUserAccount("13666666666");
        recordBO.setDrawLotteryActivityId(1L);
        recordBO.setDrawLotteryActivityPrizeId(1L);
        recordBO.setPrizeName("test");
        recordBO.setStatus((byte) 1);
        recordBO.setChannel((byte) 1);
        recordBO.setPayPoint(BigDecimal.valueOf(10));
        recordBO.setConsigneeName("test");
        recordBO.setConsigneeMobile("13666666666");
        recordBO.setConsigneeAddress("test");
        recordBO.setGmtModified(new Date());
        recordBO.setGmtCreate(new Date());
        DrawLotteryActivityRecordDTO recordDTO = DrawLotteryActivityRecordConverter.convertDTO(recordBO);
        Assert.assertNotNull(recordDTO);
        Assert.assertEquals(recordBO.getId(), recordDTO.getId());
    }

    @Test
    public void convertBOS() {
        List<DrawLotteryActivityRecordDO> recordDOS = new ArrayList<>();
        DrawLotteryActivityRecordDO recordDO = new DrawLotteryActivityRecordDO();
        recordDO.setId(100L);
        recordDO.setUserId(10L);
        recordDO.setUserNum("test");
        recordDO.setUserAccount("13666666666");
        recordDO.setDrawLotteryActivityId(1L);
        recordDO.setDrawLotteryActivityPrizeId(1L);
        recordDO.setPrizeName("test");
        recordDO.setStatus((byte) 1);
        recordDO.setChannel((byte) 1);
        recordDO.setPayPoint(BigDecimal.valueOf(10));
        recordDO.setConsigneeName("test");
        recordDO.setConsigneeMobile("13666666666");
        recordDO.setConsigneeAddress("test");
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDOS.add(recordDO);
        List<DrawLotteryActivityRecordBO> recordBOS = DrawLotteryActivityRecordConverter.convertBOS(recordDOS);
        Assert.assertNotNull(recordBOS);
        Assert.assertEquals(recordDO.getId(), recordBOS.get(0).getId());
    }

    @Test
    public void convertOperatorDTOS() {
        List<DrawLotteryActivityRecordBO> recordBOS = new ArrayList<>();
        DrawLotteryActivityRecordBO recordBO = new DrawLotteryActivityRecordBO();
        recordBO.setId(100L);
        recordBO.setUserId(10L);
        recordBO.setUserNum("test");
        recordBO.setUserAccount("13666666666");
        recordBO.setDrawLotteryActivityId(1L);
        recordBO.setDrawLotteryActivityPrizeId(1L);
        recordBO.setPrizeName("test");
        recordBO.setStatus((byte) 1);
        recordBO.setChannel((byte) 1);
        recordBO.setPayPoint(BigDecimal.valueOf(10));
        recordBO.setConsigneeName("test");
        recordBO.setConsigneeMobile("13666666666");
        recordBO.setConsigneeAddress("test");
        recordBO.setGmtModified(new Date());
        recordBO.setGmtCreate(new Date());
        recordBOS.add(recordBO);
        List<DrawLotteryActivityRecordOperatorDTO> operatorDTOS = DrawLotteryActivityRecordConverter.convertOperatorDTOS(recordBOS);
        Assert.assertNotNull(operatorDTOS);
        Assert.assertEquals(recordBO.getId(), operatorDTOS.get(0).getId());
    }

}
