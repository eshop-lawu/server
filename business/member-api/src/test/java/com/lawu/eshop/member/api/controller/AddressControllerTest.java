package com.lawu.eshop.member.api.controller;

import com.lawu.authorization.interceptor.AuthorizationInterceptor;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.member.api.MemberApiApplicationTest;
import com.lawu.framework.web.BaseController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class AddressControllerTest extends BaseController {

    private MockMvc mvc;

    @Autowired
    private AddressController addressController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(addressController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void selectAddress() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("longitude","12.2121");
        params.add("latitude","22.2121");
        RequestBuilder request = get("/address/list").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getById() {
        RequestBuilder request = get("/address/get/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void remove() {
        RequestBuilder request = delete("/address/remove/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void addAddress() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name","张三丰");
        params.add("mobile","13800138000");
        params.add("regionPath","1/101/10101");
        params.add("regionName","广东省深圳市南山区");
        params.add("addr","大冲商务中心花园");
        params.add("postcode","000000");
        RequestBuilder request = post("/address/addAddress").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void update() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("name","张三丰");
        params.add("mobile","13800138000");
        params.add("regionPath","1/101/10101");
        params.add("regionName","广东省深圳市南山区");
        params.add("addr","大冲商务中心花园");
        params.add("postcode","000000");
        RequestBuilder request = post("/address/update/1").header("authorization","").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateDefault() {
        RequestBuilder request = put("/address/updateDefault/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
