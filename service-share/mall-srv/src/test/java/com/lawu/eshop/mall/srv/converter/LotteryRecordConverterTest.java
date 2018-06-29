package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.LotteryRecordDTO;
import com.lawu.eshop.mall.dto.LotteryRecordOperatorDTO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordBO;
import com.lawu.eshop.mall.srv.bo.LotteryRecordOperatorBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;
import com.lawu.eshop.mall.srv.domain.LotteryRecordDO;

/**
 * @author meishuquan
 * @date 2017/11/28.
 */
public class LotteryRecordConverterTest {

    @Test
    public void converBO() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setPrizeName("test");
        activityDO.setPrizePrice(BigDecimal.valueOf(100));
        activityDO.setImagePath("test");
        activityDO.setEndTime(new Date());
        LotteryRecordBO recordBO = LotteryRecordConverter.converBO(activityDO);
        Assert.assertEquals(activityDO.getPrizeName(), recordBO.getPrizeName());
    }

    @Test
    public void converDTO() {
        LotteryRecordBO recordBO = new LotteryRecordBO();
        recordBO.setPrizeName("test");
        recordBO.setPrizePrice(BigDecimal.valueOf(100));
        recordBO.setImagePath("test");
        recordBO.setEndTime(new Date());
        recordBO.setLotteryNumber(10);
        recordBO.setLotteryAccountList(new ArrayList<>());
        LotteryRecordDTO recordDTO = LotteryRecordConverter.converDTO(recordBO);
        Assert.assertEquals(recordBO.getPrizeName(), recordDTO.getPrizeName());
    }

    @Test
    public void converDTOS() {
        List<LotteryRecordBO> recordBOS = new ArrayList<>();
        LotteryRecordBO recordBO = new LotteryRecordBO();
        recordBO.setPrizeName("test");
        recordBO.setPrizePrice(BigDecimal.valueOf(100));
        recordBO.setImagePath("test");
        recordBO.setEndTime(new Date());
        recordBO.setLotteryNumber(10);
        recordBO.setLotteryAccountList(new ArrayList<>());
        recordBOS.add(recordBO);
        List<LotteryRecordDTO> recordDTOS = LotteryRecordConverter.converDTOS(recordBOS);
        Assert.assertNotNull(recordDTOS);
        Assert.assertEquals(recordBO.getPrizeName(), recordDTOS.get(0).getPrizeName());
    }

    @Test
    public void converOperatorBOS() {
        List<LotteryRecordDO> recordDOS = new ArrayList<>();
        LotteryRecordDO recordDO = new LotteryRecordDO();
        recordDO.setId(100L);
        recordDO.setAccount("13666666666");
        recordDO.setPrizeName("test");
        recordDO.setLotteryCount(10);
        recordDO.setLotteryResult(true);
        recordDOS.add(recordDO);
        List<LotteryRecordOperatorBO> operatorBOS = LotteryRecordConverter.converOperatorBOS(recordDOS);
        Assert.assertNotNull(operatorBOS);
        Assert.assertEquals(recordDO.getPrizeName(), operatorBOS.get(0).getPrizeName());
    }

    @Test
    public void converOperatorDTOS() {
        List<LotteryRecordOperatorBO> operatorBOS = new ArrayList<>();
        LotteryRecordOperatorBO operatorBO = new LotteryRecordOperatorBO();
        operatorBO.setId(100L);
        operatorBO.setAccount("13666666666");
        operatorBO.setPrizeName("test");
        operatorBO.setLotteryCount(10);
        operatorBO.setLotteryResult(true);
        operatorBOS.add(operatorBO);
        List<LotteryRecordOperatorDTO> operatorDTOS = LotteryRecordConverter.converOperatorDTOS(operatorBOS);
        Assert.assertNotNull(operatorDTOS);
        Assert.assertEquals(operatorBO.getPrizeName(), operatorDTOS.get(0).getPrizeName());
    }

}
