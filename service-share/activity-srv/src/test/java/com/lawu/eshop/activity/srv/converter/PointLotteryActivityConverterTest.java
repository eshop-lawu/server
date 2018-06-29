package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.PointLotteryActivityOperatorDTO;
import com.lawu.eshop.activity.dto.PointLotteryDetailDTO;
import com.lawu.eshop.activity.dto.PointLotteryInfoDTO;
import com.lawu.eshop.activity.dto.PointLotteryRelateDTO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;

/**
 * @author meishuquan
 * @date 2018/2/9.
 */
public class PointLotteryActivityConverterTest {

    @Test
    public void convertBO() {
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setPrizeName("test");
        activityDO.setPrizePrice(BigDecimal.valueOf(10));
        activityDO.setPrizeImagePath("png");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setDrawTime(new Date());
        activityDO.setLotteryTime(new Date());
        activityDO.setLotteryPoint(10);
        activityDO.setLotteryCount(100);
        activityDO.setStatus((byte) 1);
        activityDO.setAttentNumber(1000);
        activityDO.setHotNumber(5);
        activityDO.setLotteryParam("test");
        activityDO.setLotteryBaseNum(3);
        activityDO.setLotteryResultNums("3");
        activityDO.setGmtModified(new Date());
        activityDO.setGmtCreate(new Date());
        PointLotteryActivityBO activityBO = PointLotteryActivityConverter.convertBO(activityDO);
        Assert.assertNotNull(activityBO);
        Assert.assertEquals(activityDO.getId(), activityBO.getId());

    }

    @Test
    public void convertBOS() {
        List<PointLotteryActivityDO> activityDOS = new ArrayList<>();
        PointLotteryActivityDO activityDO = new PointLotteryActivityDO();
        activityDO.setId(100L);
        activityDO.setPrizeName("test");
        activityDO.setPrizePrice(BigDecimal.valueOf(10));
        activityDO.setPrizeImagePath("png");
        activityDO.setBeginTime(new Date());
        activityDO.setEndTime(new Date());
        activityDO.setDrawTime(new Date());
        activityDO.setLotteryTime(new Date());
        activityDO.setLotteryPoint(10);
        activityDO.setLotteryCount(100);
        activityDO.setStatus((byte) 1);
        activityDO.setAttentNumber(1000);
        activityDO.setHotNumber(5);
        activityDO.setLotteryParam("test");
        activityDO.setLotteryBaseNum(3);
        activityDO.setLotteryResultNums("3");
        activityDO.setGmtModified(new Date());
        activityDO.setGmtCreate(new Date());
        activityDOS.add(activityDO);
        List<PointLotteryActivityBO> activityBOS = PointLotteryActivityConverter.convertBOS(activityDOS);
        Assert.assertNotNull(activityBOS);
        Assert.assertEquals(activityDO.getId(), activityBOS.get(0).getId());
    }

