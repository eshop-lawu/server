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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/8/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MemberApiApplicationTest.class)
@WebAppConfiguration
public class ProductCategoryControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProductCategoryController productCategoryController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productCategoryController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void all() {
        RequestBuilder request = get("/productCategory/all").header("authorization","");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void id() {
        RequestBuilder request = get("/productCategory/1").header("authorization","").param("id","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void find() {
        RequestBuilder request = get("/productCategory/find/1").param("parentId","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
