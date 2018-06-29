package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialRecordDTO;
import com.lawu.eshop.game.dto.GameDialRecordInfoDTO;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameDialRecordConverterTest {

    @Test
    public void converBO() {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setId(1L);
        recordDO.setUserId(2L);
        recordDO.setUserNum("M123456");
        recordDO.setUserAccount("1313111");
        recordDO.setGameDialId(3L);
        recordDO.setGameDialPrizeId(4L);
        recordDO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        recordDO.setPayPoint(BigDecimal.ONE);
        recordDO.setConsigneeName("TEST");
        recordDO.setConsigneeMobile("13131315155");
        recordDO.setConsigneeAddress("address");
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        GameDialRecordBO recordBO = GameDialRecordConverter.converBO(recordDO);
        Assert.assertEquals(recordDO.getId(), recordBO.getId());
        Assert.assertEquals(recordDO.getUserId(), recordBO.getUserId());
        Assert.assertEquals(recordDO.getUserNum(), recordBO.getUserNum());
        Assert.assertEquals(recordDO.getUserAccount(), recordBO.getUserAccount());
        Assert.assertEquals(recordDO.getGameDialId(), recordBO.getGameDialId());
        Assert.assertEquals(recordDO.getGameDialPrizeId(), recordBO.getGameDialPrizeId());
        Assert.assertEquals(recordDO.getStatus(), recordBO.getStatus());
        Assert.assertEquals(recordDO.getPayPoint(), recordBO.getPayPoint());
        Assert.assertEquals(recordDO.getConsigneeAddress(), recordBO.getConsigneeAddress());
        Assert.assertEquals(recordDO.getConsigneeName(), recordBO.getConsigneeName());
        Assert.assertEquals(recordDO.getConsigneeMobile(), recordBO.getConsigneeMobile());
        Assert.assertEquals(recordDO.getGmtModified(), recordBO.getGmtModified());
        Assert.assertEquals(recordDO.getGmtCreate(), recordBO.getGmtCreate());

    }

    @Test
    public void converBOS() {
        List<GameDialRecordDO> recordDOS = new ArrayList<>();
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setId(1L);
        recordDO.setUserId(2L);
        recordDO.setUserNum("M123456");
        recordDO.setUserAccount("1313111");
        recordDO.setGameDialId(3L);
        recordDO.setGameDialPrizeId(4L);
        recordDO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        recordDO.setPayPoint(BigDecimal.ONE);
        recordDO.setConsigneeName("TEST");
        recordDO.setConsigneeMobile("13131315155");
        recordDO.setConsigneeAddress("address");
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDOS.add(recordDO);
        List<GameDialRecordBO> recordBOS = GameDialRecordConverter.converBOS(recordDOS);
        Assert.assertEquals(recordDO.getId(), recordBOS.get(0).getId());
        Assert.assertEquals(recordDO.getUserId(), recordBOS.get(0).getUserId());
        Assert.assertEquals(recordDO.getUserNum(), recordBOS.get(0).getUserNum());
        Assert.assertEquals(recordDO.getUserAccount(), recordBOS.get(0).getUserAccount());
        Assert.assertEquals(recordDO.getGameDialId(), recordBOS.get(0).getGameDialId());
        Assert.assertEquals(recordDO.getGameDialPrizeId(), recordBOS.get(0).getGameDialPrizeId());
        Assert.assertEquals(recordDO.getStatus(), recordBOS.get(0).getStatus());
        Assert.assertEquals(recordDO.getPayPoint(), recordBOS.get(0).getPayPoint());
        Assert.assertEquals(recordDO.getConsigneeAddress(), recordBOS.get(0).getConsigneeAddress());
        Assert.assertEquals(recordDO.getConsigneeName(), recordBOS.get(0).getConsigneeName());
        Assert.assertEquals(recordDO.getConsigneeMobile(), recordBOS.get(0).getConsigneeMobile());
        Assert.assertEquals(recordDO.getGmtModified(), recordBOS.get(0).getGmtModified());
        Assert.assertEquals(recordDO.getGmtCreate(), recordBOS.get(0).getGmtCreate());
    }

    @Test
    public void converInfoDTOS() {
        List<GameDialRecordInfoBO> InfoBOS = new ArrayList<>();
        GameDialRecordInfoBO infoBO = new GameDialRecordInfoBO();
        infoBO.setName("test");
        infoBO.setImgPath("image");
        infoBO.setId(1L);
        infoBO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        infoBO.setGmtCreate(new Date());
        InfoBOS.add(infoBO);
        List<GameDialRecordInfoDTO> list = GameDialRecordConverter.converInfoDTOS(InfoBOS);
        Assert.assertEquals(infoBO.getName(), list.get(0).getName());
        Assert.assertEquals(infoBO.getId(), list.get(0).getId());
        Assert.assertEquals(infoBO.getImgPath(), list.get(0).getImgPath());
        Assert.assertEquals(infoBO.getStatus(), list.get(0).getStatusEnum().getVal());
        Assert.assertEquals(infoBO.getGmtCreate(), list.get(0).getGmtCreate());
    }

    @Test
    public void converDTO() {
        GameDialRecordBO recordBO = new GameDialRecordBO();
        recordBO.setId(1L);
        recordBO.setUserId(2L);
        recordBO.setUserNum("M123456");
        recordBO.setUserAccount("1313131313");
        recordBO.setGameDialPrizeId(2L);
        recordBO.setPayPoint(BigDecimal.ONE);
        recordBO.setConsigneeName("name");
        recordBO.setConsigneeMobile("1313154485");
        recordBO.setConsigneeAddress("address");
        recordBO.setGmtCreate(new Date());
        recordBO.setPrizeName("prize");
        recordBO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        GameDialRecordDTO recordDTO = GameDialRecordConverter.converDTO(recordBO);
        Assert.assertEquals(recordBO.getId(), recordDTO.getId());
        Assert.assertEquals(recordBO.getUserId(), recordDTO.getUserId());
        Assert.assertEquals(recordBO.getUserNum(), recordDTO.getUserNum());
        Assert.assertEquals(recordBO.getUserAccount(), recordDTO.getUserAccount());
        Assert.assertEquals(recordBO.getGameDialPrizeId(), recordDTO.getGameDialPrizeId());
        Assert.assertEquals(recordBO.getPayPoint(), recordDTO.getPayPoint());
        Assert.assertEquals(recordBO.getConsigneeName(), recordDTO.getConsigneeName());
        Assert.assertEquals(recordBO.getConsigneeMobile(), recordDTO.getConsigneeMobile());
        Assert.assertEquals(recordBO.getConsigneeAddress(), recordDTO.getConsigneeAddress());
        Assert.assertEquals(recordBO.getGmtCreate(), recordDTO.getGmtCreate());
        Assert.assertEquals(recordBO.getPrizeName(), recordDTO.getPrizeName());
        Assert.assertEquals(recordBO.getStatus(), recordDTO.getStatus().getVal());
    }

    @Test
    public void converDTOS() {
        List<GameDialRecordBO> records = new ArrayList<>();
        GameDialRecordBO recordBO = new GameDialRecordBO();
        recordBO.setId(1L);
        recordBO.setUserId(2L);
        recordBO.setUserNum("M123456");
        recordBO.setUserAccount("1313131313");
        recordBO.setGameDialPrizeId(2L);
        recordBO.setPayPoint(BigDecimal.ONE);
        recordBO.setConsigneeName("name");
        recordBO.setConsigneeMobile("1313154485");
        recordBO.setConsigneeAddress("address");
        recordBO.setGmtCreate(new Date());
        recordBO.setPrizeName("prize");
        recordBO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        records.add(recordBO);
        List<GameDialRecordDTO> recordDTOS = GameDialRecordConverter.converDTOS(records);
        Assert.assertEquals(recordBO.getId(), recordDTOS.get(0).getId());
        Assert.assertEquals(recordBO.getUserId(), recordDTOS.get(0).getUserId());
        Assert.assertEquals(recordBO.getUserNum(), recordDTOS.get(0).getUserNum());
        Assert.assertEquals(recordBO.getUserAccount(), recordDTOS.get(0).getUserAccount());
        Assert.assertEquals(recordBO.getGameDialPrizeId(), recordDTOS.get(0).getGameDialPrizeId());
        Assert.assertEquals(recordBO.getPayPoint(), recordDTOS.get(0).getPayPoint());
        Assert.assertEquals(recordBO.getConsigneeName(), recordDTOS.get(0).getConsigneeName());
        Assert.assertEquals(recordBO.getConsigneeMobile(), recordDTOS.get(0).getConsigneeMobile());
        Assert.assertEquals(recordBO.getConsigneeAddress(), recordDTOS.get(0).getConsigneeAddress());
        Assert.assertEquals(recordBO.getGmtCreate(), recordDTOS.get(0).getGmtCreate());
        Assert.assertEquals(recordBO.getPrizeName(), recordDTOS.get(0).getPrizeName());
        Assert.assertEquals(recordBO.getStatus(), recordDTOS.get(0).getStatus().getVal());
    }
}
