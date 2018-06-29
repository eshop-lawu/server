package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;
import com.lawu.eshop.game.dto.GameDialPrizeAttendDTO;
import com.lawu.eshop.game.dto.GameDialPrizeDetailDTO;
import com.lawu.eshop.game.dto.GameDialPrizeInfoDTO;
import com.lawu.eshop.game.dto.GameDialPrizeLotteryDTO;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameDialPrizeConverterTest {

    @Test
    public void converBO() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setId(1L);
        prizeDO.setGameDialId(2L);
        prizeDO.setName("test");
        prizeDO.setImgPath("ii.png");
        prizeDO.setPrice(BigDecimal.TEN);
        prizeDO.setNumber(1);
        prizeDO.setInventory(2);
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setIsAddress(false);
        prizeDO.setIsSendPrize(false);
        prizeDO.setPrizeType(GameDialPrizeTypeEnum.POINT.getVal());
        prizeDO.setFreightPrice(BigDecimal.ONE);
        prizeDO.setRate(BigDecimal.TEN);
        prizeDO.setGmtModified(new Date());
        prizeDO.setGmtCreate(new Date());
        GameDialPrizeBO prizeBO = GameDialPrizeConverter.converBO(prizeDO);
        Assert.assertEquals(prizeDO.getId(), prizeBO.getId());
        Assert.assertEquals(prizeDO.getGameDialId(), prizeBO.getGameDialId());
        Assert.assertEquals(prizeDO.getName(), prizeBO.getName());
        Assert.assertEquals(prizeDO.getImgPath(), prizeBO.getImgPath());
        Assert.assertEquals(prizeDO.getPrice(), prizeBO.getPrice());
        Assert.assertEquals(prizeDO.getNumber(), prizeBO.getNumber());
        Assert.assertEquals(prizeDO.getInventory(), prizeBO.getInventory());
        Assert.assertEquals(prizeDO.getStatus(), prizeBO.getStatus());
        Assert.assertEquals(prizeDO.getIsAddress(), prizeBO.getIsAddress());
        Assert.assertEquals(prizeDO.getIsSendPrize(), prizeBO.getIsSendPrize());
        Assert.assertEquals(prizeDO.getPrizeType(), prizeBO.getPrizeType());
        Assert.assertEquals(prizeDO.getFreightPrice(), prizeBO.getFreightPrice());
        Assert.assertEquals(prizeDO.getRate(), prizeBO.getRate());
        Assert.assertEquals(prizeDO.getGmtCreate(), prizeBO.getGmtCreate());
        Assert.assertEquals(prizeDO.getGmtModified(), prizeBO.getGmtModified());
    }

    @Test
    public void converBOS() {
        List<GameDialPrizeDO> prizeDOS = new ArrayList<>();
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setId(1L);
        prizeDO.setGameDialId(2L);
        prizeDO.setName("test");
        prizeDO.setImgPath("ii.png");
        prizeDO.setPrice(BigDecimal.TEN);
        prizeDO.setNumber(1);
        prizeDO.setInventory(2);
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setIsAddress(false);
        prizeDO.setIsSendPrize(false);
        prizeDO.setPrizeType(GameDialPrizeTypeEnum.POINT.getVal());
        prizeDO.setFreightPrice(BigDecimal.ONE);
        prizeDO.setRate(BigDecimal.TEN);
        prizeDO.setGmtModified(new Date());
        prizeDO.setGmtCreate(new Date());
        prizeDOS.add(prizeDO);
        List<GameDialPrizeBO> prizeBOS = GameDialPrizeConverter.converBOS(prizeDOS);
        Assert.assertEquals(prizeDO.getId(), prizeBOS.get(0).getId());
        Assert.assertEquals(prizeDO.getGameDialId(), prizeBOS.get(0).getGameDialId());
        Assert.assertEquals(prizeDO.getName(), prizeBOS.get(0).getName());
        Assert.assertEquals(prizeDO.getImgPath(), prizeBOS.get(0).getImgPath());
        Assert.assertEquals(prizeDO.getPrice(), prizeBOS.get(0).getPrice());
        Assert.assertEquals(prizeDO.getNumber(), prizeBOS.get(0).getNumber());
        Assert.assertEquals(prizeDO.getInventory(), prizeBOS.get(0).getInventory());
        Assert.assertEquals(prizeDO.getStatus(), prizeBOS.get(0).getStatus());
        Assert.assertEquals(prizeDO.getIsAddress(), prizeBOS.get(0).getIsAddress());
        Assert.assertEquals(prizeDO.getIsSendPrize(), prizeBOS.get(0).getIsSendPrize());
        Assert.assertEquals(prizeDO.getPrizeType(), prizeBOS.get(0).getPrizeType());
        Assert.assertEquals(prizeDO.getFreightPrice(), prizeBOS.get(0).getFreightPrice());
        Assert.assertEquals(prizeDO.getRate(), prizeBOS.get(0).getRate());
        Assert.assertEquals(prizeDO.getGmtCreate(), prizeBOS.get(0).getGmtCreate());
        Assert.assertEquals(prizeDO.getGmtModified(), prizeBOS.get(0).getGmtModified());
    }

    @Test
    public void converPrizeInfoDTOS() {
        List<GameDialPrizeBO> prizeBOS = new ArrayList<>();
        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setName("test");
        prizeBO.setImgPath("11.png");
        prizeBOS.add(prizeBO);
        List<GameDialPrizeInfoDTO> infoDTOS = GameDialPrizeConverter.converPrizeInfoDTOS(prizeBOS);
        Assert.assertEquals(prizeBO.getName(), infoDTOS.get(0).getName());
        Assert.assertEquals(prizeBO.getImgPath(), infoDTOS.get(0).getImgPath());
    }

    @Test
    public void convertGameDialPrizeDTOS() {
        List<GameDialPrizeBO> list = new ArrayList<>();
        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setId(1L);
        prizeBO.setGameDialId(2L);
        prizeBO.setName("test");
        prizeBO.setImgPath("ii.png");
        prizeBO.setPrice(BigDecimal.TEN);
        prizeBO.setNumber(1);
        prizeBO.setInventory(2);
        prizeBO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeBO.setIsAddress(false);
        prizeBO.setIsSendPrize(false);
        prizeBO.setPrizeType(GameDialPrizeTypeEnum.POINT.getVal());
        prizeBO.setFreightPrice(BigDecimal.ONE);
        prizeBO.setRate(BigDecimal.TEN);
        prizeBO.setGmtModified(new Date());
        prizeBO.setGmtCreate(new Date());
        list.add(prizeBO);
        List<GameDialPrizeAttendDTO> attendDTOS = GameDialPrizeConverter.convertGameDialPrizeDTOS(list);
        Assert.assertEquals(prizeBO.getId(), attendDTOS.get(0).getId());
        Assert.assertEquals(prizeBO.getGameDialId(), attendDTOS.get(0).getGameDialId());
        Assert.assertEquals(prizeBO.getName(), attendDTOS.get(0).getName());
        Assert.assertEquals(prizeBO.getImgPath(), attendDTOS.get(0).getImgPath());
        Assert.assertEquals(prizeBO.getPrice(), attendDTOS.get(0).getPrice());
        Assert.assertEquals(prizeBO.getNumber(), attendDTOS.get(0).getNumber());
        Assert.assertEquals(prizeBO.getInventory(), attendDTOS.get(0).getInventory());
        Assert.assertEquals(prizeBO.getStatus(), attendDTOS.get(0).getStatus().getVal());
        Assert.assertEquals(prizeBO.getIsAddress(), attendDTOS.get(0).getIsAddress());
        Assert.assertEquals(prizeBO.getIsSendPrize(), attendDTOS.get(0).getIsSendPrize());
        Assert.assertEquals(prizeBO.getPrizeType(), attendDTOS.get(0).getPrizeType().getVal());
        Assert.assertEquals(prizeBO.getFreightPrice(), attendDTOS.get(0).getFreightPrice());
        Assert.assertEquals(prizeBO.getRate(), attendDTOS.get(0).getRate());
        Assert.assertEquals(prizeBO.getGmtCreate(), attendDTOS.get(0).getGmtCreate());
        Assert.assertEquals(prizeBO.getGmtModified(), attendDTOS.get(0).getGmtModified());
    }

    @Test
    public void converDTO() {
        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setId(1L);
        prizeBO.setGameDialId(2L);
        prizeBO.setName("test");
        prizeBO.setImgPath("ii.png");
        prizeBO.setPrice(BigDecimal.TEN);
        prizeBO.setNumber(1);
        prizeBO.setInventory(2);
        prizeBO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeBO.setIsAddress(false);
        prizeBO.setIsSendPrize(false);
        prizeBO.setPrizeType(GameDialPrizeTypeEnum.POINT.getVal());
        prizeBO.setFreightPrice(BigDecimal.ONE);
        prizeBO.setRate(BigDecimal.TEN);
        prizeBO.setGmtModified(new Date());
        prizeBO.setGmtCreate(new Date());
        GameDialPrizeAttendDTO attendDTO = GameDialPrizeConverter.converDTO(prizeBO);
        Assert.assertEquals(prizeBO.getId(), attendDTO.getId());
        Assert.assertEquals(prizeBO.getGameDialId(), attendDTO.getGameDialId());
        Assert.assertEquals(prizeBO.getName(), attendDTO.getName());
        Assert.assertEquals(prizeBO.getImgPath(), attendDTO.getImgPath());
        Assert.assertEquals(prizeBO.getPrice(), attendDTO.getPrice());
        Assert.assertEquals(prizeBO.getNumber(), attendDTO.getNumber());
        Assert.assertEquals(prizeBO.getInventory(), attendDTO.getInventory());
        Assert.assertEquals(prizeBO.getStatus(), attendDTO.getStatus().getVal());
        Assert.assertEquals(prizeBO.getIsAddress(), attendDTO.getIsAddress());
        Assert.assertEquals(prizeBO.getIsSendPrize(), attendDTO.getIsSendPrize());
        Assert.assertEquals(prizeBO.getPrizeType(), attendDTO.getPrizeType().getVal());
        Assert.assertEquals(prizeBO.getFreightPrice(), attendDTO.getFreightPrice());
        Assert.assertEquals(prizeBO.getRate(), attendDTO.getRate());
        Assert.assertEquals(prizeBO.getGmtCreate(), attendDTO.getGmtCreate());
        Assert.assertEquals(prizeBO.getGmtModified(), attendDTO.getGmtModified());
    }

    @Test
    public void converPrizeLotteryDTOS() {
        List<GameDialPrizeBO> prizeBOS = new ArrayList<>();
        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setId(1L);
        prizeBO.setRate(BigDecimal.TEN);
        prizeBOS.add(prizeBO);
        List<GameDialPrizeLotteryDTO> lotteryDTOS = GameDialPrizeConverter.converPrizeLotteryDTOS(prizeBOS);
        Assert.assertEquals(prizeBO.getId(), lotteryDTOS.get(0).getId());
        Assert.assertEquals(prizeBO.getRate(), lotteryDTOS.get(0).getRate());
    }

    @Test
    public void converLotteryPrizeDTO() {
        GameDialPrizeBO prizeBO = new GameDialPrizeBO();
        prizeBO.setName("test");
        prizeBO.setImgPath("13213.png");
        prizeBO.setIsAddress(false);
        prizeBO.setIsSendPrize(false);
        prizeBO.setFreightPrice(BigDecimal.ONE);
        GameDialPrizeDetailDTO dialPrizeDetailDTO = GameDialPrizeConverter.converLotteryPrizeDTO(prizeBO);
        Assert.assertEquals(prizeBO.getName(), dialPrizeDetailDTO.getName());
        Assert.assertEquals(prizeBO.getImgPath(), dialPrizeDetailDTO.getImgPath());
        Assert.assertEquals(prizeBO.getIsSendPrize(), dialPrizeDetailDTO.getIsSendPrize());
        Assert.assertEquals(prizeBO.getIsAddress(), dialPrizeDetailDTO.getIsAddress());
        Assert.assertEquals(prizeBO.getFreightPrice(), dialPrizeDetailDTO.getFreightPrice());

    }
}