    @Test
    public void convertInfoDTO() {
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(10));
        activityBO.setPrizeImagePath("png");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setDrawTime(new Date());
        activityBO.setLotteryTime(new Date());
        activityBO.setLotteryPoint(10);
        activityBO.setLotteryCount(100);
        activityBO.setStatus((byte) 1);
        activityBO.setAttentNumber(1000);
        activityBO.setHotNumber(5);
        activityBO.setLotteryParam("test");
        activityBO.setLotteryBaseNum(3);
        activityBO.setLotteryResultNums("3");
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        PointLotteryInfoDTO infoDTO = PointLotteryActivityConverter.convertInfoDTO(activityBO);
        Assert.assertNotNull(infoDTO);
        Assert.assertEquals(activityBO.getId(), infoDTO.getId());
    }

    @Test
    public void convertInfoDTOS() {
        List<PointLotteryActivityBO> activityBOS = new ArrayList<>();
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(10));
        activityBO.setPrizeImagePath("png");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setDrawTime(new Date());
        activityBO.setLotteryTime(new Date());
        activityBO.setLotteryPoint(10);
        activityBO.setLotteryCount(100);
        activityBO.setStatus((byte) 1);
        activityBO.setAttentNumber(1000);
        activityBO.setHotNumber(5);
        activityBO.setLotteryParam("test");
        activityBO.setLotteryBaseNum(3);
        activityBO.setLotteryResultNums("3");
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        activityBOS.add(activityBO);
        List<PointLotteryInfoDTO> infoDTOS = PointLotteryActivityConverter.convertInfoDTOS(activityBOS);
        Assert.assertNotNull(infoDTOS);
        Assert.assertEquals(activityBO.getId(), infoDTOS.get(0).getId());
    }

    @Test
    public void convertDetailDTO() {
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(10));
        activityBO.setPrizeImagePath("png");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setDrawTime(new Date());
        activityBO.setLotteryTime(new Date());
        activityBO.setLotteryPoint(10);
        activityBO.setLotteryCount(100);
        activityBO.setStatus((byte) 1);
        activityBO.setAttentNumber(1000);
        activityBO.setHotNumber(5);
        activityBO.setLotteryParam("test");
        activityBO.setLotteryBaseNum(3);
        activityBO.setLotteryResultNums("3");
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        PointLotteryDetailDTO detailDTO = PointLotteryActivityConverter.convertDetailDTO(activityBO);
        Assert.assertNotNull(detailDTO);
        Assert.assertEquals(activityBO.getId(), detailDTO.getId());
    }

    @Test
    public void convertOperatorDTO() {
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(10));
        activityBO.setPrizeImagePath("png");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setDrawTime(new Date());
        activityBO.setLotteryTime(new Date());
        activityBO.setLotteryPoint(10);
        activityBO.setLotteryCount(100);
        activityBO.setStatus((byte) 1);
        activityBO.setAttentNumber(1000);
        activityBO.setHotNumber(5);
        activityBO.setLotteryParam("test");
        activityBO.setLotteryBaseNum(3);
        activityBO.setLotteryResultNums("3");
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        PointLotteryActivityOperatorDTO operatorDTO = PointLotteryActivityConverter.convertOperatorDTO(activityBO);
        Assert.assertNotNull(operatorDTO);
        Assert.assertEquals(activityBO.getId(), operatorDTO.getId());
    }

    @Test
    public void convertOperatorDTOS() {
        List<PointLotteryActivityBO> activityBOS = new ArrayList<>();
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setPrizePrice(BigDecimal.valueOf(10));
        activityBO.setPrizeImagePath("png");
        activityBO.setBeginTime(new Date());
        activityBO.setEndTime(new Date());
        activityBO.setDrawTime(new Date());
        activityBO.setLotteryTime(new Date());
        activityBO.setLotteryPoint(10);
        activityBO.setLotteryCount(100);
        activityBO.setStatus((byte) 1);
        activityBO.setAttentNumber(1000);
        activityBO.setHotNumber(5);
        activityBO.setLotteryParam("test");
        activityBO.setLotteryBaseNum(3);
        activityBO.setLotteryResultNums("3");
        activityBO.setGmtModified(new Date());
        activityBO.setGmtCreate(new Date());
        activityBOS.add(activityBO);
        List<PointLotteryActivityOperatorDTO> operatorDTOS = PointLotteryActivityConverter.convertOperatorDTOS(activityBOS);
        Assert.assertNotNull(operatorDTOS);
        Assert.assertEquals(activityBO.getId(), operatorDTOS.get(0).getId());
    }

    @Test
    public void convertRelateDTOS() {
        List<PointLotteryActivityBO> activityBOS = new ArrayList<>();
        PointLotteryActivityBO activityBO = new PointLotteryActivityBO();
        activityBO.setId(100L);
        activityBO.setPrizeName("test");
        activityBO.setStatus((byte) 1);
        activityBOS.add(activityBO);

        List<PointLotteryRelateDTO> relateDTOS = PointLotteryActivityConverter.convertRelateDTOS(activityBOS);
        Assert.assertNotNull(relateDTOS);
        Assert.assertEquals(activityBO.getId(), relateDTOS.get(0).getId());
    }

}
