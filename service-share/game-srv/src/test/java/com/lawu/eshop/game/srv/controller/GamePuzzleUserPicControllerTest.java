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
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleUserPicDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleUserPicControllerTest {

    private MockMvc mvc;

    @Autowired
    private GamePuzzleUserPicController gamePuzzleUserPicController;

    @Autowired
    private GamePuzzleUserPicDOMapper gamePuzzleUserPicDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(gamePuzzleUserPicController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveGamePuzzleUserPic() {
        GamePuzzleParam param = new GamePuzzleParam();
        param.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        param.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.CHECK_PENDING);
        param.setUserNum("M001");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gamePuzzleUserPic/saveGamePuzzleUserPic").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void getGamePuzzleUserPicUploadNumber() {
        RequestBuilder request = get("/gamePuzzleUserPic/getGamePuzzleUserPicUploadNumber").param("userNum", "M001");
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
    public void listGamePuzzleUserPic() {
        GamePuzzleUserPicRealQuery param = new GamePuzzleUserPicRealQuery();
        param.setUserNum("M001");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gamePuzzleUserPic/listGamePuzzleUserPic").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void listOperatorGamePuzzleUserPic() {
        OperatorGamePuzzleUserPicQuery param = new OperatorGamePuzzleUserPicQuery();
        param.setStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/gamePuzzleUserPic/listOperatorGamePuzzleUserPic").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void updateGamePuzzleUserPicStatus() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setStatus(GamePuzzleUserPicStatusEnum.CHECK_PENDING.getVal());
        gamePuzzleUserPicDOMapper.insertSelective(picDO);
        RequestBuilder request = put("/gamePuzzleUserPic/updateGamePuzzleUserPicStatus/" + picDO.getId()).param("statusEnum", "HAVE_USE");
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
