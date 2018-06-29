package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GameMindResultDTO;
import com.lawu.eshop.game.srv.bo.GameMindAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
public class GameMindAttendRecordConverterTest {

    @Test
    public void converterBO(){
        List<GameMindAttendRecordDO> list = new ArrayList<>();
        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setId(1L);
        recordDO.setUserNum("M123456");
        recordDO.setAttendCount(10);
        recordDO.setAttendNum("A123456");
        recordDO.setAttendPoint(1);
        recordDO.setAttendStar(2);
        recordDO.setAttendType(AttendTypeEnum.STANDALONE.getVal());
        recordDO.setGameRank(3);
        recordDO.setGameScore(100);
        recordDO.setGmtCreate(new Date());
        recordDO.setGmtModified(new Date());
        recordDO.setQuestionUseTime(5);
        list.add(recordDO);
        GameMindAttendRecordBO recordBO = GameMindAttendRecordConverter.converterBO(list);
        Assert.assertEquals(recordDO.getId(),recordBO.getId());
        Assert.assertEquals(recordDO.getUserNum(),recordBO.getUserNum());
        Assert.assertEquals(recordDO.getAttendCount(),recordBO.getAttendCount());
        Assert.assertEquals(recordDO.getAttendNum(),recordBO.getAttendNum());
        Assert.assertEquals(recordDO.getAttendPoint(),recordBO.getAttendPoint());
        Assert.assertEquals(recordDO.getAttendStar(),recordBO.getAttendStar());
        Assert.assertEquals(recordDO.getAttendType(),recordBO.getAttendType());
        Assert.assertEquals(recordDO.getGameRank(),recordBO.getGameRank());
        Assert.assertEquals(recordDO.getGameScore(),recordBO.getGameScore());
        Assert.assertEquals(recordDO.getQuestionUseTime(),recordBO.getQuestionUseTime());

    }

    @Test
    public void converterDTO(){
        GameMindResultBO resultBO = new GameMindResultBO();
        resultBO.setAwardPoint(1);
        resultBO.setIsFinish(false);
        resultBO.setIsTrue(false);
        resultBO.setAwardStar(2);
        resultBO.setPoint(3);
        resultBO.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS);
        resultBO.setRightAnswer(2);
        resultBO.setTotalScore(100);
        resultBO.setAttendNum("MG123456");
        GameMindResultDTO resultDTO = GameMindAttendRecordConverter.converterDTO(resultBO);
        Assert.assertEquals(resultBO.getIsFinish(),resultDTO.getIsFinish());
        Assert.assertEquals(resultBO.getAwardPoint(),resultDTO.getAwardPoint());
        Assert.assertEquals(resultBO.getIsTrue(),resultDTO.getIsTrue());
        Assert.assertEquals(resultBO.getAwardStar(),resultDTO.getAwardStar());
        Assert.assertEquals(resultBO.getPoint(),resultDTO.getPoint());
        Assert.assertEquals(resultBO.getStatus(),resultDTO.getStatus());
        Assert.assertEquals(resultBO.getRightAnswer(),resultDTO.getRightAnwser());
        Assert.assertEquals(resultBO.getTotalScore(),resultDTO.getTotalScore());
        Assert.assertEquals(resultBO.getAttendNum(),resultDTO.getAttendNum());
    }
}
