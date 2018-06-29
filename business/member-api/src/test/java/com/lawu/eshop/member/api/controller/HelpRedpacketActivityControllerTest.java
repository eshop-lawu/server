package com.lawu.eshop.member.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSONObject;
import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年1月4日
 * @updateDate 2018年1月4日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class HelpRedpacketActivityControllerTest extends BaseController {

    private MockMvc mvc;

    @Autowired
    private HelpRedpacketActivityController helpRedpacketActivityController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(helpRedpacketActivityController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void selectAddress() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/isOpen");
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        HelpRedpacketActivityOpenDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), HelpRedpacketActivityOpenDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getOpen());
        Assert.assertNotNull(actual.getStatus());
        Assert.assertNotNull(actual.getStartPic());
        Assert.assertNotNull(actual.getEndPic());
        Assert.assertNotNull(actual.getEndUrl());
    }
}
