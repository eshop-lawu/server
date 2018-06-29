package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.merchant.api.MerchantApiApplicationTest;
import com.lawu.framework.web.HttpCode;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MerchantApiApplicationTest.class)
@WebAppConfiguration
public class ReportFansControllerTest {
    private MockMvc mvc;

    @Autowired
    private ReportFansController reportFansController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(reportFansController).build();
    }

    @Test
    public void fansRiseRate() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("flag", "DAY");
        RequestBuilder request = get("/reportFans/fansRiseRate").header("authorization", "8888").params(map).requestAttr(AuthorizationInterceptor.REQUEST_CURRENT_USER_ID, "200");
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
    public void fansRiseSource() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("flag", "DAY");
        RequestBuilder request = get("/reportFans/fansRiseSource").header("authorization", "8888").params(map).requestAttr(AuthorizationInterceptor.REQUEST_CURRENT_USER_ID, "200");
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
    public void fansSaleTransform() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("flag", "DAY");
        RequestBuilder request = get("/reportFans/fansSaleTransform").header("authorization", "8888").params(map).requestAttr(AuthorizationInterceptor.REQUEST_CURRENT_USER_ID, "200");
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
