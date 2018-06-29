package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.eshop.game.srv.bo.GameMindConfigBO;
import com.lawu.eshop.game.srv.domain.GameMindConfigDO;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
public class GameMindConfigConverterTest {

    @Test
    public void converterBO() {
        List<GameMindConfigDO> configDOS = new ArrayList<>();
        GameMindConfigDO configDO = new GameMindConfigDO();
        configDO.setId(1L);
        configDO.setAttendMaxPoint(10);
        configDO.setAttendPoint(5);
        configDO.setAwardPoint(6);
        configDO.setAwardStar(1);
        configDO.setCountDown(10);
        configDO.setDeductStar(5);
        configDO.setLastScoreMultiple(2);
        configDO.setQuestionCount(5);
        configDO.setRoomMaxNum(3);
        configDO.setSecScore("100");
        configDO.setShareAttendCount(1);
        configDO.setStatus(GameConfigStatusEnum.ENABLE.getVal());
        configDO.setSuccessScore(200);
        configDO.setFreeCount(2);
        configDOS.add(configDO);
        GameMindConfigBO configBO = GameMindConfigConverter.converterBO(configDOS);
        Assert.assertEquals(configDO.getId(), configBO.getId());
        Assert.assertEquals(configDO.getAttendPoint(), configBO.getAttendPoint());
        Assert.assertEquals(configDO.getAttendMaxPoint(), configBO.getAttendMaxPoint());
        Assert.assertEquals(configDO.getAwardPoint(), configBO.getAwardPoint());
        Assert.assertEquals(configDO.getAwardStar(), configBO.getAwardStar());
        Assert.assertEquals(configDO.getCountDown(), configBO.getCountDown());
        Assert.assertEquals(configDO.getDeductStar(), configBO.getDeductStar());
        Assert.assertEquals(configDO.getLastScoreMultiple(), configBO.getLastScoreMultiple());
        Assert.assertEquals(configDO.getQuestionCount(), configBO.getQuestionCount());
        Assert.assertEquals(configDO.getRoomMaxNum(), configBO.getRoomMaxNum());
        Assert.assertEquals(configDO.getSecScore(), configBO.getSecScore());
        Assert.assertEquals(configDO.getShareAttendCount(), configBO.getShareAttendCount());
        Assert.assertEquals(configDO.getStatus(), configBO.getStatusEnum().getVal());
        Assert.assertEquals(configDO.getFreeCount(), configBO.getFreeCount());
        Assert.assertEquals(configDO.getSuccessScore(), configBO.getSuccessScore());
    }

    @Test
    public void converterDTO() {
        GameMindConfigBO configBO = new GameMindConfigBO();
        configBO.setId(1L);
        configBO.setAttendMaxPoint(10);
        configBO.setAttendPoint(5);
        configBO.setAwardPoint(6);
        configBO.setAwardStar(1);
        configBO.setCountDown(10);
        configBO.setDeductStar(5);
        configBO.setLastScoreMultiple(2);
        configBO.setQuestionCount(5);
        configBO.setRoomMaxNum(3);
        configBO.setSecScore("[{\"point\":90,\"second\":1},{\"point\":80,\"second\":2},{\"point\":70,\"second\":3},{\"point\":60,\"second\":4},{\"point\":50,\"second\":5},{\"point\":40,\"second\":6},{\"point\":30,\"second\":7},{\"point\":20,\"second\":8},{\"point\":10,\"second\":9},{\"point\":0,\"second\":10}]");
        configBO.setShareAttendCount(1);
        configBO.setStatusEnum(GameConfigStatusEnum.ENABLE);
        configBO.setSuccessScore(200);
        configBO.setFreeCount(2);
        GameMindConfigDTO configDTO = GameMindConfigConverter.converterDTO(configBO);
        Assert.assertEquals(configDTO.getId(), configBO.getId());
        Assert.assertEquals(configDTO.getAttendPoint(), configBO.getAttendPoint());
        Assert.assertEquals(configDTO.getAttendMaxPoint(), configBO.getAttendMaxPoint());
        Assert.assertEquals(configDTO.getAwardPoint(), configBO.getAwardPoint());
        Assert.assertEquals(configDTO.getAwardStar(), configBO.getAwardStar());
        Assert.assertEquals(configDTO.getCountDown(), configBO.getCountDown());
        Assert.assertEquals(configDTO.getDeductStar(), configBO.getDeductStar());
        Assert.assertEquals(configDTO.getLastScoreMultiple(), configBO.getLastScoreMultiple());
        Assert.assertEquals(configDTO.getQuestionCount(), configBO.getQuestionCount());
        Assert.assertEquals(configDTO.getRoomMaxNum(), configBO.getRoomMaxNum());
        Assert.assertEquals(configDTO.getShareAttendCount(), configBO.getShareAttendCount());
        Assert.assertEquals(configDTO.getStatusEnum(), configBO.getStatusEnum());
        Assert.assertEquals(configDTO.getFreeCount(), configBO.getFreeCount());
        Assert.assertEquals(configDTO.getSuccessScore(), configBO.getSuccessScore());

    }
}
