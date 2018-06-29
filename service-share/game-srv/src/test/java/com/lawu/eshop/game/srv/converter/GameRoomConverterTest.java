package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.dto.GameRoomDTO;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.domain.GameRoomDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameRoomConverterTest {

    @Test
    public void converBO() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setId(1L);
        roomDO.setUserNum("M123456");
        roomDO.setAccount("13411111");
        roomDO.setRoomNum("R123456");
        roomDO.setPlayerInfo("player");
        roomDO.setPoint(BigDecimal.TEN);
        roomDO.setPwd("12346");
        roomDO.setType((byte) 1);
        roomDO.setStatus((byte) 1);
        roomDO.setHardLevel((byte) 1);
        roomDO.setGmtModified(new Date());
        roomDO.setGmtCreate(new Date());
        GameRoomBO roomBO = GameRoomConverter.converBO(roomDO);
        Assert.assertEquals(roomBO.getId(), roomDO.getId());
        Assert.assertEquals(roomBO.getUserNum(), roomDO.getUserNum());
        Assert.assertEquals(roomBO.getAccount(), roomDO.getAccount());
        Assert.assertEquals(roomBO.getRoomNum(), roomDO.getRoomNum());
        Assert.assertEquals(roomBO.getPoint(), roomDO.getPoint());
        Assert.assertEquals(roomBO.getPlayerInfo(), roomDO.getPlayerInfo());
        Assert.assertEquals(roomBO.getPwd(), roomDO.getPwd());
        Assert.assertEquals(roomBO.getStatus(), roomDO.getStatus());
        Assert.assertEquals(roomBO.getHardLevel(), roomDO.getHardLevel());
        Assert.assertEquals(roomBO.getGmtCreate(), roomDO.getGmtCreate());
        Assert.assertEquals(roomBO.getGmtModified(), roomDO.getGmtModified());

    }

    @Test
    public void converBOS() {
        List<GameRoomDO> roomDOS = new ArrayList<>();
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setId(1L);
        roomDO.setUserNum("M123456");
        roomDO.setAccount("13411111");
        roomDO.setRoomNum("R123456");
        roomDO.setPlayerInfo("player");
        roomDO.setPoint(BigDecimal.TEN);
        roomDO.setPwd("12346");
        roomDO.setType((byte) 1);
        roomDO.setStatus((byte) 1);
        roomDO.setHardLevel((byte) 1);
        roomDO.setGmtModified(new Date());
        roomDO.setGmtCreate(new Date());
        roomDOS.add(roomDO);
        List<GameRoomBO> roomBOS = GameRoomConverter.converBOS(roomDOS);
        Assert.assertEquals(roomBOS.get(0).getId(), roomDO.getId());
        Assert.assertEquals(roomBOS.get(0).getUserNum(), roomDO.getUserNum());
        Assert.assertEquals(roomBOS.get(0).getAccount(), roomDO.getAccount());
        Assert.assertEquals(roomBOS.get(0).getRoomNum(), roomDO.getRoomNum());
        Assert.assertEquals(roomBOS.get(0).getPoint(), roomDO.getPoint());
        Assert.assertEquals(roomBOS.get(0).getPlayerInfo(), roomDO.getPlayerInfo());
        Assert.assertEquals(roomBOS.get(0).getPwd(), roomDO.getPwd());
        Assert.assertEquals(roomBOS.get(0).getStatus(), roomDO.getStatus());
        Assert.assertEquals(roomBOS.get(0).getHardLevel(), roomDO.getHardLevel());
        Assert.assertEquals(roomBOS.get(0).getGmtCreate(), roomDO.getGmtCreate());
        Assert.assertEquals(roomBOS.get(0).getGmtModified(), roomDO.getGmtModified());
    }

    @Test
    public void converDTO() {
        GameRoomBO roomBO = new GameRoomBO();
        roomBO.setId(roomBO.getId());
        roomBO.setUserNum(roomBO.getUserNum());
        roomBO.setAccount(roomBO.getAccount());
        roomBO.setRoomNum(roomBO.getRoomNum());
        List<GameRoomPlayerInfoDTO> infoDTOS = new ArrayList<>();
        GameRoomPlayerInfoDTO infoDTO = new GameRoomPlayerInfoDTO();
        infoDTO.setUserNum("M12456");
        infoDTO.setHeadImg("image");
        infoDTO.setIsRoomHost(false);
        infoDTO.setNickName("name");
        infoDTO.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        infoDTOS.add(infoDTO);
        roomBO.setPlayerInfo(JSONObject.toJSONString(infoDTOS));
        roomBO.setPoint(roomBO.getPoint());
        roomBO.setPwd("123456");
        roomBO.setType(GameTypeEnum.PUZZLE.getVal());
        roomBO.setStatus(GameRoomStatusEnum.PLAYING.getVal());
        GameRoomDTO roomDTO = GameRoomConverter.converDTO(roomBO);
        Assert.assertEquals(roomBO.getId(), roomDTO.getId());
        Assert.assertEquals(roomBO.getUserNum(), roomDTO.getUserNum());
        Assert.assertEquals(roomBO.getAccount(), roomDTO.getAccount());
        Assert.assertEquals(roomBO.getRoomNum(), roomDTO.getRoomNum());
        Assert.assertEquals(roomBO.getPoint(), roomDTO.getPoint());
        Assert.assertEquals(roomBO.getType(), roomDTO.getTypeEnum().getVal());
        Assert.assertEquals(roomBO.getStatus(), roomDTO.getStatusEnum().getVal());

    }

    @Test
    public void converDTOS() {
        List<GameRoomBO> roomBOS = new ArrayList<>();
        GameRoomBO roomBO = new GameRoomBO();
        roomBO.setId(roomBO.getId());
        roomBO.setUserNum(roomBO.getUserNum());
        roomBO.setAccount(roomBO.getAccount());
        roomBO.setRoomNum(roomBO.getRoomNum());
        List<GameRoomPlayerInfoDTO> infoDTOS = new ArrayList<>();
        GameRoomPlayerInfoDTO infoDTO = new GameRoomPlayerInfoDTO();
        infoDTO.setUserNum("M12456");
        infoDTO.setHeadImg("image");
        infoDTO.setIsRoomHost(false);
        infoDTO.setNickName("name");
        infoDTO.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        infoDTOS.add(infoDTO);
        roomBO.setPlayerInfo(JSONObject.toJSONString(infoDTOS));
        roomBO.setPoint(roomBO.getPoint());
        roomBO.setPwd("123456");
        roomBO.setType(GameTypeEnum.PUZZLE.getVal());
        roomBO.setStatus(GameRoomStatusEnum.PLAYING.getVal());

        roomBOS.add(roomBO);
        List<GameRoomDTO> roomDTOS = GameRoomConverter.converDTOS(roomBOS);
        Assert.assertEquals(roomBO.getId(), roomDTOS.get(0).getId());
        Assert.assertEquals(roomBO.getUserNum(), roomDTOS.get(0).getUserNum());
        Assert.assertEquals(roomBO.getAccount(), roomDTOS.get(0).getAccount());
        Assert.assertEquals(roomBO.getRoomNum(), roomDTOS.get(0).getRoomNum());
        Assert.assertEquals(roomBO.getPoint(), roomDTOS.get(0).getPoint());
        Assert.assertEquals(roomBO.getType(), roomDTOS.get(0).getTypeEnum().getVal());
        Assert.assertEquals(roomBO.getStatus(), roomDTOS.get(0).getStatusEnum().getVal());

    }
}
