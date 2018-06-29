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
public class ProductSolrControllerTest {

    private MockMvc mvc;

    @Autowired
    private ProductSolrController productSolrController;

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productSolrController).addInterceptors(authorizationInterceptor).build();
    }

    @Test
    public void listProductByCategoryId() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/productSolr/listProductByCategoryId").param("categoryId","1").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listRecommendProduct() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/productSolr/listRecommendProduct").param("categoryId","1").param("productId","1").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listProductByName() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/productSolr/listProductByName").param("name","1").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listProductSearchWord() {
        RequestBuilder request = get("/productSolr/listProductSearchWord").param("name","1");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listProduct() {
        RequestBuilder request = get("/productSolr/listProduct");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listYouLikeProduct() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("currentPage","1");
        params.add("pageSize","10");
        RequestBuilder request = get("/productSolr/listYouLikeProduct").params(params);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void listRecommendProductCategory() {
        RequestBuilder request = get("/productSolr/listRecommendProductCategory");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
