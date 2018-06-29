package com.lawu.eshop.user.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author meishuquan
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class CommonControllerTest {

    private MockMvc mvc;

    @Autowired
    private CommonController commonController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(commonController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getInviterByAccount() {
        RequestBuilder request = get("/user/common/getInviter/13666666666");
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
    public void selectHigherLevelInviters() {
        String requestJson = JSONObject.toJSONString(true);
        RequestBuilder request = post("/user/common/selectHigherLevelInviters/M0001").param("level", "1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void selectEFriend() {
        EFriendQueryDataParam dataParam = new EFriendQueryDataParam();
        dataParam.setUserNum("M00001");
        dataParam.setCurrentPage(1);
        dataParam.setPageSize(10);
        dataParam.setQueryContent("135");
        String requestJson = JSONObject.toJSONString(dataParam);
        RequestBuilder request = post("/user/common/selectEFriend").param("level", "1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
