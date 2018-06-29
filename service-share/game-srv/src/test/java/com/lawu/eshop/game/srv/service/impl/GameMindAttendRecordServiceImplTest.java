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

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.constants.GameQuestionStatusEnum;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;
import com.lawu.eshop.game.srv.domain.GameAccountDO;
import com.lawu.eshop.game.srv.domain.GameMindAccountDO;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.mapper.GameAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordService;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindAttendRecordServiceImplTest {

    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;

    @Autowired
    private GameMindAccountDOMapper gameMindAccountDOMapper;

    @Autowired
    private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;

    @Autowired
    private GameAccountDOMapper gameAccountDOMapper;

    @Autowired
    private GameMindQuestionDOMapper gameMindQuestionDOMapper;


    @Transactional
    @Test
    @Rollback
    public void addAttendRecords(){
        GameAccountDO gameAccountDO = new GameAccountDO();
        gameAccountDO.setGmtCreate(new Date());
        gameAccountDO.setUserNum("M123456");
        gameAccountDO.setAccount("13131313131");
        gameAccountDO.setStarCount(10);
        gameAccountDOMapper.insertSelective(gameAccountDO);

        GameMindAccountDO accountDO = new GameMindAccountDO();
        accountDO.setGmtCreate(new Date());
        accountDO.setUserNum("M123456");
        accountDO.setFreeCount(1);
        accountDO.setIsGetFree(false);
        accountDO.setShareAttendCount(1);
        gameMindAccountDOMapper.insertSelective(accountDO);

        MindAttendParam param = new MindAttendParam();
        param.setUserNum(accountDO.getUserNum());
        param.setAttendType(AttendTypeEnum.STANDALONE);
        param.setAttendStar(10);
        param.setAttendPoint(20);
        param.setQuestionIds("21,25,10,9,22");
        param.setRoomNum("R123");
        gameMindAttendRecordService.addAttendRecords(param);

        List<GameMindAttendRecordDO> recordDOS = gameMindAttendRecordDOMapper.selectByExample(null);
        Assert.assertEquals(1,recordDOS.size());
        Assert.assertEquals(param.getAttendPoint(),recordDOS.get(0).getAttendPoint());
        Assert.assertEquals(param.getAttendType().getVal(),recordDOS.get(0).getAttendType());
        Assert.assertEquals(param.getAttendStar(),recordDOS.get(0).getAttendStar());
        Assert.assertEquals(param.getQuestionIds(),recordDOS.get(0).getQuestionIds());
    }

    @Transactional
    @Test
    @Rollback
    public void getGameAttendStatus(){
        GameAttendRecordStatusEnum statusEnum = gameMindAttendRecordService.getGameAttendStatus(1L,"m13245");
        Assert.assertEquals(null,statusEnum);
        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setAttendCount(1);
        recordDO.setUserNum("M123456");
        recordDO.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        gameMindAttendRecordDOMapper.insertSelective(recordDO);
        GameAttendRecordStatusEnum status = gameMindAttendRecordService.getGameAttendStatus(recordDO.getId(),recordDO.getUserNum());
        Assert.assertEquals(status,GameAttendRecordStatusEnum.ATTENDSUCCESS);

    }

    @Transactional
    @Rollback
    @Test
    public void answerQuestion(){
        GameMindQuestionDO questionDO = new GameMindQuestionDO();
        questionDO.setStatus(GameQuestionStatusEnum.ENABLE.getVal());
        questionDO.setRightAnswer(1);
        questionDO.setAnswers("[\"1\",\"2\",\"3\",\"以上都错\"]");
        questionDO.setGmtModified(new Date());
        questionDO.setCategoryId(1L);
        questionDO.setCategoryName("基础题");
        questionDO.setQuestionNum("Q974457879044030469");
        questionDO.setTitle("test");
        gameMindQuestionDOMapper.insertSelective(questionDO);
        GameMindQuestionDO questionDO2 = new GameMindQuestionDO();
        questionDO2.setStatus(GameQuestionStatusEnum.ENABLE.getVal());
        questionDO2.setRightAnswer(1);
        questionDO2.setAnswers("[\"1\",\"2\",\"3\",\"以上都错\"]");
        questionDO2.setGmtModified(new Date());
        questionDO2.setCategoryId(1L);
        questionDO2.setCategoryName("基础题");
        questionDO2.setQuestionNum("Q974457879044030469");
        questionDO2.setTitle("test");
        gameMindQuestionDOMapper.insertSelective(questionDO2);

        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setAttendCount(1);
        recordDO.setUserNum("M123456");
        recordDO.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        recordDO.setQuestionIds(questionDO.getId().toString());
        recordDO.setAttendPoint(1);
        recordDO.setAttendStar(1);
        recordDO.setGmtCreate(new Date());
        recordDO.setGmtModified(new Date());
        gameMindAttendRecordDOMapper.insertSelective(recordDO);

        GameMindAnswerQuestionParam param = new GameMindAnswerQuestionParam();
        param.setUserNum("M123456");
        param.setUserTime(2);
        param.setRightAnswer(1);
        param.setQuestionId(questionDO.getId());
        param.setQuestionIds(questionDO.getId() + "," + questionDO2.getId());

        List<String> idList = new ArrayList<>();
        idList.add(questionDO.getId().toString());
        idList.add(questionDO2.getId().toString());

        List<GameMindAttendRecordResultParam> record = new ArrayList<>();
        GameMindAttendRecordResultParam  resultParam = new GameMindAttendRecordResultParam();
        resultParam.setMindAttendId(recordDO.getId());
        resultParam.setGmtModified(new Date());
        resultParam.setUseTime(1);
        resultParam.setQuestionId(questionDO.getId());
        resultParam.setRightAnswer(questionDO.getRightAnswer());
        resultParam.setAnswer("test");
        record.add(resultParam);
        GameMindResultBO resultBO = gameMindAttendRecordService.answerQuestion(param,idList,record);
        Assert.assertEquals(resultBO.getPoint().intValue(),100);
        Assert.assertEquals(resultBO.getIsTrue(),true);
        Assert.assertEquals(resultBO.getIsFinish(),false);
      /*  GameMindAnswerQuestionParam param2 = new GameMindAnswerQuestionParam();
        param2.setUserNum("M123456");
        param2.setUserTime(2);
        param2.setRightAnswer(1);
        param2.setQuestionId(questionDO2.getId());
        param2.setQuestionIds(questionDO.getId() + "," + questionDO2.getId());

        GameMindResultBO resultBO2 = gameMindAttendRecordService.answerQuestion(param2,idList,record);
        Assert.assertEquals(resultBO2.getPoint().intValue(),300);
        Assert.assertEquals(resultBO2.getIsTrue(),true);
        Assert.assertEquals(resultBO2.getIsFinish(),true);*/
    }
}
