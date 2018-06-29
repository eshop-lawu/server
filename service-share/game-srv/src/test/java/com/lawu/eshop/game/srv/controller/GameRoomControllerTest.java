package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameRoomControllerTest {

    private MockMvc mvc;

    @Autowired
    private GameRoomController gameRoomController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(gameRoomController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveGameRoom() {
        GameRoomParam param = new GameRoomParam();
        param.setStatusEnum(GameRoomStatusEnum.WAITING);
        param.setHardLevelEnum(GameHardLevelEnum.SIMPLE);
        param.setTypeEnum(GameTypeEnum.PUZZLE);
        param.setPwd("123456");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameRoom/saveGameRoom").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listGameRoom() {
        GameRoomQuery param = new GameRoomQuery();
        param.setTypeEnum(GameTypeEnum.PUZZLE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameRoom/listGameRoom").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getGameRoom() {
        RequestBuilder request = get("/gameRoom/getGameRoom/1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void verifyGameRoomPwd() {
        RequestBuilder request = get("/gameRoom/verifyGameRoomPwd/1").param("pwd", "123456");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void joinGameRoom() {
        JoinGameRoomParam param = new JoinGameRoomParam();
        param.setRoomNum("1");
        param.setUserNum("M001");
        param.setNickName("test");
        param.setHeadImg("png");
        param.setGameType(GameTypeEnum.PUZZLE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/gameRoom/joinGameRoom").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void exitGameRoom() {
        ExitGameRoomParam param = new ExitGameRoomParam();
        param.setUserNum("M001");
        param.setRoomNum("1");
        param.setGameType(GameTypeEnum.PUZZLE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/gameRoom/exitGameRoom").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateGameRoomPlayerReadyStatus() {
        UpdateGameRoomPlayerReadyStatusParam param = new UpdateGameRoomPlayerReadyStatusParam();
        param.setUserNum("M001");
        param.setRoomNum("1");
        param.setGameType(GameTypeEnum.PUZZLE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/gameRoom/updateGameRoomPlayerReadyStatus").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getGameRoomByRoomNum() {
        RequestBuilder request = get("/gameRoom/getGameRoomByRoomNum").param("roomNum", "100").param("typeEnum", "PUZZLE");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getGameRoomByUserNum() {
        RequestBuilder request = get("/gameRoom/getGameRoomByUserNum").param("userNum", "M001").param("typeEnum", "PUZZLE");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
