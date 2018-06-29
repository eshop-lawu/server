package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.dto.LotteryActivityDTO;
import com.lawu.eshop.mall.dto.LotteryActivityOperatorDTO;
import com.lawu.eshop.mall.srv.bo.LotteryActivityBO;
import com.lawu.eshop.mall.srv.domain.LotteryActivityDO;

/**
 * @author meishuquan
 * @date 2017/11/28.
 */
public class LotteryActivityConverterTest {

    @Test
    public void converBO() {
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setPrizeName("test");
        activityDO.setPrizePrice(BigDecimal.valueOf(100));
        activityDO.setPrizeNumber(1);
        activityDO.setImagePath("test");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setGrade((byte) 1);
        activityDO.setStatus((byte) 1);
        LotteryActivityBO activityBO = LotteryActivityConverter.converBO(activityDO);
        Assert.assertEquals(activityBO.getId(), activityDO.getId());
    }

    @Test
    public void converDTO() {
        LotteryActivityBO activityBO = new LotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(100));
        activityBO.setPrizeNumber(1);
        activityBO.setImagePath("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        LotteryActivityDTO activityDTO = LotteryActivityConverter.converDTO(activityBO);
        Assert.assertEquals(activityBO.getId(), activityDTO.getLotteryActivityId());
    }

    @Test
    public void converBOS() {
        List<LotteryActivityDO> activityDOS = new ArrayList<>();
        LotteryActivityDO activityDO = new LotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setPrizeName("test");
        activityDO.setPrizePrice(BigDecimal.valueOf(100));
        activityDO.setPrizeNumber(1);
        activityDO.setImagePath("test");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setGrade((byte) 1);
        activityDO.setStatus((byte) 1);
        activityDOS.add(activityDO);
        List<LotteryActivityBO> activityBOS = LotteryActivityConverter.converBOS(activityDOS);
        Assert.assertNotNull(activityBOS);
        Assert.assertEquals(activityDO.getId(), activityBOS.get(0).getId());
    }

    @Test
    public void converDTOS() {
        List<LotteryActivityBO> activityBOS = new ArrayList<>();
        LotteryActivityBO activityBO = new LotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(100));
        activityBO.setPrizeNumber(1);
        activityBO.setImagePath("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBOS.add(activityBO);
        List<LotteryActivityDTO> activityDTOS = LotteryActivityConverter.converDTOS(activityBOS);
        Assert.assertNotNull(activityDTOS);
        Assert.assertEquals(activityBO.getId(), activityDTOS.get(0).getLotteryActivityId());
    }

    @Test
    public void converOperatorDTO() {
        LotteryActivityBO activityBO = new LotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(100));
        activityBO.setPrizeNumber(1);
        activityBO.setImagePath("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        LotteryActivityOperatorDTO activityOperatorDTO = LotteryActivityConverter.converOperatorDTO(activityBO);
        Assert.assertEquals(activityBO.getId(), activityOperatorDTO.getId());
    }

    @Test
    public void converOperatorDTOS() {
        List<LotteryActivityBO> activityBOS = new ArrayList<>();
        LotteryActivityBO activityBO = new LotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(100));
        activityBO.setPrizeNumber(1);
        activityBO.setImagePath("test");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setGrade((byte) 1);
        activityBO.setStatus((byte) 1);
        activityBOS.add(activityBO);
        List<LotteryActivityOperatorDTO> activityOperatorDTOS = LotteryActivityConverter.converOperatorDTOS(activityBOS);
        Assert.assertNotNull(activityOperatorDTOS);
        Assert.assertEquals(activityBO.getId(), activityOperatorDTOS.get(0).getId());
    }

}
