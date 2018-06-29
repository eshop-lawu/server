package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameDialControllerTest {

    private MockMvc mvc;

    @Autowired
    private GameDialController gameDialController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(gameDialController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getGameDialDetail() {
        RequestBuilder request = get("/gameDial/getGameDialDetail");
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
    public void getGameDialById() {
        RequestBuilder request = get("/gameDial/getGameDialById/1");
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
    public void updateGameDial() {
        GameDialParam param = new GameDialParam();
        param.setName("test");
        param.setFreeCount(1);
        param.setImgPath("png");
        param.setPoint(10);
        param.setShareAttendCount(1);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameDial/updateGameDial/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void saveGameDial() {
        GameDialParam param = new GameDialParam();
        param.setName("test");
        param.setFreeCount(1);
        param.setImgPath("png");
        param.setPoint(10);
        param.setShareAttendCount(1);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameDial/saveGameDial").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void page() {
        GameDialQuery param = new GameDialQuery();
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gameDial/page").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
