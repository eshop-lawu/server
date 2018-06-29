package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GamePuzzleAttendRecordConverterTest {

    @Test
    public void convertGamePuzzleAttendRecordDO() {
        GamePuzzleAttendRecordBO attendBO = new GamePuzzleAttendRecordBO();
        attendBO.setUserNum("U123456");
        attendBO.setAttendNum("t1234561");
        attendBO.setAttendType(AttendTypeEnum.STANDALONE.getVal());
        attendBO.setRoomNum("R123456");
        attendBO.setAttendCount(10);
        attendBO.setDifficulty(GameHardLevelEnum.DIFFICULTY.getVal());
        attendBO.setAttendPoint(20);
        attendBO.setAttendStar(10);
        attendBO.setPuzzlePicId(1L);
        attendBO.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
        attendBO.setGameScore(10);
        attendBO.setGameRank(200);
        attendBO.setGameUseTime(10);
        attendBO.setRewardPoint(new BigDecimal(8));
        attendBO.setRewardStar(10);
        attendBO.setGmtModified(new Date());
        attendBO.setGmtCreate(new Date());
        GamePuzzleAttendRecordDO recordDO = GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(attendBO);
        Assert.assertEquals(attendBO.getUserNum(), recordDO.getUserNum());
        Assert.assertEquals(attendBO.getAttendNum(), recordDO.getAttendNum());
        Assert.assertEquals(attendBO.getAttendType(), recordDO.getAttendType());
        Assert.assertEquals(attendBO.getRoomNum(), recordDO.getRoomNum());
        Assert.assertEquals(attendBO.getAttendCount(), recordDO.getAttendCount());
        Assert.assertEquals(attendBO.getDifficulty(), recordDO.getDifficulty());
        Assert.assertEquals(attendBO.getAttendStar(), recordDO.getAttendStar());
        Assert.assertEquals(attendBO.getAttendPoint(), recordDO.getAttendPoint());
        Assert.assertEquals(attendBO.getPuzzlePicId(), recordDO.getPuzzlePicId());
        Assert.assertEquals(attendBO.getStatus(), recordDO.getStatus());
        Assert.assertEquals(attendBO.getGameScore(), recordDO.getGameScore());
        Assert.assertEquals(attendBO.getGameRank(), recordDO.getGameRank());
        Assert.assertEquals(attendBO.getGameUseTime(), recordDO.getGameUseTime());
        Assert.assertEquals(attendBO.getRewardPoint(), recordDO.getRewardPoint());
        Assert.assertEquals(attendBO.getRewardStar(), recordDO.getRewardStar());
        Assert.assertEquals(attendBO.getGmtModified(), recordDO.getGmtModified());
        Assert.assertEquals(attendBO.getGmtCreate(), recordDO.getGmtCreate());
    }

    @Test
    public void convertGamePuzzleAttendRecordDO2(){
        GamePuzzleAttendRecordDO recordDO = new GamePuzzleAttendRecordDO();
        recordDO.setUserNum("U123456");
        recordDO.setAttendNum("t1234561");
        recordDO.setAttendType(AttendTypeEnum.STANDALONE.getVal());
        recordDO.setRoomNum("R123456");
        recordDO.setAttendCount(10);
        recordDO.setDifficulty(GameHardLevelEnum.DIFFICULTY.getVal());
        recordDO.setAttendPoint(20);
        recordDO.setAttendStar(10);
        recordDO.setPuzzlePicId(1L);
        recordDO.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
        recordDO.setGameScore(10);
        recordDO.setGameRank(200);
        recordDO.setGameUseTime(10);
        recordDO.setRewardPoint(new BigDecimal(8));
        recordDO.setRewardStar(10);
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        GamePuzzleAttendRecordBO recordBO = GamePuzzleAttendRecordConverter.convertGamePuzzleAttendRecordDO(recordDO);
        Assert.assertEquals(recordBO.getUserNum(), recordDO.getUserNum());
        Assert.assertEquals(recordBO.getAttendNum(), recordDO.getAttendNum());
        Assert.assertEquals(recordBO.getAttendType(), recordDO.getAttendType());
        Assert.assertEquals(recordBO.getRoomNum(), recordDO.getRoomNum());
        Assert.assertEquals(recordBO.getAttendCount(), recordDO.getAttendCount());
        Assert.assertEquals(recordBO.getDifficulty(), recordDO.getDifficulty());
        Assert.assertEquals(recordBO.getAttendStar(), recordDO.getAttendStar());
        Assert.assertEquals(recordBO.getAttendPoint(), recordDO.getAttendPoint());
        Assert.assertEquals(recordBO.getPuzzlePicId(), recordDO.getPuzzlePicId());
        Assert.assertEquals(recordBO.getStatus(), recordDO.getStatus());
        Assert.assertEquals(recordBO.getGameScore(), recordDO.getGameScore());
        Assert.assertEquals(recordBO.getGameRank(), recordDO.getGameRank());
        Assert.assertEquals(recordBO.getGameUseTime(), recordDO.getGameUseTime());
        Assert.assertEquals(recordBO.getRewardPoint(), recordDO.getRewardPoint());
        Assert.assertEquals(recordBO.getRewardStar(), recordDO.getRewardStar());
        Assert.assertEquals(recordBO.getGmtModified(), recordDO.getGmtModified());
        Assert.assertEquals(recordBO.getGmtCreate(), recordDO.getGmtCreate());
    }

    @Test
    public void convertGamePuzzleRankReturnBO(){
        List<GamePuzzleAttendRecordDO> list = new ArrayList<>();
        GamePuzzleAttendRecordDO recordDO = new GamePuzzleAttendRecordDO();
        recordDO.setRewardStar(20);
        recordDO.setAttendStar(10);
        recordDO.setRewardPoint(new BigDecimal(10));
        recordDO.setAttendPoint(10);
        recordDO.setGameRank(20);
        recordDO.setGameScore(30);
        list.add(recordDO);
        //GamePuzzleRankReturnBO rankReturnBO = GamePuzzleAttendRecordConverter.convertGamePuzzleRankReturnBO(list);
        //Assert.assertEquals(recordDO.getGameScore().intValue(),rankReturnBO.getGameScore());
        //Assert.assertEquals(rankReturnBO.getGameRank(),recordDO.getGameRank().intValue());
    }
}
