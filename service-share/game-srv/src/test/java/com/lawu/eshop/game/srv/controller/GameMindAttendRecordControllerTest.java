package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.constants.GameQuestionStatusEnum;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/3/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameMindAttendRecordControllerTest {
    private MockMvc mvc;

    @Autowired
    private GameMindAttendRecordController gameMindAttendRecordController;

    @Autowired
    private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;

    @Autowired
    private GameMindQuestionDOMapper gameMindQuestionDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(gameMindAttendRecordController).build();
    }

    @Test
    @Transactional
    @Rollback
    public void getGameAttendStatus() {
        RequestBuilder request = get("/gameMindAttendRecord/getGameAttendStatus/1").param("userNum","m123456");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setUserNum("M123456");
        recordDO.setAttendCount(1);
        recordDO.setAttendNum("MG123456");
        recordDO.setAttendPoint(10);
        recordDO.setAttendStar(1);
        recordDO.setAttendType(AttendTypeEnum.STANDALONE.getVal());
        recordDO.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
        recordDO.setQuestionIds("19,23,9,4,8");
        recordDO.setGmtCreate(new Date());
        recordDO.setRoomNum("R123456");
        recordDO.setGmtModified(new Date());
        gameMindAttendRecordDOMapper.insertSelective(recordDO);
        RequestBuilder request2 = get("/gameMindAttendRecord/getGameAttendStatus/"+ recordDO.getId()).param("userNum",recordDO.getUserNum());
        try {
            ResultActions perform = mvc.perform(request2);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Transactional
    @Test
    @Rollback
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
        param.setQuestionId(1L);
        param.setUserTime(10);
        param.setQuestionIds("1,17,20,13,18");
        param.setRightAnswer(1);
        param.setUserNum(recordDO.getUserNum());
        param.setAttendId(1L);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameMindAttendRecord/answerQuestion").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

}
