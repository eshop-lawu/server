package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordResultDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordResultDOMapper;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordResultService;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindAttendRecordResultServiceImplTest {

    @Autowired
    private GameMindAttendRecordResultService gameMindAttendRecordResultService;

    @Autowired
    private GameMindAttendRecordResultDOMapper gameMindAttendRecordResultDOMapper;

    @Test
    @Transactional
    @Rollback
    public void addAttendRecordsResult(){
        List<GameMindAttendRecordResultParam> list = new ArrayList<>();
        GameMindAttendRecordResultParam param = new GameMindAttendRecordResultParam();
        param.setGmtModified(new Date());
        param.setAnswer("test");
        param.setFlag(true);
        param.setGmtCreate(new Date());
        param.setMindAttendId(1L);
        param.setPoint(10);
        param.setQuestionId(2L);
        param.setRightAnswer(1);
        param.setUseTime(5);
        list.add(param);
        gameMindAttendRecordResultService.addAttendRecordsResult(list);

        List<GameMindAttendRecordResultDO> recordResultDOS = gameMindAttendRecordResultDOMapper.selectByExample(null);
        Assert.assertEquals(1,recordResultDOS.size());
        Assert.assertEquals(param.getAnswer(),recordResultDOS.get(0).getAnswer());
        Assert.assertEquals(param.getFlag(),recordResultDOS.get(0).getFlag());
        Assert.assertEquals(param.getMindAttendId(),recordResultDOS.get(0).getMindAttendId());
        Assert.assertEquals(param.getPoint(),recordResultDOS.get(0).getPoint());
        Assert.assertEquals(param.getQuestionId(),recordResultDOS.get(0).getQuestionId());
        Assert.assertEquals(param.getRightAnswer(),recordResultDOS.get(0).getRightAnswer());
        Assert.assertEquals(param.getUseTime(),recordResultDOS.get(0).getUseTime());

    }

}
