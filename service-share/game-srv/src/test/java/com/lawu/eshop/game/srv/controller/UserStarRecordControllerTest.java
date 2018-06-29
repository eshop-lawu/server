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
import com.lawu.eshop.game.constants.StarRecordStatusEnum;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2018/3/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class UserStarRecordControllerTest {
    private MockMvc mvc;

    @Autowired

    private UserStarRecordController userStarRecordController;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userStarRecordController).build();
    }


    @Test
    @Transactional
    @Rollback
    public void addUserStartRecord(){
        UserStarRecordParam param = new UserStarRecordParam();
        param.setStarCount(10);
        param.setStatus(StarRecordStatusEnum.INCREASE);
        param.setUserNum("M123456");
        param.setAccount("13111111");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/userStar/addUserStartRecord").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional
    @Test
    @Rollback
    public void getStarRankList(){
        RankListParam param = new RankListParam();
        param.setReportDate(DateUtil.getFirstDayOfMonth(new Date()));
        param.setCurrentPage(1);
        param.setPageSize(10);

        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/userStar/getStarRankList").contentType(MediaType.APPLICATION_JSON)
                .content(requestJson);
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

    @Transactional
    @Rollback
    @Test
    public void currentUserRank(){
        RequestBuilder request = get("/userStar/currentUserRank").param("userNum","M123456");
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
