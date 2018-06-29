package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GameAttendSaveReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleConfigDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.game.dto.GamePuzzleResultDTO;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GamePuzzleConfigConverterTest {

    @Test
    public void convertGamePuzzleGetPicBO() {
        List<GamePuzzleGetPicBO> list = new ArrayList<>();
        GamePuzzleGetPicBO picBO = new GamePuzzleGetPicBO();
        picBO.setId(1L);
        picBO.setCoefficient(2);
        picBO.setCutImgNum("I123456");
        picBO.setImgPath("111.png");
        picBO.setKey(1);
        picBO.setLevel(GameHardLevelEnum.COMMONLY);
        picBO.setAttendType(AttendTypeEnum.STANDALONE);
        picBO.setUserNickname("nickName");
        list.add(picBO);
        List<GamePuzzleGetPicDTO> picDTOS = GamePuzzleConfigConverter.convertGamePuzzleGetPicBO(list);
        Assert.assertEquals(picBO.getId(), picDTOS.get(0).getId());
        Assert.assertEquals(picBO.getCoefficient(), picDTOS.get(0).getCoefficient());
        Assert.assertEquals(picBO.getCutImgNum(), picDTOS.get(0).getCutImgNum());
        Assert.assertEquals(picBO.getImgPath(), picDTOS.get(0).getImgPath());
        Assert.assertEquals(picBO.getKey(), picDTOS.get(0).getKey());
        Assert.assertEquals(picBO.getLevel(), picDTOS.get(0).getLevel());
        Assert.assertEquals(picBO.getAttendType(), picDTOS.get(0).getAttendType());
        Assert.assertEquals(picBO.getUserNickname(), picDTOS.get(0).getUserNickname());
    }


    @Test
    public void convertGamePuzzleGetPicBO2() {
        GamePuzzlePicBO gamePuzzlePicBO = new GamePuzzlePicBO();
        GamePuzzleDifficultyBO diffBo = new GamePuzzleDifficultyBO();
        gamePuzzlePicBO.setId(gamePuzzlePicBO.getId());
        diffBo.setCoefficient(diffBo.getCoefficient());
        gamePuzzlePicBO.setImgPath(gamePuzzlePicBO.getImgPath());
        gamePuzzlePicBO.setUserNickname(gamePuzzlePicBO.getUserNickname());
        gamePuzzlePicBO.setGmtCreate(new Date());
        GamePuzzleGetPicBO picBO = GamePuzzleConfigConverter.convertGamePuzzleGetPicBO(gamePuzzlePicBO, diffBo);
        Assert.assertEquals(picBO.getId(), gamePuzzlePicBO.getId());
        Assert.assertEquals(picBO.getCoefficient(), diffBo.getCoefficient());
        Assert.assertEquals(picBO.getImgPath(), gamePuzzlePicBO.getImgPath());
        Assert.assertEquals(picBO.getUserNickname(), gamePuzzlePicBO.getUserNickname());
    }

    @Test
    public void convertInitGamePuzzleAttendRecordBO() {
        GamePuzzleDifficultyDO game = new GamePuzzleDifficultyDO();
        GameAttendSaveParam param = new GameAttendSaveParam();
        String userNum = "M123456";
        String attendNum = "M1234564897";
        param.setAttendType(AttendTypeEnum.STANDALONE);
        game.setType(GameTypeEnum.PUZZLE.getVal());
        GamePuzzleAttendRecordBO recordBO = GamePuzzleConfigConverter.convertInitGamePuzzleAttendRecordBO(game, param, userNum, attendNum);
        Assert.assertEquals(param.getAttendType().getVal(), recordBO.getAttendType());
    }

    @Test
    public void convertGameAttendSaveReturnDTO() {
        GameAttendSaveReturnBO attendBO = new GameAttendSaveReturnBO();
        attendBO.setFlag(false);
        attendBO.setRoomMaster(false);
        attendBO.setAttendNum("A123456");
        GameAttendSaveReturnDTO returnDTO = GamePuzzleConfigConverter.convertGameAttendSaveReturnDTO(attendBO);
        Assert.assertEquals(attendBO.getAttendNum(), returnDTO.getAttendNum());
        Assert.assertEquals(attendBO.isFlag(), returnDTO.isFlag());
        Assert.assertEquals(attendBO.getRoomMaster(), returnDTO.getRoomMaster());
    }

    @Test
    public void convertGamePuzzleResultDTO() {
        GamePuzzleResultBO bo = new GamePuzzleResultBO();
        bo.setFlag(false);
        bo.setGameRank(20);
        bo.setGameScore(30);
        bo.setGameStar(40);
        bo.setOver(false);
        GamePuzzleResultDTO resultDTO = GamePuzzleConfigConverter.convertGamePuzzleResultDTO(bo);
        Assert.assertEquals(bo.isFlag(), resultDTO.isFlag());
        Assert.assertEquals(bo.getGameRank(), resultDTO.getGameRank());
        Assert.assertEquals(bo.getGameScore(), resultDTO.getGameScore());
        Assert.assertEquals(bo.getGameStar(), resultDTO.getGameStar());
        Assert.assertEquals(bo.isOver(), resultDTO.isOver());
    }

    @Test
    public void convertRandomGamePuzzleAttendRecordBO() {
        GameAttendSaveParam param = new GameAttendSaveParam();
        String userNum = "M123456";
        String attendNum = "At124654";
        param.setAttendType(AttendTypeEnum.STANDALONE);
        param.setAttendPoint(20);
        param.setAttendStar(10);
        param.setFree(false);
        GamePuzzleAttendRecordBO recordBO = GamePuzzleConfigConverter.convertRandomGamePuzzleAttendRecordBO(param, userNum, attendNum);
        Assert.assertEquals(recordBO.getStatus(), GameAttendRecordStatusEnum.INITSTATUS.getVal());
        Assert.assertEquals(recordBO.getAttendPoint().intValue(), param.getAttendPoint());
        Assert.assertEquals(recordBO.getAttendStar().intValue(), param.getAttendStar());
        Assert.assertEquals(recordBO.getAttendType(), param.getAttendType().getVal());
    }

    @Test
    public void converterBO() {
        List<GamePuzzleConfigDO> configDOS = new ArrayList<>();
        GamePuzzleConfigDO configDO = new GamePuzzleConfigDO();
        configDO.setId(1L);
        configDO.setAttendMaxPoint(1);
        configDO.setAttendPoint(2);
        configDO.setAwardPoint(3);
        configDO.setAwardStar(4);
        configDO.setCountDown(5);
        configDO.setDeductStar(6);
        configDO.setRoomMaxNum(10);
        configDO.setSecScore("100");
        configDO.setShareAttendCount(7);
        configDO.setStatus(GameConfigStatusEnum.ENABLE.getVal());
        configDO.setFreeCount(2);
        configDO.setPicCount(3);
        configDOS.add(configDO);
        GamePuzzleConfigBO configBO = GamePuzzleConfigConverter.converterBO(configDOS);
        Assert.assertEquals(configDO.getId(), configBO.getId());
        Assert.assertEquals(configDO.getAttendMaxPoint(), configBO.getAttendMaxPoint());
        Assert.assertEquals(configDO.getAttendPoint(), configBO.getAttendPoint());
        Assert.assertEquals(configDO.getAwardPoint(), configBO.getAwardPoint());
        Assert.assertEquals(configDO.getAwardStar(), configBO.getAwardStar());
        Assert.assertEquals(configDO.getCountDown(), configBO.getCountDown());
        Assert.assertEquals(configDO.getDeductStar(), configBO.getDeductStar());
        Assert.assertEquals(configDO.getRoomMaxNum(), configBO.getRoomMaxNum());
        Assert.assertEquals(configDO.getSecScore(), configBO.getSecScore());
        Assert.assertEquals(configDO.getShareAttendCount(), configBO.getShareAttendCount());
        Assert.assertEquals(configDO.getStatus(), configBO.getStatusEnum().getVal());
        Assert.assertEquals(configDO.getPicCount(), configBO.getPicCount());
        Assert.assertEquals(configDO.getFreeCount(), configBO.getFreeCount());

    }

    @Test
    public void converterDTO() {
        GamePuzzleConfigBO configDO = new GamePuzzleConfigBO();
        configDO.setId(1L);
        configDO.setAttendMaxPoint(1);
        configDO.setAttendPoint(2);
        configDO.setAwardPoint(3);
        configDO.setAwardStar(4);
        configDO.setCountDown(5);
        configDO.setDeductStar(6);
        configDO.setRoomMaxNum(10);
        configDO.setSecScore("100");
        configDO.setShareAttendCount(7);
        configDO.setStatusEnum(GameConfigStatusEnum.ENABLE);
        configDO.setFreeCount(2);
        configDO.setPicCount(3);
        GamePuzzleConfigDTO configDTO = GamePuzzleConfigConverter.converterDTO(configDO);
        Assert.assertEquals(configDO.getId(), configDTO.getId());
        Assert.assertEquals(configDO.getAttendMaxPoint(), configDTO.getAttendMaxPoint());
        Assert.assertEquals(configDO.getAttendPoint(), configDTO.getAttendPoint());
        Assert.assertEquals(configDO.getAwardPoint(), configDTO.getAwardPoint());
        Assert.assertEquals(configDO.getAwardStar(), configDTO.getAwardStar());
        Assert.assertEquals(configDO.getCountDown(), configDTO.getCountDown());
        Assert.assertEquals(configDO.getDeductStar(), configDTO.getDeductStar());
        Assert.assertEquals(configDO.getRoomMaxNum(), configDTO.getRoomMaxNum());
        Assert.assertEquals(configDO.getShareAttendCount(), configDTO.getShareAttendCount());
        Assert.assertEquals(configDO.getStatusEnum(), configDTO.getStatusEnum());
        Assert.assertEquals(configDO.getPicCount(), configDTO.getPicCount());
        Assert.assertEquals(configDO.getFreeCount(), configDTO.getFreeCount());
    }

    @Test
    public void convertGamePuzzleGetPicDTO() {
        List<GamePuzzleGetPicBO> picBOS = new ArrayList<>();
        GamePuzzleGetPicBO picBO = new GamePuzzleGetPicBO();
        picBO.setId(1L);
        picBO.setImgPath("IMG");
        picBO.setLevel(GameHardLevelEnum.DIFFICULTY);
        picBO.setAttendType(AttendTypeEnum.STANDALONE);
        picBO.setCoefficient(1);
        picBO.setCanUseTime(10);
        picBO.setKey(2);
        picBO.setCutImgNum("c123456");
        picBO.setUserNickname("name");
        picBO.setScore(20);
        picBOS.add(picBO);
        List<GamePuzzleGetPicDTO> list = GamePuzzleConfigConverter.convertGamePuzzleGetPicDTO(picBOS);
        Assert.assertEquals(picBO.getId(), list.get(0).getId());
        Assert.assertEquals(picBO.getImgPath(), list.get(0).getImgPath());
        Assert.assertEquals(picBO.getLevel(), list.get(0).getLevel());
        Assert.assertEquals(picBO.getAttendType(), list.get(0).getAttendType());
        Assert.assertEquals(picBO.getCoefficient(), list.get(0).getCoefficient());
        Assert.assertEquals(picBO.getCanUseTime(), list.get(0).getCanUseTime());
        Assert.assertEquals(picBO.getKey(), list.get(0).getKey());
        Assert.assertEquals(picBO.getCutImgNum(), list.get(0).getCutImgNum());
        Assert.assertEquals(picBO.getUserNickname(), list.get(0).getUserNickname());
        Assert.assertEquals(picBO.getScore(), list.get(0).getScore());

    }

    @Test
    public void convertGamePuzzleGetPicReturnDTO() {
        List<GamePuzzleGetPicDTO> dto = new ArrayList<>();
        GamePuzzleGetPicDTO picDTO = new GamePuzzleGetPicDTO();
        picDTO.setId(1L);
        dto.add(picDTO);
        GamePuzzleGetPicReturnDTO returnDTO = GamePuzzleConfigConverter.convertGamePuzzleGetPicReturnDTO(dto);
        Assert.assertEquals(picDTO.getId().longValue(), returnDTO.getPuzzleInfo().get(0).getId().longValue());
    }

    @Test
    public void convertGamePuzzleConfigConverter() {
        GamePuzzleRankReturnBO game = new GamePuzzleRankReturnBO();
        game.setGamePoint(new BigDecimal(10));
        game.setGameRank(20);
        game.setGameScore(30);
        game.setGameStar(1);
        game.setTotalStar(2);
        //GamePuzzleRankReturnDTO returnDTO = GamePuzzleConfigConverter.convertGamePuzzleConfigConverter(game);
       // Assert.assertEquals(game.getGameRank(), returnDTO.getGameRank());
        //Assert.assertEquals(game.getGamePoint(), returnDTO.getGamePoint());
       // Assert.assertEquals(game.getGameScore(), returnDTO.getGameScore());
        //Assert.assertEquals(game.getTotalStar(), returnDTO.getTotalStar());
    }
}
