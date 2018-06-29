package com.lawu.eshop.merchant.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lawu.eshop.merchant.api.MerchantApiApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MerchantApiApplicationTest.class)
@WebAppConfiguration
public class FansMerchantControllerTest {
    private MockMvc mvc;

    @Autowired
    private FansMerchantController fansMerchantController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(fansMerchantController).build();
    }

    @Test
    public void listInviteFans() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userSexEnum", "SEX_SECRET");
        map.add("isAgeLimit", "false");
        RequestBuilder request = get("/fansMerchant/listInviteFans").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void pageListInviteFans() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("currentPage", "1");
        map.add("pageSize", "10");
        map.add("userSexEnum", "SEX_SECRET");
        map.add("isAgeLimit", "false");
        RequestBuilder request = get("/fansMerchant/pageListInviteFans").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void inviteFans() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("nums", "B0001");
        map.add("userSexEnum", "SEX_SECRET");
        map.add("isAgeLimit", "false");
        map.add("payPwd", "123456");
        RequestBuilder request = post("/fansMerchant/inviteFans").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listFans() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("currentPage", "1");
        map.add("pageSize", "10");
        RequestBuilder request = get("/fansMerchant/listFans").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void countFans() {
        RequestBuilder request = get("/fansMerchant/countFans").header("authorization", "8888");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    
    @Test
    public void inviteFansWithContent() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("nums", "B0001");
        map.add("userSexEnum", "SEX_SECRET");
        map.add("isAgeLimit", "false");
        map.add("payPwd", "123456");
        map.add("Url", "urlurlurl");
        map.add("logoUrl", "logoUrlLogoUrl");
        map.add("merchantStoreName", "merchantStoreName");
        map.add("inviteContent", "inviteContent");
        map.add("merchantStoreIntro", "merchantStoreIntro");
        RequestBuilder request = post("/fansMerchant/inviteFansWithContent").header("authorization", "8888").params(map);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    
    @Test
    public void inviteFansWithContentExtend() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("nums", "B0001");
        map.add("userSexEnum", "SEX_SECRET");
        map.add("isAgeLimit", "false");
        map.add("payPwd", "123456");
        map.add("Url", "urlurlurl");
        map.add("logoUrl", "logoUrlLogoUrl");
        map.add("merchantStoreName", "merchantStoreName");
        map.add("inviteContent", "inviteContent");
        map.add("merchantStoreIntro", "merchantStoreIntro");
        map.add("inviteCount", "10");
        map.add("inviteTypeEnum", "ALL");
        map.add("regionPath", "");
        RequestBuilder request = post("/fansMerchant/inviteFansWithContentExtend").header("authorization", "8888").params(map);
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
