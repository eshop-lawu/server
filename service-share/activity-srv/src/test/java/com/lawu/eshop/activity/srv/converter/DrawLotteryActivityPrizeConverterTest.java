package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDTO;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.activity.srv.bo.DrawLotteryActivityPrizeBO;
import com.lawu.eshop.activity.srv.domain.DrawLotteryActivityPrizeDO;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityPrizeConverterTest {

    @Test
    public void convertBO() {
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setId(100L);
        prizeDO.setDrawLotteryActivityId(10L);
        prizeDO.setName("test");
        prizeDO.setImgPath("png");
        prizeDO.setPrice(BigDecimal.valueOf(10));
        prizeDO.setNumber(20);
        prizeDO.setInventory(20);
        prizeDO.setStatus((byte) 1);
        prizeDO.setFreightPrice(BigDecimal.valueOf(0));
        prizeDO.setMerchantStoreId(10L);
        prizeDO.setAdImgPath("png");
        prizeDO.setRate(BigDecimal.valueOf(10));
        prizeDO.setGmtModified(new Date());
        prizeDO.setGmtCreate(new Date());
        DrawLotteryActivityPrizeBO prizeBO = DrawLotteryActivityPrizeConverter.convertBO(prizeDO);
        Assert.assertNotNull(prizeBO);
        Assert.assertEquals(prizeDO.getId(), prizeBO.getId());
    }

    @Test
    public void convertDTO() {
        DrawLotteryActivityPrizeBO prizeBO = new DrawLotteryActivityPrizeBO();
        prizeBO.setId(100L);
        prizeBO.setDrawLotteryActivityId(10L);
        prizeBO.setName("test");
        prizeBO.setImgPath("png");
        prizeBO.setPrice(BigDecimal.valueOf(10));
        prizeBO.setNumber(20);
        prizeBO.setInventory(20);
        prizeBO.setStatus((byte) 1);
        prizeBO.setFreightPrice(BigDecimal.valueOf(0));
        prizeBO.setMerchantStoreId(10L);
        prizeBO.setAdImgPath("png");
        prizeBO.setRate(BigDecimal.valueOf(10));
        prizeBO.setGmtModified(new Date());
        prizeBO.setGmtCreate(new Date());
        DrawLotteryActivityPrizeDTO prizeDTO = DrawLotteryActivityPrizeConverter.convertDTO(prizeBO);
        Assert.assertNotNull(prizeDTO);
        Assert.assertEquals(prizeBO.getId(), prizeDTO.getId());
    }

    @Test
    public void convertBOS() {
        List<DrawLotteryActivityPrizeDO> prizeDOS = new ArrayList<>();
        DrawLotteryActivityPrizeDO prizeDO = new DrawLotteryActivityPrizeDO();
        prizeDO.setId(100L);
        prizeDO.setDrawLotteryActivityId(10L);
        prizeDO.setName("test");
        prizeDO.setImgPath("png");
        prizeDO.setPrice(BigDecimal.valueOf(10));
        prizeDO.setNumber(20);
        prizeDO.setInventory(20);
        prizeDO.setStatus((byte) 1);
        prizeDO.setFreightPrice(BigDecimal.valueOf(0));
        prizeDO.setMerchantStoreId(10L);
        prizeDO.setAdImgPath("png");
        prizeDO.setRate(BigDecimal.valueOf(10));
        prizeDO.setGmtModified(new Date());
        prizeDO.setGmtCreate(new Date());
        prizeDOS.add(prizeDO);
        List<DrawLotteryActivityPrizeBO> prizeBOS = DrawLotteryActivityPrizeConverter.convertBOS(prizeDOS);
        Assert.assertNotNull(prizeBOS);
        Assert.assertEquals(prizeDO.getId(), prizeBOS.get(0).getId());
    }

    @Test
    public void convertDTOS() {
        List<DrawLotteryActivityPrizeBO> prizeBOS = new ArrayList<>();
        DrawLotteryActivityPrizeBO prizeBO = new DrawLotteryActivityPrizeBO();
        prizeBO.setId(100L);
        prizeBO.setDrawLotteryActivityId(10L);
        prizeBO.setName("test");
        prizeBO.setImgPath("png");
        prizeBO.setPrice(BigDecimal.valueOf(10));
        prizeBO.setNumber(20);
        prizeBO.setInventory(20);
        prizeBO.setStatus((byte) 1);
        prizeBO.setFreightPrice(BigDecimal.valueOf(0));
        prizeBO.setMerchantStoreId(10L);
        prizeBO.setAdImgPath("png");
        prizeBO.setRate(BigDecimal.valueOf(10));
        prizeBO.setGmtModified(new Date());
        prizeBO.setGmtCreate(new Date());
        prizeBOS.add(prizeBO);
        List<DrawLotteryActivityPrizeDTO> prizeDTOS = DrawLotteryActivityPrizeConverter.convertDTOS(prizeBOS);
        Assert.assertNotNull(prizeDTOS);
        Assert.assertEquals(prizeBO.getId(), prizeDTOS.get(0).getId());
    }

    @Test
    public void convertDetailDTO() {
        DrawLotteryActivityPrizeBO prizeBO = new DrawLotteryActivityPrizeBO();
        prizeBO.setId(100L);
        prizeBO.setDrawLotteryActivityId(10L);
        prizeBO.setName("test");
        prizeBO.setImgPath("png");
        prizeBO.setPrice(BigDecimal.valueOf(10));
        prizeBO.setNumber(20);
        prizeBO.setInventory(20);
        prizeBO.setStatus((byte) 1);
        prizeBO.setFreightPrice(BigDecimal.valueOf(0));
        prizeBO.setMerchantStoreId(10L);
        prizeBO.setAdImgPath("png");
        prizeBO.setRate(BigDecimal.valueOf(10));
        prizeBO.setGmtModified(new Date());
        prizeBO.setGmtCreate(new Date());
        DrawLotteryActivityPrizeDetailDTO detailDTO = DrawLotteryActivityPrizeConverter.convertDetailDTO(prizeBO);
        Assert.assertNotNull(detailDTO);
        Assert.assertEquals(prizeBO.getId(), detailDTO.getId());
    }

}
