package com.lawu.eshop.user.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author meishuquan
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class FansMerchantControllerTest {

    private MockMvc mvc;

    @Autowired
    private FansMerchantController fansMerchantController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(fansMerchantController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listInviteFans() {
        ListInviteFansParam param = new ListInviteFansParam();
        param.setUserSexEnum(UserSexEnum.SEX_SECRET);
        param.setIsAgeLimit(false);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/fansMerchant/listInviteFans/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void listInviteFansWithContent() {
    	ListInviteFansWithContentParam param = new ListInviteFansWithContentParam();
        param.setUserSexEnum(UserSexEnum.SEX_SECRET);
        param.setIsAgeLimit(false);
        param.setNums("1,2");
        param.setInviteType((byte)3);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/fansMerchant/listInviteFansWithContent/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void pageListInviteFans() {
        ListInviteFansParam param = new ListInviteFansParam();
        param.setUserSexEnum(UserSexEnum.SEX_SECRET);
        param.setIsAgeLimit(false);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/fansMerchant/pageListInviteFans/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void listFans() {
        ListFansParam param = new ListFansParam();
        param.setRegionPath("44/4403/440303");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/fansMerchant/listFans/200").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void isFansMerchant() {
        RequestBuilder request = get("/fansMerchant/isFansMerchant/200").param("memberId", "100");
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
    public void countFans() {
        RequestBuilder request = get("/fansMerchant/countFans/200");
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
    public void findMerchant() {
        RequestBuilder request = get("/fansMerchant/findMerchant").param("memberId", "100");
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
    public void findFensCount() {
        RequestBuilder request = get("/fansMerchant/findFensCount").param("merchantId", "200");
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
    public void saveFansMerchant() {
        RequestBuilder request = put("/fansMerchant/saveFansMerchant/200").param("memberId", "100").param("channelEnum", "INVITE");
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
    public void saveFansMerchantFromInvite() {
        RequestBuilder request = put("/fansMerchant/saveFansMerchantFromInvite/200").param("memberId", "100").param("messageId", "1").param("dealWay", "true");
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
