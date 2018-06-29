package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class PayOrderControllerTest {

    private MockMvc mvc;

    @Autowired
    private PayOrderController payOrderController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(payOrderController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void getMessageStatistics() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("merchantId","1");
        params.add("totalAmount","100");
        params.add("notFavoredAmount","10");
        params.add("favoredAmount","5");
        params.add("merchantNum","B0001");
        params.add("merchantFavoredId","1");
        RequestBuilder request = post("/payOrder/savePayOrderInfo").header("authorization","");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getpayOrderList() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("evaluationEnum","UN_EVALUATION");
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = post("/payOrder/getpayOrderList").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void delPayOrderInfo() {
        RequestBuilder request = delete("/payOrder/delPayOrderInfo/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getOrderInfo() {
        RequestBuilder request = get("/payOrder/getOrderInfo/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
